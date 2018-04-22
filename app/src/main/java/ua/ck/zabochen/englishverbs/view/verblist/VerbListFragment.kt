package ua.ck.zabochen.englishverbs.view.verblist

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.mosby3.mvp.MvpFragment
import kotlinx.android.synthetic.main.fragment_verb_list.*
import org.jetbrains.anko.AnkoLogger
import ua.ck.zabochen.englishverbs.R
import ua.ck.zabochen.englishverbs.model.realm.Verb
import ua.ck.zabochen.englishverbs.utils.listener.RecyclerViewItemTouchListener

class VerbListFragment : MvpFragment<VerbListView, VerbListPresenter>(),
        VerbListView, AnkoLogger {

    override fun createPresenter() = VerbListPresenter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_verb_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.viewIsReady()
    }

    override fun setUi(verbList: ArrayList<Verb>) {
        // RecyclerView - VerbList
        fragmentVerbList_recyclerView.layoutManager = LinearLayoutManager(activity)
        fragmentVerbList_recyclerView.adapter = VerbListAdapter(verbList)
        fragmentVerbList_recyclerView.addOnItemTouchListener(RecyclerViewItemTouchListener(
                activity,
                fragmentVerbList_recyclerView,
                object : RecyclerViewItemTouchListener.ClickListener {
                    override fun onClick(view: View?, position: Int) {
                        presenter.onClickVerbItem(activity!!.applicationContext, position)
                    }

                    override fun onLongClick(view: View?, position: Int) {
                    }
                }
        ))
    }

}