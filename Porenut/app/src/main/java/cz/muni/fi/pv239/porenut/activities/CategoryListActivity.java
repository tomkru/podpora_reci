package cz.muni.fi.pv239.porenut.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cz.muni.fi.pv239.porenut.R;
import cz.muni.fi.pv239.porenut.Utility;
import cz.muni.fi.pv239.porenut.adapters.CategoryAdapter;
import cz.muni.fi.pv239.porenut.entities.Category;
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
            items.add(new CategoryItem("item " + i));
        }

        getSupportActionBar().setTitle(getIntent().getStringExtra("category"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        context = getApplicationContext();
        mRecyclerView = (RecyclerView) findViewById(R.id.category_recycler_view);
        mLayoutManager = new GridLayoutManager(context, 1); //Utility.calculateNoOfColumns(context));
        mRecyclerView.setLayoutManager(mLayoutManager);


        mAdapter = new CategoryItemAdapter(context, items);
        mRecyclerView.setAdapter(mAdapter);

    }
}
