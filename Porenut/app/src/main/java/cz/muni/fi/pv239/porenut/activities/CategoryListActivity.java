package cz.muni.fi.pv239.porenut.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cz.muni.fi.pv239.porenut.R;
import cz.muni.fi.pv239.porenut.entities.CategoryItem;
import cz.muni.fi.pv239.porenut.adapters.CategoryItemAdapter;

/**
 * Created by pcyprian on 2.4.2017.
 */

public class CategoryListActivity extends AppCompatActivity {

    private Context context;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);

        List<CategoryItem> items = new ArrayList<>();
        for(int i = 0; i < 15; i++){
            if (i % 2 == 0 )items.add(new CategoryItem("item item test testt testestetestestsetetse " + i, R.raw.dobry_den));
            else items.add(new CategoryItem("item " + i, R.raw.kava));
        }

        getSupportActionBar().setTitle(getIntent().getStringExtra("category"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        context = getApplicationContext();
        mRecyclerView = (RecyclerView) findViewById(R.id.category_recycler_view);
        mLayoutManager = new GridLayoutManager(context, getResources().getInteger(R.integer.column));
        mRecyclerView.setLayoutManager(mLayoutManager);


        mAdapter = new CategoryItemAdapter(context, items);
        mRecyclerView.setAdapter(mAdapter);

    }
}
