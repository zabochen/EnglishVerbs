package ua.ck.zabochen.englishverbs.ui.bookmark

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import butterknife.Unbinder
import org.jetbrains.anko.AnkoLogger
import ua.ck.zabochen.englishverbs.R
import ua.ck.zabochen.englishverbs.database.entity.Verb
import ua.ck.zabochen.englishverbs.ui.base.BaseFragment
import ua.ck.zabochen.englishverbs.ui.verbfull.VerbFullActivity
import ua.ck.zabochen.englishverbs.utils.Constants

class BookmarkFragment : BaseFragment(), AnkoLogger {

    companion object {
        fun newInstance() = BookmarkFragment()
    }

    // Views
    lateinit var unbinder: Unbinder
    private val bookmarkAdapter: BookmarkAdapter by lazy { BookmarkAdapter() }

    // Current state
    private var refreshState: Boolean = false
    private var selectedVerbPosition: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Layout & ButterKnife
        return inflater.inflate(R.layout.fragment_bookmark, container, false).also {
            unbinder = ButterKnife.bind(this, it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // TODO: viewIsReady()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // saveRecyclerViewState()
        unbinder.unbind()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Constants.AFR_VERB_FULL_ACTIVITY_DESTROY) {
            if (data != null) {
                val verbBookmarkState: Boolean = data.getBooleanExtra(
                        Constants.AFR_INTENT_KEY_VERB_FULL_ACTIVITY_BOOKMARK_STATE,
                        true)
                if (!verbBookmarkState) {
                    refreshState = true
                    // TODO: refreshView
                }
            }
        }
    }

//    override fun bookmarkVerbListObserver() {
//
//            if (!it.isEmpty() && !refreshState) {
//                setUi(it)
//            } else if (refreshState) {
//                setUi(it, refreshState)
//            }
//    }

    private fun setUi(bookmarkVerbList: ArrayList<Verb>, refreshState: Boolean = false) {
//        if (bookmarkRecyclerView.adapter == null) {
//            // Layout Manager
//            bookmarkRecyclerView.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
//            // Adapter
//            bookmarkRecyclerView.adapter = bookmarkAdapter
//            // Click Listener
//            bookmarkRecyclerView.addOnItemTouchListener(RecyclerViewItemTouchListener(
//                    context = activity,
//                    recyclerView = bookmarkRecyclerView,
//                    clickListener = object : RecyclerViewItemTouchListener.ClickListener {
//                        override fun onClick(view: View, position: Int) {
//                            selectedVerbPosition = position
//                            onClickVerbItem(bookmarkAdapter.getData()[position].id)
//                        }
//
//                        override fun onLongClick(view: View, position: Int) {
//                        }
//                    }
//            ))
//        }
//
        // Restore RecyclerView state
//        if (restoreRecyclerViewState() != null) {
//            bookmarkRecyclerView.layoutManager?.onRestoreInstanceState(restoreRecyclerViewState())
//        }

        when (refreshState) {
            true -> {
                bookmarkAdapter.setData(bookmarkVerbList)
                bookmarkAdapter.notifyItemRemoved(selectedVerbPosition)
            }
            false -> {
                bookmarkAdapter.setData(bookmarkVerbList)
                bookmarkAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun onClickVerbItem(verbId: Int) {
        val intentVerbFullActivity = Intent(activity, VerbFullActivity::class.java)
        intentVerbFullActivity.putExtra(Constants.INTENT_SELECTED_VERB_ID, verbId)
        startActivityForResult(intentVerbFullActivity, 88)
    }

//    private fun saveRecyclerViewState() {
//        getViewModel().recyclerViewState = bookmarkRecyclerView.layoutManager?.onSaveInstanceState()
//    }
//
//    private fun restoreRecyclerViewState(): Parcelable? {
//        return getViewModel().recyclerViewState
//    }
}