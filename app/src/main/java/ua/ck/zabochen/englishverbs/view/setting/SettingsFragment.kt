package ua.ck.zabochen.englishverbs.view.setting

import android.os.Bundle
import android.support.v7.preference.PreferenceFragmentCompat
import ua.ck.zabochen.englishverbs.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.settings)
    }

}