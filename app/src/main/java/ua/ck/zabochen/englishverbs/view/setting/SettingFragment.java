package ua.ck.zabochen.englishverbs.view.setting;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.util.Log;

import javax.inject.Inject;

import ua.ck.zabochen.englishverbs.MainApp;
import ua.ck.zabochen.englishverbs.R;
import ua.ck.zabochen.englishverbs.notification.NotificationHelper;

public class SettingFragment extends PreferenceFragmentCompat
        implements SettingContract.View, SharedPreferences.OnSharedPreferenceChangeListener {

    private static final String TAG = SettingFragment.class.getSimpleName();

    @Inject SettingPresenter settingPresenter;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.settings);

        // Dagger injection
        MainApp.getAppComponent().inject(this);

        // Bind with presenter
        settingPresenter.attachView(this);
        settingPresenter.viewIsReady();
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        Log.i(TAG, "onSharedPreferenceChanged: " + s);
        if (s.equals(getString(R.string.settings_listPreference_verbNotificationTime_key))) {
            settingPresenter.sharedPreferenceChanged();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

}
