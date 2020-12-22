package com.kylix.submissionbajp3.ui.setting

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.kylix.submissionbajp3.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}