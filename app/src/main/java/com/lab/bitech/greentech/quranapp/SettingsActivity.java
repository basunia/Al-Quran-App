package com.lab.bitech.greentech.quranapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
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

        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        /*Set up radio button*/
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
       switch (item.getItemId()) {
           case android.R.id.home:
               NavUtils.navigateUpFromSameTask(this);
        }

        return super.onOptionsItemSelected(item);
    }
}
