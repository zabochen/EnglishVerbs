package ua.ck.zabochen.englishverbs.view.verbfull

import android.os.Bundle
import butterknife.ButterKnife
import com.arellomobile.mvp.MvpAppCompatActivity
import ua.ck.zabochen.englishverbs.R

class VerbFullActivity : MvpAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUi()
    }

    private fun setUi() {

        // Layout & ButterKnife
        setContentView(R.layout.activity_verb_full)
        ButterKnife.bind(this)
    }

}