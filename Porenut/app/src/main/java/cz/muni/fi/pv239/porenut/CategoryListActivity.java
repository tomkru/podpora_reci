package cz.muni.fi.pv239.porenut;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pcyprian on 2.4.2017.
 */

public class CategoryListActivity extends AppCompatActivity {

    private ListView categoryItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);

        List<CategoryItem> items = new ArrayList<>();
        categoryItems = (ListView) findViewById(R.id.category_recycler_view);
        for(int i = 0; i < 15; i++){
            items.add(new CategoryItem("item " + i));
        }

        getSupportActionBar().setTitle(getIntent().getStringExtra("category"));
        categoryItems.setAdapter(new CategoryItemAdapter(this, items));
    }
}
