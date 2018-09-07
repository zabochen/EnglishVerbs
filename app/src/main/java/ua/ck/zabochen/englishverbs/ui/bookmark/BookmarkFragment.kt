package ua.ck.zabochen.englishverbs.ui.bookmark

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
import org.jetbrains.anko.AnkoLogger
import ua.ck.zabochen.englishverbs.R
import ua.ck.zabochen.englishverbs.database.entity.Verb
import ua.ck.zabochen.englishverbs.ui.verbfull.VerbFullActivity
import ua.ck.zabochen.englishverbs.utils.Constants
import ua.ck.zabochen.englishverbs.utils.binding.bindView
import ua.ck.zabochen.englishverbs.utils.listener.RecyclerViewItemTouchListener

class BookmarkFragment : Fragment(), BookmarkView, AnkoLogger {

    private lateinit var rootView: View

    private val bookmarkRecyclerView: RecyclerView by bindView(rootView, R.id.fragmentBookmark_recyclerView)

    private lateinit var bookmarkAdapter: BookmarkAdapter

    // Current state
    private var bookmarkVerbListSize: Int = 0
    private var selectedVerbPosition: Int = 0
    private var selectedVerbId: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Layout & BindView
        rootView = inflater.inflate(R.layout.fragment_bookmark, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addObservers()
        getViewModel().viewIsReady()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Constants.AFR_VERB_FULL_ACTIVITY_DESTROY) {
            getViewModel().refreshView(selectedVerbId)
        }
    }

    override fun getViewModel(): BookmarkViewModel {
        return ViewModelProviders.of(this).get(BookmarkViewModel::class.java)
    }

    override fun addObservers() {
        bookmarkVerbListObserver()
        removeVerbObserver()
    }

    override fun bookmarkVerbListObserver() {
        getViewModel().bookmarkVerbList.observe(this, Observer<ArrayList<Verb>> {
            if (!it.isEmpty()) {
                setUi(it)
            }
        })
    }

    override fun removeVerbObserver() {
        getViewModel().bookmarkVerbRemove.observe(this, Observer<Boolean> {
            if (it) {
                bookmarkAdapter.notifyItemRemoved(selectedVerbPosition)
                bookmarkAdapter.notifyItemRangeChanged(selectedVerbPosition, bookmarkVerbListSize - 1)
            }
        })
    }

    private fun setUi(bookmarkVerbList: ArrayList<Verb>) {
        // RecyclerView - BookmarkVerbList
        bookmarkRecyclerView.layoutManager = LinearLayoutManager(activity)
        bookmarkAdapter = BookmarkAdapter(bookmarkVerbList)
        bookmarkRecyclerView.adapter = bookmarkAdapter
        bookmarkRecyclerView.addOnItemTouchListener(RecyclerViewItemTouchListener(
                activity,
                bookmarkRecyclerView,
                object : RecyclerViewItemTouchListener.ClickListener {
                    override fun onClick(view: View, position: Int) {
                        // Save current state
                        bookmarkVerbListSize = bookmarkVerbList.size
                        selectedVerbPosition = position
                        selectedVerbId = bookmarkVerbList[position].id

                        // Show VerbFull Activity
                        onClickVerbItem(selectedVerbId)
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