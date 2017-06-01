package cz.muni.fi.pv239.porenut.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import cz.muni.fi.pv239.porenut.R;
import cz.muni.fi.pv239.porenut.adapters.ItemAdapter;
import cz.muni.fi.pv239.porenut.entities.Category;
import cz.muni.fi.pv239.porenut.entities.Item;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;

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

        // TODO je dobre tato inicializace nebo ta nize???
        //Realm.init(this);
        //mRealm = Realm.getDefaultInstance();

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("myrealm.realm")
                .deleteRealmIfMigrationNeeded()
                .build();
        mRealm = Realm.getInstance(config);

        if (mRealm == null) {
            Log.e("ItemActivity","Realm is null");
        }

        Category category = mRealm.where(Category.class).equalTo("id",getIntent()
                                    .getLongExtra("categoryId",Long.MIN_VALUE)).findFirst();
        mRealm.beginTransaction();
        category.setCounter(category.getCounter()+1);
        mRealm.commitTransaction();
        if (category == null) {
            Log.e("ItemActivity","Category wasn't found");
        }
        items = category.getItems();
        Log.d("ItemActivity","Category with id "+category.getId()+" has "+items.size()+" items");

        getSupportActionBar().setTitle(category.getTitle());
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
