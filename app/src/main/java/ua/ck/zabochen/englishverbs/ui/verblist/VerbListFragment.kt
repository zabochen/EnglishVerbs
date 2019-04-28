package ua.ck.zabochen.englishverbs.ui.verblist

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

class VerbListFragment : BaseFragment(), AnkoLogger {

    companion object {
        fun newInstance() = VerbListFragment()
    }

    private lateinit var unbinder: Unbinder

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Layout & ButterKnife
        return inflater.inflate(R.layout.fragment_verb_list, container, false).also {
            unbinder = ButterKnife.bind(this, it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //saveRecyclerViewState()
    }


    private fun setUi(verbList: ArrayList<Verb>) {
//        verbListRecyclerView.layoutManager = LinearLayoutManager(activity)
//        verbListRecyclerView.adapter = VerbListAdapter(verbList)
//        verbListRecyclerView.addOnItemTouchListener(RecyclerViewItemTouchListener(
//                context = activity,
//                recyclerView = verbListRecyclerView,
//                clickListener = object : RecyclerViewItemTouchListener.ClickListener {
//                    override fun onClick(view: View, position: Int) {
//                        onClickVerbItem(verbList[position].id)
//                    }
//
//                    override fun onLongClick(view: View, position: Int) {
//                    }
//                }
//        ))

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