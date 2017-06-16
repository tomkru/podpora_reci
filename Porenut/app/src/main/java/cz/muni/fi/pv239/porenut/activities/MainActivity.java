package cz.muni.fi.pv239.porenut.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cz.muni.fi.pv239.porenut.FirstRun;
import cz.muni.fi.pv239.porenut.Initializer;
import cz.muni.fi.pv239.porenut.R;
import cz.muni.fi.pv239.porenut.SpacesItemDecoration;
import cz.muni.fi.pv239.porenut.adapters.CategoryAdapter;
import cz.muni.fi.pv239.porenut.entities.Category;
import cz.muni.fi.pv239.porenut.entities.Item;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import io.realm.Sort;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Context mContext;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private CategoryAdapter mAdapter;
    private Realm mRealm;
    private RealmResults<Category> categories;
    public static final String PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = getApplicationContext();


        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("myrealm.realm")
                .deleteRealmIfMigrationNeeded()
                .build();

        mRealm = Realm.getInstance(config);


        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

        // uncomment for simulate first time run
        //settings.edit().putBoolean("my_first_time", true).commit();
        if (settings.getBoolean("my_first_time", true)) {
            Toast.makeText(mContext, "Inicializuji DB", Toast.LENGTH_SHORT).show();
            settings.edit().putBoolean("my_first_time", false).commit();

            mRealm.beginTransaction();
            mRealm.deleteAll();
            mRealm.commitTransaction();

            Initializer initializer = new Initializer(mRealm, mContext);
            initializer.initData();

            Intent intent = new Intent(mContext, FirstRun.class);
            this.startActivity(intent);
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(getResources().getInteger(R.integer.padding)));
        mLayoutManager = new GridLayoutManager(mContext, getResources().getInteger(R.integer.column));
        mRecyclerView.setLayoutManager(mLayoutManager);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Menu menu = navigationView.getMenu();
        MenuItem order = menu.findItem(R.id.nav_order);
        MenuItem counter = menu.findItem(R.id.nav_counter);


        getSupportActionBar().setTitle(R.string.choose);


        categories = mRealm.where(Category.class).findAll();

        if (settings.getBoolean("order", true)) {
            categories = categories.sort("order", Sort.ASCENDING);
            order.setChecked(true);
            counter.setChecked(false);
        } else {
            categories = categories.sort("counter", Sort.DESCENDING);
            order.setChecked(false);
            counter.setChecked(true);
        }


        mAdapter = new CategoryAdapter(mContext,categories);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_keyboard: {
                Log.d("MainActivity","Click on manual input");
                Intent intent = new Intent(this, TextToSpeechActivity.class);
                this.startActivity(intent);
                break;
            }
            case R.id.nav_recently_used: {
                Log.d("MainActivity","Click on last used");
                Intent intent = new Intent(this, ItemActivity.class);
                intent.putExtra("itemMode", 3);
                this.startActivity(intent);
                break;
            }
            case R.id.nav_counter: {
                Log.d("MainActivity","Sort by counter");
                final String PREFS_NAME = "MyPrefsFile";
                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                settings.edit().putBoolean("order", false).commit();
                finish();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.nav_order: {
                Log.d("MainActivity","Sort by order");

                final String PREFS_NAME = "MyPrefsFile";
                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                settings.edit().putBoolean("order", true).commit();
                finish();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.nav_auth: {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder
                        .setTitle(R.string.enter_admin_mode)
                        .setNegativeButton(R.string.no, null)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(mContext, "Prihlaseni do admin modu", Toast.LENGTH_SHORT).show();
                                        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                                        View mView = getLayoutInflater().inflate(R.layout.confirm_admin_code, null);
                                        final EditText mAdminCodeEditText = (EditText) mView.findViewById(R.id.confirm_admin_code_editText);
                                        mAdminCodeEditText.setInputType(InputType.TYPE_CLASS_NUMBER);

                                        Button mEnterCode = (Button) mView.findViewById(R.id.confirm_admin_code_button);

                                        mBuilder.setView(mView);
                                        final AlertDialog dialogCode = mBuilder.create();
                                        dialogCode.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
                                        dialogCode.show();

                                        mEnterCode.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                String enteredCode = mAdminCodeEditText.getText().toString();
                                                if ( enteredCode == null || enteredCode.isEmpty() || !TextUtils.isDigitsOnly(enteredCode) ) {
                                                    Toast.makeText(mContext, R.string.wrong_code, Toast.LENGTH_SHORT).show();
                                                    dialogCode.dismiss();
                                                    return;
                                                }

                                                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                                                if (Integer.parseInt(enteredCode) == settings.getInt("admin_code", -1)) {
                                                    Toast.makeText(mContext, "Spravny kod", Toast.LENGTH_SHORT).show();
                                                    Intent intent = new Intent(MainActivity.this, AdminModeActivity.class);
                                                    MainActivity.this.startActivity(intent);
                                                } else {
                                                    Toast.makeText(mContext, R.string.wrong_code, Toast.LENGTH_SHORT).show();
                                                    dialogCode.dismiss();
                                                    return;
                                                }
                                            }
                                        });
                                    }
                                }
                        ).create();
                alertDialogBuilder.show();
                break;
            }
            default:
                break;
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRealm.close();
    }
}
