package cz.muni.fi.pv239.porenut;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cz.muni.fi.pv239.porenut.activities.MainActivity;

public class FirstRun extends AppCompatActivity {

    private EditText mEditText;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_run);
        mEditText = (EditText) findViewById(R.id.first_run_edit_text);
        mButton = (Button) findViewById(R.id.first_run_button);

        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mButton.setEnabled(s.length() == 6);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final String PREFS_NAME = "MyPrefsFile";
                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                settings.edit().putInt("admin_code",
                        Integer.parseInt(mEditText.getText().toString())).commit();
                Log.d("FirstRun", "Code has been stored");
                finish();
                startActivity(
                        new Intent(getApplicationContext(),MainActivity.class));
            }
        });
    }


}
