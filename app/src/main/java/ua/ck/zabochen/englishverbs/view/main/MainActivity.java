package ua.ck.zabochen.englishverbs.view.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ua.ck.zabochen.englishverbs.MainApp;
import ua.ck.zabochen.englishverbs.R;
import ua.ck.zabochen.englishverbs.view.base.BaseActivity;
import ua.ck.zabochen.englishverbs.view.setting.SettingActivity;
import ua.ck.zabochen.englishverbs.view.verb.VerbFragment;

public class MainActivity extends BaseActivity {

    @Inject
    VerbFragment mVerbFragment;

    @BindView(R.id.activityMain_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.activityMain_frameLayout)
    FrameLayout mFrameLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainApp.getAppComponent().inject(this);
        setUi();
        setFragment();
    }

    private void setUi() {
        // Layout
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // Toolbar
        setSupportActionBar(mToolbar);
        mToolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.toolbar_title));
    }

    private void setFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(mFrameLayout.getId(), mVerbFragment)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuMain_itemSettings:
                startActivity(new Intent(this, SettingActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
