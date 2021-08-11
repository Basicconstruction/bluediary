package com.luckyxmoible.mymemo;

import android.app.AppComponentFactory;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;

public class PreferencesActivity extends AppCompatActivity {
    public static final String PREF_AUTO_THEME = "PREF_AUTO_THEME";
    public static final String PREF_PASSKEY_LEN = "PREF_PASSKEY_LEN";
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preferences);
    }
    public static class PrefFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState,String rootKey){
            setPreferencesFromResource(R.xml.userprefereneces,null);
        }
    }
}
