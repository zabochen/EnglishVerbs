package ua.ck.zabochen.englishverbs.view.base

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity

open class BaseActivity : MvpAppCompatActivity(),
        BaseView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

}