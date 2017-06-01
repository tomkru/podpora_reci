package cz.muni.fi.pv239.porenut.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import cz.muni.fi.pv239.porenut.Initializer;
import cz.muni.fi.pv239.porenut.R;
import cz.muni.fi.pv239.porenut.SpacesItemDecoration;
import cz.muni.fi.pv239.porenut.adapters.CategoryAdapter;
import cz.muni.fi.pv239.porenut.entities.Category;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Context mContext;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private CategoryAdapter mAdapter;
    private Realm mRealm;
    private RealmResults<Category> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = getApplicationContext();


        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("myrealm.realm")
                //.deleteRealmIfMigrationNeeded()
                .build();

        mRealm = Realm.getInstance(config);

        final String PREFS_NAME = "MyPrefsFile";
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

        // uncomment for simulate first time run
        settings.edit().putBoolean("my_first_time", true).commit();
        if (settings.getBoolean("my_first_time", true)) {
            Toast.makeText(mContext, "Inicializuji DB", Toast.LENGTH_LONG).show();
            settings.edit().putBoolean("my_first_time", false).commit();

            mRealm.beginTransaction();
            mRealm.deleteAll();
            mRealm.commitTransaction();

            Initializer initializer = new Initializer(mRealm, mContext);
            initializer.initData();
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(getResources().getInteger(R.integer.padding)));
        mLayoutManager = new GridLayoutManager(mContext, getResources().getInteger(R.integer.column));
        mRecyclerView.setLayoutManager(mLayoutManager);

        categories = mRealm.where(Category.class).findAll();

        //TODO delete
        Category category = mRealm.where(Category.class).equalTo("id",1).findFirst();
        Log.d("MainActivity", "Category with id 1 has title " + category.getTitle() +" and "
                +category.getItems().size() + " items");

        mAdapter = new CategoryAdapter(mContext,categories);
        mRecyclerView.setAdapter(mAdapter);

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

        getSupportActionBar().setTitle("Vyber kategorii");
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

        if (id == R.id.nav_keyboard) {
            Toast.makeText(
                    mContext,
                    "Activita pro psani na klavesnici",
                    Toast.LENGTH_SHORT
            ).show();
            Intent intent = new Intent(this, TextToSpeechActivity.class);
            this.startActivity(intent);
        } else if (id == R.id.nav_recently_used) {
            Toast.makeText(
                    mContext,
                    "Activita pro posledni pouzite",
                    Toast.LENGTH_SHORT
            ).show();
        } else if (id == R.id.nav_auth) {
            Toast.makeText(
                    mContext,
                    "Administratorsky rezim",
                    Toast.LENGTH_SHORT
            ).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // TODO uncomment
        //mRealm.close();
    }
}
