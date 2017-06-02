package cz.muni.fi.pv239.porenut.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import cz.muni.fi.pv239.porenut.R;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class AdminModeActivity extends AppCompatActivity {

    private long itemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_mode);

        Button loadButton = (Button) findViewById(R.id.load_button);
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ItemActivity.class);
                intent.putExtra("categoryId", -1);

                startActivity(intent);
            }
        });

        itemId = getIntent().getLongExtra("id", -1);

        Button saveButton = (Button) findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Realm.init(getApplicationContext());
                RealmConfiguration config = new RealmConfiguration.Builder()
                        .name("myrealm.realm")
                        .deleteRealmIfMigrationNeeded()
                        .build();

                final Realm mRealm = Realm.getInstance(config);
            }
        });

        if(getIntent().getBooleanExtra("toFill", false)) {
            List<String> colors = Arrays.asList((getResources().getStringArray(R.array.colorArray)));

            EditText text = (EditText) findViewById(R.id.item_text);
            text.setText(getIntent().getStringExtra("name"));
            EditText audioFileName = (EditText) findViewById(R.id.audio_file_name);
            audioFileName.setText(getIntent().getStringExtra("audioFile"));
            Spinner cardColorSpinner = (Spinner) findViewById(R.id.card_color_spinner);
            cardColorSpinner.setSelection(getIndexFromName(getApplicationContext()
                    .getResources()
                    .getResourceEntryName(getIntent()
                        .getIntExtra("cardColor", 0)), colors));
            Spinner textColorSpinner = (Spinner) findViewById(R.id.text_color_spinner);
            textColorSpinner.setSelection(getIndexFromName(getApplicationContext()
                    .getResources()
                    .getResourceEntryName(getIntent()
                        .getIntExtra("textColor", 0)), colors));

        }
    }

    private int getIndexFromName(String name, List<String> list) {
        for(int i = 0; i < list.size(); i++) {
            if(list.get(i).equals(name)){
                return i;
            }
        }
        return 0;
    }
}
