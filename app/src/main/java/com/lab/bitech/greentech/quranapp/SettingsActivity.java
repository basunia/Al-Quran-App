package com.lab.bitech.greentech.quranapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ToggleButton;

import com.lab.bitech.greentech.quranapp.utils.Commons;
import com.pixplicity.easyprefs.library.Prefs;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        radioGroup.check(Prefs.getInt(Commons.LANGUAGES_ID, 1));
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                Log.d("RadioButton", "Radio button: " + i);
                RadioButton rb = (RadioButton) radioGroup.findViewById(i);
                Prefs.putInt(Commons.LANGUAGES_ID, i);
                Prefs.putString(Commons.LANGUAGES, String.valueOf(rb.getText()));
            }
        });
    }

    /*public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_en:
                if (checked)
                    Prefs.putBoolean(Commons.TOGGLE_STATUS, true);
                    break;
            case R.id.radio_bn:
                if (checked)
                    Prefs.putBoolean(Commons.TOGGLE_STATUS, false);
                    break;
        }
    }*/
}
