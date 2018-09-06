package ua.ck.zabochen.englishverbs.ui.bookmark

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import ua.ck.zabochen.englishverbs.R
import ua.ck.zabochen.englishverbs.database.entity.Verb
import ua.ck.zabochen.englishverbs.ui.verbfull.VerbFullActivity
import ua.ck.zabochen.englishverbs.utils.Constants
import ua.ck.zabochen.englishverbs.utils.listener.RecyclerViewItemTouchListener

class BookmarkFragment : Fragment(), BookmarkView, AnkoLogger {

    @BindView(R.id.fragmentBookmark_recyclerView)
    lateinit var bookmarkRecyclerView: RecyclerView

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        addObservers()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Layout & ButterKnife
        val view: View = inflater.inflate(R.layout.fragment_bookmark, container, false)
        ButterKnife.bind(this, view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getViewModel().viewIsReady()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        info { "requestCode = $requestCode, resultCode = $resultCode" }

        if (resultCode == Constants.ACTIVITY_FOR_RESULT_ACTIVITY_DESTROY) {
            // TODO: Refresh view
        }
    }

    override fun getViewModel(): BookmarkViewModel {
        return ViewModelProviders.of(this).get(BookmarkViewModel::class.java)
    }

    override fun addObservers() {
        bookmarkVerbListObserver()
    }

    override fun bookmarkVerbListObserver() {
        getViewModel().bookmarkVerbList.observe(this, Observer<ArrayList<Verb>> {
            if (!it.isEmpty()) {
                setUi(it)
            }
        })
    }

    private fun setUi(verbList: ArrayList<Verb>) {
        // RecyclerView - BookmarkVerbList
        bookmarkRecyclerView.layoutManager = LinearLayoutManager(activity)
        bookmarkRecyclerView.adapter = BookmarkAdapter(verbList)
        bookmarkRecyclerView.addOnItemTouchListener(RecyclerViewItemTouchListener(
                activity,
                bookmarkRecyclerView,
                object : RecyclerViewItemTouchListener.ClickListener {
                    override fun onClick(view: View, position: Int) {
                        onClickVerbItem(verbList[position].id)

                        // TODO: LOG
                        info { "onClick ${verbList[position].verbInfinitive}" }
                    }

                    override fun onLongClick(view: View, position: Int) {
                    }
                }
        ))
    }

    private fun onClickVerbItem(verbId: Int) {
        val intentVerbFullActivity = Intent(activity, VerbFullActivity::class.java)
        intentVerbFullActivity.putExtra(Constants.INTENT_SELECTED_VERB_ID, verbId)
        startActivityForResult(intentVerbFullActivity, 88)
    }
}