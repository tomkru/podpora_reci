package cz.muni.fi.pv239.porenut.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import cz.muni.fi.pv239.porenut.R;
import cz.muni.fi.pv239.porenut.adapters.ItemAdapter;
import cz.muni.fi.pv239.porenut.entities.Category;
import cz.muni.fi.pv239.porenut.entities.Item;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmResults;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        //Realm.init(this);
        //mRealm = Realm.getDefaultInstance();

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("myrealm.realm")
                //.deleteRealmIfMigrationNeeded()
                .build();
        mRealm = Realm.getInstance(config);

        if (mRealm == null) {
            Log.e("ItemActivity","Realm is null");
        } else {
            Log.d("ItemActivity","Realm getDefaultInstance OK");
        }

        /*Category category = mRealm.where(Category.class).equalTo("id",getIntent()
                                    .getStringExtra("categoryId")).findFirst();*/
        RealmResults<Category> categories = mRealm.where(Category.class).findAll();
        Log.d("ItemActivity", "Category count "+categories.size());
        Category category = mRealm.where(Category.class).equalTo("id",1).findFirst();
        if (category == null) {
            Log.e("ItemActivity","Category not found");
        }
        Log.d("ItemActivity", "Found category with title "+category.getTitle());
        items = category.getItems();
        Log.d("ItemActivity","Item list length "+items.size());
        /*
        for(int i = 0; i < 15; i++){
            if (i % 2 == 0 )items.add(new Item("item item test testt testestetestestsetetse " + i, R.raw.dobry_den));
            else items.add(new Item("item " + i, R.raw.kava));
        }*/

        getSupportActionBar().setTitle(getIntent().getStringExtra("categoryTitle"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        context = getApplicationContext();
        mRecyclerView = (RecyclerView) findViewById(R.id.category_recycler_view);
        mLayoutManager = new GridLayoutManager(context, getResources().getInteger(R.integer.column));
        mRecyclerView.setLayoutManager(mLayoutManager);


        mAdapter = new ItemAdapter(context, items);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRealm.close();
    }
}
