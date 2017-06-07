package cz.muni.fi.pv239.porenut.activities;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

import cz.muni.fi.pv239.porenut.R;

public class TextToSpeechActivity extends AppCompatActivity {
    TextToSpeech t1;
    Button b1;
    EditText et1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_to_speech);
        b1 = (Button)findViewById(R.id.textToSpeechButton);
        et1 = (EditText)findViewById(R.id.textToSpeechEditText);

        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                t1.setLanguage(Locale.getDefault());
            }
        });


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(t1.isSpeaking()){
                    if(t1 != null) t1.stop();
                } else {
                    String toSpeak = et1.getText().toString();
                    Toast.makeText(getApplicationContext(), toSpeak,Toast.LENGTH_SHORT).show();
                    t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                    et1.setText("");
                }

            }
        });
    }
}
