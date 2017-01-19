

package com.manoj.stepcounterinput;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.manoj.fitnessdemo.R;



public class Settings extends PreferenceActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        addPreferencesFromResource(R.xml.preferences);
    }
}
