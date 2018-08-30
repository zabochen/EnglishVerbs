package ua.ck.zabochen.englishverbs.ui.verbfull

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import butterknife.ButterKnife
import org.jetbrains.anko.AnkoLogger
import ua.ck.zabochen.englishverbs.R
import ua.ck.zabochen.englishverbs.model.realm.Verb
import ua.ck.zabochen.englishverbs.utils.Constants

class VerbFullActivity : AppCompatActivity(), VerbFullView, AnkoLogger {

    private val mToolbar: Toolbar by lazy { findViewById<Toolbar>(R.id.snippet_toolbar) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getIntentValues()
        addObservers()
    }

    override fun getViewModel(): VerbFullViewModel {
        return ViewModelProviders.of(this).get(VerbFullViewModel::class.java)
    }

    override fun addObservers() {
        verbState()
    }

    override fun verbState() {
        getViewModel().verbState.observe(this, Observer { verb ->
            setUi(verb)
        })
    }

    private fun getIntentValues() {
        getViewModel().selectedVerbItem(intent.getIntExtra(Constants.INTENT_VERB_SELECTED_POSITION, 0))
    }

    private fun setUi(verb: Verb) {
        // Layout & ButterKnife
        setContentView(R.layout.activity_verb_full)
        ButterKnife.bind(this)

        // Toolbar
        setSupportActionBar(mToolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

    }

}