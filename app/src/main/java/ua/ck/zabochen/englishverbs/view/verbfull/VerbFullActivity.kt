package ua.ck.zabochen.englishverbs.view.verbfull

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.activity_verb_full.*
import org.jetbrains.anko.AnkoLogger
import ua.ck.zabochen.englishverbs.R
import ua.ck.zabochen.englishverbs.model.realm.Verb
import ua.ck.zabochen.englishverbs.utils.Constants
import ua.ck.zabochen.englishverbs.utils.Tools
import ua.ck.zabochen.englishverbs.view.base.BaseActivity

class VerbFullActivity : BaseActivity(),
        AnkoLogger, VerbFullView {

    @InjectPresenter lateinit var mVerbFullPresenter: VerbFullPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getIntentValues()
    }

    private fun getIntentValues() {
        loadVerb(intent.getIntExtra(Constants.INTENT_VERB_SELECTED_POSITION, 0))
    }

    private fun loadVerb(verbPosition: Int) {
        mVerbFullPresenter.loadVerb(verbPosition)
    }

    override fun setUi(verb: Verb?) {

        // Layout
        setContentView(R.layout.activity_verb_full)

        // Toolbar
        val toolbar = activityVerbFull_toolbar
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.toolbarTitle))
        setSupportActionBar(toolbar)

        if (verb != null) {

            // Verb Image
            val mVerbImage: ImageView = activityVerbFull_imageView_verbImage
            mVerbImage.setImageBitmap(Tools.bitmapImageFromAssets(this, verb.verbImage))

            // Verb Translation
            val mVerbTranslation: TextView = activityVerbFull_textView_verbTranslation
            mVerbTranslation.text = verb.verbTranslation

            // Verb Infinitive
            val mVerbInfinitive: TextView = activityVerbFull_textView_verb
            mVerbInfinitive.text = verb.verbInfinitive

            val mVerbInfinitiveTranscription: TextView = activityVerbFull_textView_verbTranscription
            mVerbInfinitiveTranscription.text = verb.verbInfinitiveTranscription

            val mVerbInfinitiveButtonPlay: ImageView = activityVerbFull_imageView_verbPlay
            mVerbInfinitiveButtonPlay.setOnClickListener {
                Toast.makeText(this, "Infinitive", Toast.LENGTH_LONG).show()
            }

            // Verb Past Tense
            val mVerbPastTense: TextView = activityVerbFull_textView_verbPastTense
            mVerbPastTense.text = verb.verbPastTense

            val mVerbPastTenseTranscription: TextView = activityVerbFull_textView_verbPastTenseTranscription
            mVerbPastTenseTranscription.text = verb.verbPastTenseTranscription

            val mVerbPastTenseButtonPlay: ImageView = activityVerbFull_imageView_verbPastTensePlay
            mVerbPastTenseButtonPlay.setOnClickListener {
                Toast.makeText(this, "Verb Past Tense", Toast.LENGTH_LONG).show()
            }

            // Verb Past Participle
            val mVerbPastParticiple: TextView = activityVerbFull_textView_verbPastParticiple
            mVerbPastParticiple.text = verb.verbPastParticiple

            val mVerbPastParticipleTranscription: TextView = activityVerbFull_textView_verbPastParticipleTranscription
            mVerbPastParticipleTranscription.text = verb.verbPastParticipleTranscription

            val mVerbPastParticipleButtonPlay: ImageView = activityVerbFull_imageView_verbPastParticiplePlay
            mVerbPastParticipleButtonPlay.setOnClickListener {
                Toast.makeText(this, "Verb Past Participle", Toast.LENGTH_LONG).show()
            }

        }
    }

}