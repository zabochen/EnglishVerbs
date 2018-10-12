package ua.ck.zabochen.englishverbs.ui.verblist

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.arellomobile.mvp.presenter.InjectPresenter
import org.jetbrains.anko.AnkoLogger
import ua.ck.zabochen.englishverbs.R
import ua.ck.zabochen.englishverbs.database.entity.Verb
import ua.ck.zabochen.englishverbs.mvp.MvpAppCompatFragment
import ua.ck.zabochen.englishverbs.ui.verbfull.VerbFullActivity
import ua.ck.zabochen.englishverbs.utils.Constants
import ua.ck.zabochen.englishverbs.utils.listener.RecyclerViewItemTouchListener

class VerbListFragment : MvpAppCompatFragment(), VerbListView, AnkoLogger {

    @InjectPresenter
    lateinit var verbListPresenter: VerbListPresenter

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
        showProgressBar()

        // View is ready to show data
        verbListPresenter.viewIsReady()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //saveRecyclerViewState()
    }

    override fun showProgressBar() {
        progressBarHolder.bringToFront()
        progressBarHolder.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBarHolder.visibility = View.GONE
    }

    override fun setUi(verbList: ArrayList<Verb>) {
        hideProgressBar()
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
//        if (restoreRecyclerViewState() != null) {
//            verbListRecyclerView.layoutManager?.onRestoreInstanceState(restoreRecyclerViewState())
//        }
    }

    fun onClickVerbItem(id: Int) {
        val intentVerbFullActivity = Intent(activity, VerbFullActivity::class.java)
        intentVerbFullActivity.putExtra(Constants.INTENT_SELECTED_VERB_ID, id)
        startActivity(intentVerbFullActivity)
    }

//    private fun saveRecyclerViewState() {
//        getViewModel().recyclerViewState = verbListRecyclerView.layoutManager?.onSaveInstanceState()
//    }

//    private fun restoreRecyclerViewState(): Parcelable? {
//        return getViewModel().recyclerViewState
//    }

}