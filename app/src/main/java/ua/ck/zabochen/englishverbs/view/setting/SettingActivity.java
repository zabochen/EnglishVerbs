package ua.ck.zabochen.englishverbs.view.setting;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import ua.ck.zabochen.englishverbs.R;
import ua.ck.zabochen.englishverbs.view.base.BaseActivity;

public class SettingActivity extends BaseActivity {

    @BindView(R.id.activityPreference_toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUi();
        setFragment();
    }

    private void setUi() {
        // Layout
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);

        // Toolbar
        mToolbar.setTitle(R.string.settingActivity_title);
        mToolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.toolbar_title));
    }

    private void setFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activityPreference_frameLayout, new SettingFragment())
                .commit();
    }
}
