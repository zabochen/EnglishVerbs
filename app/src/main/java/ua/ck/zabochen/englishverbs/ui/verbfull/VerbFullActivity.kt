package ua.ck.zabochen.englishverbs.ui.verbfull

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.arellomobile.mvp.presenter.InjectPresenter
import org.jetbrains.anko.AnkoLogger
import ua.ck.zabochen.englishverbs.R
import ua.ck.zabochen.englishverbs.database.entity.Verb
import ua.ck.zabochen.englishverbs.mvp.MvpAppCompatActivity
import ua.ck.zabochen.englishverbs.utils.Constants
import ua.ck.zabochen.englishverbs.utils.Tools

class VerbFullActivity : MvpAppCompatActivity(), VerbFullView, AnkoLogger {

    @InjectPresenter
    lateinit var verbFullPresenter: VerbFullPresenter

    @BindView(R.id.snippet_toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.activityVerbFull_textView_verbInfinitiveAndTranslate)
    lateinit var verbInfinitiveAndTranslate: TextView

    @BindView(R.id.activityVerbFull_imageView_bookmarkAddOrRemove)
    lateinit var verbBookmark: ImageView

    @BindView(R.id.activityVerbFull_imageView_verbImage)
    lateinit var verbImage: ImageView

    @BindView(R.id.activityVerbFull_verbInfinitiveGroup_verbInfinitive)
    lateinit var verbInfinitive: TextView

    @BindView(R.id.activityVerbFull_verbInfinitiveGroup_verbInfinitiveTranscription)
    lateinit var verbInfinitiveTranscription: TextView

    @BindView(R.id.activityVerbFull_verbPastTenseGroup_verbPastTense)
    lateinit var verbPastTense: TextView

    @BindView(R.id.activityVerbFull_verbPastTenseGroup_verbPastTenseTranscription)
    lateinit var verbPastTenseTranscription: TextView

    @BindView(R.id.activityVerbFull_verbPastParticipleGroup_verbPastParticiple)
    lateinit var verbPastParticiple: TextView

    @BindView(R.id.activityVerbFull_verbPastParticipleGroup_verbPastParticipleTranscription)
    lateinit var verbPastParticipleTranscription: TextView

    @BindView(R.id.activityVerbFull_cardView_verbExamplesGroup_verbExample)
    lateinit var verbExample: TextView

    // Current verb
    private var verbInstance: Verb? = null

    // Verb id & bookmark state
    private var verbId: Int = 0
    private var verbBookmarkState: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getIntentValues()
    }

//    override fun bookmarkStateObserver() {
//        getViewModel().verbBookmarkState.observe(this, Observer {
//            setBookmarkView(it)
//            // Show toast
//            when (it) {
//                true -> showToast("Add to Bookmark")
//                false -> showToast("Remove from Bookmark")
//            }
//        })
//    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        setActivityForResultValues()
        finish()
    }

    private fun getIntentValues() {
        verbId = intent.getIntExtra(Constants.INTENT_SELECTED_VERB_ID, 0)
        verbFullPresenter.selectedVerbItem(verbId)
    }

    override fun setUi(verb: Verb) {

        this.verbInstance = verb

        // Layout & ButterKnife
        setContentView(R.layout.activity_verb_full)
        ButterKnife.bind(this)

        // Toolbar
        toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_white_24)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        // Set data
        // Verb Infinitive and Translate
        val verbInfinitiveAndTranslateBuilder = StringBuilder()
        verbInfinitiveAndTranslateBuilder.append(verb.verbInfinitive)
        verbInfinitiveAndTranslateBuilder.append(" - ")
        verbInfinitiveAndTranslateBuilder.append(verb.verbTranslation)
        verbInfinitiveAndTranslate.text = verbInfinitiveAndTranslateBuilder.toString()

        // Verb Image
        verbImage.setImageBitmap(Tools.bitmapImageFromAssets(context = this, imagePath = verb.verbImage))

        // Verb Infinitive
        verbInfinitive.text = verb.verbInfinitive
        verbInfinitiveTranscription.text = verb.verbInfinitiveTranscription

        // Verb Past Tense
        verbPastTense.text = verb.verbPastTense
        verbPastTenseTranscription.text = verb.verbPastTenseTranscription

        // Verb Past Participle
        verbPastParticiple.text = verb.verbPastParticiple
        verbPastParticipleTranscription.text = verb.verbPastParticipleTranscription

        // Verb Examples
        verbExample.text = verb.verbExample

        // Bookmark
        setBookmarkView(verb.bookmarkState)
    }

    private fun setActivityForResultValues() {
        val intent: Intent = Intent().putExtra(Constants.AFR_INTENT_KEY_VERB_FULL_ACTIVITY_BOOKMARK_STATE, verbBookmarkState)
        setResult(Constants.AFR_VERB_FULL_ACTIVITY_DESTROY, intent)
    }

    @OnClick(R.id.activityVerbFull_imageView_bookmarkAddOrRemove)
    fun onClickBookmark() {
        //getViewModel().setVerbBookmarkState(verbId)
    }

    @OnClick(R.id.activityVerbFull_verbInfinitiveGroup_verbInfinitivePlay,
            R.id.activityVerbFull_verbPastParticipleGroup_verbPastParticiplePlay,
            R.id.activityVerbFull_verbPastTenseGroup_verbPastTensePlay)
    fun onClickPlay(view: View) {
        when (view.id) {
//            R.id.activityVerbFull_verbInfinitiveGroup_verbInfinitivePlay -> getViewModel().speak(verbInstance!!.verbInfinitive)
//            R.id.activityVerbFull_verbPastParticipleGroup_verbPastParticiplePlay -> getViewModel().speak(verbInstance!!.verbPastParticiple)
//            R.id.activityVerbFull_verbPastTenseGroup_verbPastTensePlay -> getViewModel().speak(verbInstance!!.verbPastTense)
        }
    }

    private fun setBookmarkView(state: Boolean) {
        verbBookmarkState = when (state) {
            true -> {
                verbBookmark.setColorFilter(ContextCompat.getColor(this, R.color.bookmark_state_add))
                state
            }
            false -> {
                verbBookmark.setColorFilter(ContextCompat.getColor(this, R.color.bookmark_state_remove))
                state
            }
        }
    }
}