
package com.orca.settings.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceGroup;
import android.preference.PreferenceScreen;

import com.orca.settings.ORCAPreferenceFragment;
import com.orca.settings.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class About extends ORCAPreferenceFragment {

    public static final String TAG = "About";

    Preference mSiteUrl;
    Preference mSourceUrl;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.title_about);
        addPreferencesFromResource(R.xml.prefs_about);
        mSiteUrl = findPreference("orca_website");
        mSourceUrl = findPreference("orca_source");

        PreferenceGroup devsGroup = (PreferenceGroup) findPreference("devs");
        ArrayList<Preference> devs = new ArrayList<Preference>();
        for (int i = 0; i < devsGroup.getPreferenceCount(); i++) {
            devs.add(devsGroup.getPreference(i));
        }
        devsGroup.removeAll();
        devsGroup.setOrderingAsAdded(false);
        Collections.shuffle(devs);
        for(int i = 0; i < devs.size(); i++) {
            Preference p = devs.get(i);
            p.setOrder(i);

            devsGroup.addPreference(p);
        }
    }

    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        if (preference == mSiteUrl) {
            launchUrl("http://drewgaren.com/");
        } else if (preference == mSourceUrl) {
            launchUrl("http://github.com/Bamtau");
        }
        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }

    private void launchUrl(String url) {
        Uri uriUrl = Uri.parse(url);
        Intent donate = new Intent(Intent.ACTION_VIEW, uriUrl);
        getActivity().startActivity(donate);
    }
}
