package ua.ck.zabochen.englishverbs.ui.verbfull

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import org.jetbrains.anko.AnkoLogger
import ua.ck.zabochen.englishverbs.R
import ua.ck.zabochen.englishverbs.database.entity.Verb
import ua.ck.zabochen.englishverbs.utils.Constants
import ua.ck.zabochen.englishverbs.utils.Tools
import ua.ck.zabochen.englishverbs.utils.showToast

class VerbFullActivity : AppCompatActivity(), VerbFullView, AnkoLogger {

    @BindView(R.id.snippet_toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.activityVerbFull_textView_verbInfinitiveAndTranslate)
    lateinit var verbInfinitiveAndTranslate: TextView

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getIntentValues()
        addObservers()
    }

    override fun getViewModel(): VerbFullViewModel {
        return ViewModelProviders.of(this).get(VerbFullViewModel::class.java)
    }

    override fun addObservers() {
        verbStateObserver()
    }

    override fun verbStateObserver() {
        getViewModel().verbState.observe(this, Observer { verb ->
            setUi(verb)
        })
    }

    //
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun getIntentValues() {
        getViewModel().selectedVerbItem(intent.getIntExtra(Constants.INTENT_SELECTED_VERB_ID, 0))
    }

    private fun setUi(verb: Verb) {
        // Layout & ButterKnife
        setContentView(R.layout.activity_verb_full)
        ButterKnife.bind(this)

        // Toolbar
        toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_white_24)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        // Set data
        // VerbJson Infinitive and Translate
        val verbInfinitiveAndTranslateBuilder = StringBuilder()
        verbInfinitiveAndTranslateBuilder.append(verb.verbInfinitive)
        verbInfinitiveAndTranslateBuilder.append(" - ")
        verbInfinitiveAndTranslateBuilder.append(verb.verbTranslation)
        verbInfinitiveAndTranslate.text = verbInfinitiveAndTranslateBuilder.toString()

        // VerbJson Image
        verbImage.setImageBitmap(Tools.bitmapImageFromAssets(
                context = this,
                imagePath = verb.verbImage
        ))

        // VerbJson Infinitive
        verbInfinitive.text = verb.verbInfinitive
        verbInfinitiveTranscription.text = verb.verbInfinitiveTranscription

        // VerbJson Past Tense
        verbPastTense.text = verb.verbPastTense
        verbPastTenseTranscription.text = verb.verbPastTenseTranscription

        // VerbJson Past Participle
        verbPastParticiple.text = verb.verbPastParticiple
        verbPastParticipleTranscription.text = verb.verbPastParticipleTranscription

        // VerbJson Examples
        verbExample.text = verb.verbExample

    }

    @OnClick(R.id.activityVerbFull_imageView_bookmarkAddOrRemove)
    fun onClickBookmark() {
        showToast("onClick -> Bookmark")
    }

    @OnClick(R.id.activityVerbFull_verbInfinitiveGroup_verbInfinitivePlay,
            R.id.activityVerbFull_verbPastParticipleGroup_verbPastParticiplePlay,
            R.id.activityVerbFull_verbPastTenseGroup_verbPastTensePlay)
    fun onClickPlay() {
        showToast("onClick -> Play")
    }

}