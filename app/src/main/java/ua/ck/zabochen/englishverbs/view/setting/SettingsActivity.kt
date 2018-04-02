package ua.ck.zabochen.englishverbs.view.setting

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import kotlinx.android.synthetic.main.activity_settings.*
import ua.ck.zabochen.englishverbs.R

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUi()
    }

    private fun setUi() {
        // Layout
        setContentView(R.layout.activity_settings)

        // Toolbar
        val toolbar: Toolbar = activitySettings_toolbar
        toolbar.setTitle(R.string.settingsActivity_toolbar_title)
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.toolbarTitle))
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Settings Fragment
        setFragment()
    }

    private fun setFragment() {
        supportFragmentManager.beginTransaction()
                .replace(activitySettings_frameLayout.id, SettingsFragment())
                .commit()
    }

}