package ua.ck.zabochen.englishverbs.ui.bookmark

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
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
import ua.ck.zabochen.englishverbs.utils.listener.RecyclerViewClickListener

class BookmarkFragment : Fragment(), BookmarkView, AnkoLogger {

    companion object {
        fun newInstance(): BookmarkFragment {
            return BookmarkFragment()
        }
    }

    @BindView(R.id.fragmentBookmark_recyclerView)
    lateinit var bookmarkRecyclerView: RecyclerView

    // Current state
    private var refreshState: Boolean = false
    private var selectedVerbPosition: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Layout & ButterKnife
        val view: View = inflater.inflate(R.layout.fragment_bookmark, container, false)
        ButterKnife.bind(this, view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addObservers()
        getViewModel().viewIsReady()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        saveRecyclerViewState()
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
                    getViewModel().refreshView()
                }
            }
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
            if (!it.isEmpty() && !refreshState) {
                setUi(it)
            } else if (!it.isEmpty() && refreshState) {
                setUi(it, refreshState)
            }
        })
    }

    private fun setUi(bookmarkVerbList: ArrayList<Verb>, refreshState: Boolean = false) {

        if (bookmarkRecyclerView.adapter == null) {
            bookmarkRecyclerView.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            info { "Init recyclerView" }
            bookmarkRecyclerView.setHasFixedSize(true)
        }

        bookmarkRecyclerView.adapter = BookmarkAdapter(object : RecyclerViewClickListener {
            override fun onClick(view: View, position: Int) {
                selectedVerbPosition = position
                onClickVerbItem(bookmarkVerbList[position].id)
            }

            override fun onLongClick(view: View, position: Int) {
            }
        })


        when (refreshState) {
            true -> {
                info { "refreshState => true" }
                val bookmarkAdapter = bookmarkRecyclerView.adapter as BookmarkAdapter
                bookmarkAdapter.setData(bookmarkVerbList)
                bookmarkRecyclerView.adapter?.notifyItemRemoved(selectedVerbPosition)
                //bookmarkRecyclerView.adapter?.notifyItemRangeChanged(0, bookmarkVerbList.size-1)
//                bookmarkAdapter.notifyDataSetChanged()
            }
            false -> {
                info { "refreshState => false" }
                val bookmarkAdapter = bookmarkRecyclerView.adapter as BookmarkAdapter
                bookmarkAdapter.setData(bookmarkVerbList)
                bookmarkAdapter.notifyDataSetChanged()
            }
        }

//        val newVerbList: ArrayList<Verb> = bookmarkVerbList
//
//        info { "SIZE: ${newVerbList.size}" }
//
//        val list: StringBuilder = StringBuilder()
//        newVerbList.forEach {
//            list.append(it.verbInfinitive)
//            list.append(", ")
//        }
//        info { "LIST: $list \n" }
//
//        // RecyclerView - BookmarkVerbList
//        bookmarkRecyclerView.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
//        //bookmarkRecyclerView.layoutManager?.onRestoreInstanceState(restoreRecyclerViewState())
//
//        val bookmarkAdapter = BookmarkAdapter()
//        bookmarkAdapter.setData(newVerbList)
//        //bookmarkAdapter.notifyDataSetChanged()
//
//        bookmarkRecyclerView.adapter = bookmarkAdapter
//        bookmarkRecyclerView.adapter?.notifyDataSetChanged()
//
//
//        bookmarkRecyclerView.addOnItemTouchListener(RecyclerViewItemTouchListener(
//                activity,
//                bookmarkRecyclerView,
//                object : RecyclerViewItemTouchListener.ClickListener {
//                    override fun onClick(view: View, position: Int) {
//                        selectedVerbPosition = position
//
//                        val size = newVerbList.size
//
//                        info { "FACK!!!! => S: $size, P: $position, L: ${newVerbList[position].verbInfinitive}" }
//
//
//                        onClickVerbItem(newVerbList[position].id)
//                    }
//
//                    override fun onLongClick(view: View, position: Int) {
//                        activity?.showToast(bookmarkVerbList[position].verbInfinitive)
//                    }
//                }
//        ))

//        if (!refreshState) {
//            bookmarkAdapter.setData(bookmarkVerbList)
//            bookmarkAdapter.notifyDataSetChanged()
//            //bookmarkRecyclerView.adapter = bookmarkAdapter
//
//        } else if (refreshState) {
//            bookmarkAdapter.setData(bookmarkVerbList)
//            bookmarkAdapter.notifyItemRemoved(selectedVerbPosition)
//        }
    }

    private fun onClickVerbItem(verbId: Int) {
        val intentVerbFullActivity = Intent(activity, VerbFullActivity::class.java)
        intentVerbFullActivity.putExtra(Constants.INTENT_SELECTED_VERB_ID, verbId)
        startActivityForResult(intentVerbFullActivity, 88)
    }

    private fun saveRecyclerViewState() {
        getViewModel().recyclerViewState = bookmarkRecyclerView.layoutManager?.onSaveInstanceState()
    }

    private fun restoreRecyclerViewState(): Parcelable? {
        return getViewModel().recyclerViewState
    }
}