package cz.muni.fi.pv239.porenut.activities;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import cz.muni.fi.pv239.porenut.R;
import cz.muni.fi.pv239.porenut.entities.Category;
import cz.muni.fi.pv239.porenut.entities.Item;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class AdminModeActivity extends AppCompatActivity {

    private static final String LOG_TAG = "AudioRecord";
    private String itemId = "";
    private MediaRecorder mRecorder = null;

    // Requesting permission to RECORD_AUDIO
    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
    private boolean permissionToRecordAccepted = false;
    private String [] permissions = {Manifest.permission.RECORD_AUDIO};

    EditText audioFileName;
    Button saveButton;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_RECORD_AUDIO_PERMISSION:
                permissionToRecordAccepted  = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                break;
        }
        if (!permissionToRecordAccepted ) finish();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_mode);

        final EditText text = (EditText) findViewById(R.id.item_text);
        final Spinner cardColorSpinner = (Spinner) findViewById(R.id.card_color_spinner);
        final Spinner textColorSpinner = (Spinner) findViewById(R.id.text_color_spinner);
        audioFileName = (EditText) findViewById(R.id.audio_file_name);
        final Button recordButton = (Button) findViewById(R.id.record_button);
        final Spinner categorySpinner = (Spinner) findViewById(R.id.categorySpinner);
        saveButton = (Button) findViewById(R.id.save_button);

        saveButton.setEnabled(false);

        text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (text.getText().length() > 0 ) {
                    audioFileName.setText(getExternalFilesDir(null).getAbsolutePath() +"/audio_" + text.getText().toString() + ".3gp");
                } else {
                    audioFileName.setText("");
                }
            }
        });

        audioFileName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (audioFileName.getText().length() > 0 ) {
                    recordButton.setEnabled(true);
                } else {
                    recordButton.setEnabled(false);
                }
            }
        });

        recordButton.setOnClickListener(new View.OnClickListener() {
            Boolean mStartRecording = true;
            @Override
            public void onClick(View view) {
                onRecord(mStartRecording);
                if (mStartRecording) {
                    recordButton.setText("Stop recording");
                } else {
                    recordButton.setText("Start recording");
                }
                mStartRecording = !mStartRecording;
            }
        });

        Button loadButton = (Button) findViewById(R.id.load_button);
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ItemActivity.class);
                intent.putExtra("categoryId", -1);

                startActivity(intent);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Realm.init(getApplicationContext());
                RealmConfiguration config = new RealmConfiguration.Builder()
                        .name("myrealm.realm")
                        .deleteRealmIfMigrationNeeded()
                        .build();

                final Realm mRealm = Realm.getInstance(config);

                String id = text.getText().toString().toLowerCase().replace(' ', '_');;

                Item item = mRealm.where(Item.class).findAll().where().equalTo("id", itemId).findFirst();
                mRealm.beginTransaction();
                if(item == null) {
                    Item item2 = mRealm.createObject(Item.class, id);
                    item2.setText(text.getText().toString());
                    item2.setUserAudioFileId(audioFileName.getText().toString());

                    String cardColor = getResources()
                            .getStringArray(R.array.entryNameColor)[cardColorSpinner.getSelectedItemPosition()];
                    int cardColorId = getResources().getIdentifier(cardColor, "color", getPackageName());
                    item2.setCardColor(cardColorId);
                    String textColor = getResources()
                            .getStringArray(R.array.entryNameColor)[textColorSpinner.getSelectedItemPosition()];
                    int textColorId = getResources().getIdentifier(textColor, "color", getPackageName());
                    item2.setTextColor(textColorId);
                    item2.setUser(true);

                    if (mRealm.where(Item.class).findAll().where().equalTo("id", id).findFirst() != null){
                        Toast.makeText(getApplicationContext(),
                                "Polozka s tymto id uz existuje",
                                Toast.LENGTH_LONG).show();
                    }
                    
                    mRealm.copyToRealm(item2);
                } else {
                    item.setText(text.getText().toString());
                    String cardColor = getResources()
                            .getStringArray(R.array.entryNameColor)[cardColorSpinner.getSelectedItemPosition()];
                    int cardColorId = getResources().getIdentifier(cardColor, "color", getPackageName());
                    item.setCardColor(cardColorId);
                    String textColor = getResources()
                            .getStringArray(R.array.entryNameColor)[textColorSpinner.getSelectedItemPosition()];
                    int textColorId = getResources().getIdentifier(textColor, "color", getPackageName());
                    item.setTextColor(textColorId);
                }
                mRealm.commitTransaction();

                mRealm.beginTransaction();
                if(item == null) {

                    Item temp = mRealm.where(Item.class)
                            .findAll()
                            .where()
                            .equalTo("id", id)
                            .findFirst();

                    Category category = mRealm
                            .where(Category.class)
                            .findAll()
                            .where()
                            .equalTo("id", categorySpinner.getSelectedItemPosition()+1)
                            .findFirst();
                    category.getItems().add(temp);
                }
                mRealm.commitTransaction();
            }
        });

        if(getIntent().getBooleanExtra("toFill", false)) {
            recordButton.setEnabled(false);
            saveButton.setEnabled(true);

            List<String> colors = Arrays.asList((getResources().getStringArray(R.array.entryNameColor)));
            itemId = getIntent().getStringExtra("id");
            text.setText(getIntent().getStringExtra("name"));
            audioFileName.setText(getIntent().getStringExtra("audioFile"));
            cardColorSpinner.setSelection(getIndexFromName(getApplicationContext()
                    .getResources()
                    .getResourceEntryName(getIntent()
                        .getIntExtra("cardColor", 0)), colors));
            textColorSpinner.setSelection(getIndexFromName(getApplicationContext()
                    .getResources()
                    .getResourceEntryName(getIntent()
                        .getIntExtra("textColor", 0)), colors));
            int id = getResources().getIdentifier("colorArray", "array", getPackageName());

        }
    }


    private int getIndexFromName(String name, List<String> list) {
        // TODO proc nepouzit list.indexOf(name);
        for(int i = 0; i < list.size(); i++) {
            if(list.get(i).equals(name)){
                return i;
            }
        }

        return 0;
    }

    private void onRecord(boolean start) {
        if (start) {
            startRecording();
        } else {
            stopRecording();
        }
    }

    private void startRecording() {
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(audioFileName.getText().toString());
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mRecorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed" + e);
        }

        mRecorder.start();
    }

    private void stopRecording() {
        mRecorder.stop();
        mRecorder.release();
        saveButton.setEnabled(true);
        mRecorder = null;
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mRecorder != null) {
            mRecorder.release();
            mRecorder = null;
        }
    }
}
