package ua.ck.zabochen.englishverbs.ui.verblist

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import org.jetbrains.anko.AnkoLogger
import ua.ck.zabochen.englishverbs.R
import ua.ck.zabochen.englishverbs.database.entity.Verb
import ua.ck.zabochen.englishverbs.ui.verbfull.VerbFullActivity
import ua.ck.zabochen.englishverbs.utils.Constants
import ua.ck.zabochen.englishverbs.utils.listener.RecyclerViewItemTouchListener

class VerbListFragment : Fragment(), VerbListView, AnkoLogger {

    @BindView(R.id.snippetProgressBar_frameLayout_progressBarHolder)
    lateinit var progressBarHolder: FrameLayout

    @BindView(R.id.fragmentVerbList_recyclerView)
    lateinit var verbListRecyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Layout & ButterKnife
        val view: View = inflater.inflate(R.layout.fragment_verb_list, container, false)
        ButterKnife.bind(this, view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addObservers()
        // Show progressBar
        showProgressBar(true)
        // View is ready to show data
        getViewModel().viewIsReady()
    }

    override fun onDestroyView() {
        saveRecyclerViewState()
        super.onDestroyView()
    }

    override fun getViewModel(): VerbListViewModel {
        return ViewModelProviders.of(activity!!).get(VerbListViewModel::class.java)
    }

    override fun addObservers() {
        verbListState()
    }

    override fun verbListState() {
        getViewModel().verbListState.observe(this, Observer {
            if (!it.isEmpty()) {
                setUi(it)
                // Hide progressBar
                showProgressBar(false)
            }
        })
    }

    private fun setUi(verbList: ArrayList<Verb>) {
        verbListRecyclerView.layoutManager = LinearLayoutManager(activity)
        verbListRecyclerView.adapter = VerbListAdapter(verbList)
        verbListRecyclerView.addOnItemTouchListener(RecyclerViewItemTouchListener(
                context = activity,
                recyclerView = verbListRecyclerView,
                clickListener = object : RecyclerViewItemTouchListener.ClickListener {
                    override fun onClick(view: View, position: Int) {
                        onClickVerbItem(verbList[position].id)
                    }

                    override fun onLongClick(view: View, position: Int) {
                    }
                }
        ))

        // Restore RecyclerView state
        if (restoreRecyclerViewState() != null) {
            verbListRecyclerView.layoutManager?.onRestoreInstanceState(restoreRecyclerViewState())
        }
    }

    fun onClickVerbItem(id: Int) {
        val intentVerbFullActivity = Intent(activity, VerbFullActivity::class.java)
        intentVerbFullActivity.putExtra(Constants.INTENT_SELECTED_VERB_ID, id)
        startActivity(intentVerbFullActivity)
    }

    private fun saveRecyclerViewState() {
        getViewModel().recyclerViewState = verbListRecyclerView.layoutManager?.onSaveInstanceState()
    }

    private fun restoreRecyclerViewState(): Parcelable? {
        return getViewModel().recyclerViewState
    }

    private fun showProgressBar(progressBarState: Boolean) {
        when (progressBarState) {
            true -> {
                progressBarHolder.bringToFront()
                progressBarHolder.visibility = View.VISIBLE
            }
            false -> progressBarHolder.visibility = View.GONE
        }
    }
}