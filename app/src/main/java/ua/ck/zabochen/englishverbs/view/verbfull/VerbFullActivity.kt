package ua.ck.zabochen.englishverbs.view.verbfull


import android.os.Bundle
import android.support.v7.widget.Toolbar
import com.hannesdorfmann.mosby3.mvp.MvpActivity
import org.jetbrains.anko.AnkoLogger
import ua.ck.zabochen.englishverbs.R
import ua.ck.zabochen.englishverbs.model.realm.Verb
import ua.ck.zabochen.englishverbs.utils.Constants

class VerbFullActivity : MvpActivity<VerbFullView, VerbFullPresenter>(),
        VerbFullView, AnkoLogger {

    override fun createPresenter() = VerbFullPresenter()

    private val mToolbar: Toolbar by lazy { findViewById<Toolbar>(R.id.snippet_toolbar) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getIntentValues()
        setUi(null)
    }

    private fun getIntentValues() {
        loadVerb(intent.getIntExtra(Constants.INTENT_VERB_SELECTED_POSITION, 0))
    }

    private fun loadVerb(verbPosition: Int) {

    }

    override fun setUi(verb: Verb?) {
        // Layout
        setContentView(R.layout.activity_verb_full)

        // Toolbar
        setSupportActionBar(mToolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

}