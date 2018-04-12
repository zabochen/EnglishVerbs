package ua.ck.zabochen.englishverbs.view.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.mosby3.mvp.MvpFragment
import ua.ck.zabochen.englishverbs.R

class BookmarkFragment : MvpFragment<BookmarkView, BookmarkPresenter>(),
        BookmarkView {

    override fun createPresenter() = BookmarkPresenter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bookmark, container, false)
    }

}