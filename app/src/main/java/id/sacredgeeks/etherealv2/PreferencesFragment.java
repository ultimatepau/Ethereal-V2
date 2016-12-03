package id.sacredgeeks.etherealv2;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by SacredGeeks on 12/3/2016.
 */

public class PreferencesFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);
    }
}
