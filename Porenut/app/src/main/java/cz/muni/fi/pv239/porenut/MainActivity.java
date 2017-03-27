package cz.muni.fi.pv239.porenut;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recycler = (RecyclerView) findViewById(R.id.recycler);
        recycler.setLayoutManager(new GridLayoutManager(this, 2));
        recycler.setAdapter(new MyAdapter(new String[]{"Jídlo", "Potřeby", "Nálady", "a", "b", "c"}));

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        RecyclerView recycler = (RecyclerView) findViewById(R.id.recycler);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            recycler.setLayoutManager(new GridLayoutManager(this, 3));
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            recycler.setLayoutManager(new GridLayoutManager(this, 2));
        }
        recycler.setAdapter(new MyAdapter(new String[]{"Jídlo", "Potřeby", "Nálady", "a", "b", "c"}));
    }
}
