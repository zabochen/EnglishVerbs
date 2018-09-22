package ua.ck.zabochen.englishverbs.ui.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.RelativeLayout
import android.widget.Switch
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Unbinder
import org.jetbrains.anko.AnkoLogger
import ua.ck.zabochen.englishverbs.R
import ua.ck.zabochen.englishverbs.database.entity.Setting

class SettingFragment : Fragment(), SettingView, AnkoLogger {

    @BindView(R.id.snippetProgressBar_frameLayout_progressBarHolder)
    lateinit var progressBarHolder: FrameLayout

    @BindView(R.id.fragmentSettings_relativeLayout_notificationStateGroup)
    lateinit var notificationStateGroup: RelativeLayout

    @BindView(R.id.fragmentSettings_switch_notificationState)
    lateinit var notificationStateSwitch: Switch

    @BindView(R.id.fragmentSettings_relativeLayout_notificationAllWordsGroup)
    lateinit var notificationAllWordsGroup: RelativeLayout

    @BindView(R.id.fragmentSettings_switch_notificationAllWords)
    lateinit var notificationAllWordsSwitch: Switch

    @BindView(R.id.fragmentSettings_relativeLayout_notificationBookmarkWordsGroup)
    lateinit var notificationBookmarkWordsGroup: RelativeLayout

    @BindView(R.id.fragmentSettings_switch_notificationBookmarkWords)
    lateinit var notificationBookmarkWordsSwitch: Switch

    private lateinit var unbinder: Unbinder

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Layout & ButterKnife
        val view: View = inflater.inflate(R.layout.fragment_settings, container, false)
        unbinder = ButterKnife.bind(this, view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showProgressBar(true)
        getViewModel().viewIsReady()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        unbinder.unbind()
    }

    override fun getViewModel(): SettingViewModel {
        return ViewModelProviders.of(activity!!).get(SettingViewModel::class.java)
    }

    override fun addObservers() {
        settingStateObserver()
    }

    override fun settingStateObserver() {
        getViewModel().settingsState.observe(this, Observer {
            // TODO: settingStateObserver()
        })
    }

    private fun showProgressBar(state: Boolean) {
        when (state) {
            true -> {
                progressBarHolder.bringToFront()
                progressBarHolder.visibility = View.VISIBLE
            }
            false -> {
                progressBarHolder.visibility = View.GONE
            }
        }
    }

    private fun setUi(settings: Setting) {
        // Setting
        notificationStateSwitch.isChecked = settings.notificationState

        // Setting All Words
        notificationAllWordsSwitch.isChecked = settings.notificationAllWordsState

        // Setting Bookmark
        notificationBookmarkWordsSwitch.isChecked = settings.notificationBookmarksWordsState
    }

    @OnClick(R.id.fragmentSettings_relativeLayout_notificationStateGroup,
            R.id.fragmentSettings_switch_notificationState)
    fun onClickNotificationState(view: View) {
        when (view.id) {
            notificationStateGroup.id -> {
                notificationStateSwitch.isChecked = !notificationStateSwitch.isChecked
            }
            notificationStateSwitch.id -> {

            }
        }
    }

}