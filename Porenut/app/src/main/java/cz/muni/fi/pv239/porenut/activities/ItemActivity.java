package cz.muni.fi.pv239.porenut.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import cz.muni.fi.pv239.porenut.R;
import cz.muni.fi.pv239.porenut.adapters.ItemAdapter;
import cz.muni.fi.pv239.porenut.entities.Category;
import cz.muni.fi.pv239.porenut.entities.Item;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by pcyprian on 2.4.2017.
 */

public class ItemActivity extends AppCompatActivity {

    private Context context;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private Realm mRealm;
    private RealmList<Item> items;
    private List<Item> itemsToAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);


        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("myrealm.realm")
                .deleteRealmIfMigrationNeeded()
                .build();
        mRealm = Realm.getInstance(config);

        if (mRealm == null) {
            Log.e("ItemActivity","Realm is null");
        }

        RealmResults<Item> sortedItems = null;

        switch (getIntent().getIntExtra("itemMode",0)) {
            // normal mode (category clicked)
            case 0 : {
                final String PREFS_NAME = "MyPrefsFile";
                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                Category category = null;
                if(getIntent().getLongExtra("categoryId", -1) != -1) {
                    category = mRealm.where(Category.class).equalTo("id", getIntent()
                            .getLongExtra("categoryId", Long.MIN_VALUE)).findFirst();
                }
                if (category == null) {
                    Log.e("ItemActivity","Category wasn't found");
                }
                items = category.getItems();
                Log.d("ItemActivity","Category with id "+category.getId()+" has "+items.size()+" items");
                getSupportActionBar().setTitle(category.getTitle());

                if (settings.getBoolean("order", true)) {
                    sortedItems = items.sort("order", Sort.ASCENDING);
                    itemsToAdapter = sortedItems.subList(0, items.size());
                } else {
                    sortedItems = items.sort("counter", Sort.DESCENDING);
                    itemsToAdapter = sortedItems.subList(0, items.size());
                }

                break;
            }
            case 1 : {
                getSupportActionBar().setTitle(R.string.edit);
                sortedItems = mRealm.where(Item.class).findAll().sort("lastUsed", Sort.DESCENDING);
                itemsToAdapter = sortedItems.subList(0, sortedItems.size());
                break;
            }
            case 2 : {
                getSupportActionBar().setTitle(R.string.favourite);
                sortedItems = mRealm.where(Item.class).findAll().sort("counter", Sort.DESCENDING);
                itemsToAdapter = sortedItems.subList(0, 20);


                break;
            }
            case 3 : {
                getSupportActionBar().setTitle(R.string.last);
                sortedItems = mRealm.where(Item.class).findAll().sort("lastUsed", Sort.DESCENDING);
                itemsToAdapter = sortedItems.subList(0, 20);


                break;
            }
        }

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        context = getApplicationContext();
        mRecyclerView = (RecyclerView) findViewById(R.id.category_recycler_view);
        mLayoutManager = new GridLayoutManager(context, getResources().getInteger(R.integer.column));
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new ItemAdapter(context, itemsToAdapter, getIntent().getIntExtra("itemMode",0) == 1);

        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRealm.close();
    }
}
