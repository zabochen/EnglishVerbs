package ua.ck.zabochen.englishverbs.ui.verblist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import org.jetbrains.anko.AnkoLogger
import ua.ck.zabochen.englishverbs.R
import ua.ck.zabochen.englishverbs.model.realm.Verb
import ua.ck.zabochen.englishverbs.utils.listener.RecyclerViewItemTouchListener

class VerbListFragment : Fragment(), AnkoLogger {

    @BindView(R.id.fragmentVerbList_recyclerView)
    lateinit var verbListRecyclerView: RecyclerView

    @BindView(R.id.fragmentVerbList_progressBar)
    lateinit var verbListProgressBar: ProgressBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Layout & ButterKnife
        val view: View = inflater.inflate(R.layout.fragment_verb_list, container, false)
        ButterKnife.bind(this, view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Get VerbListViewModel
        val verbListViewModel = ViewModelProviders.of(this).get(VerbListViewModel::class.java)
        subscription(verbListViewModel)

        verbListViewModel.getVerbList()?.observe(this, Observer {
            setUi(it)
        })
    }

    private fun subscription(viewModel: VerbListViewModel) {
        // Progress Listener
        progressListener(viewModel)
    }

    private fun setUi(verbList: ArrayList<Verb>) {
        // RecyclerView - VerbList
        verbListRecyclerView.layoutManager = LinearLayoutManager(activity)
        verbListRecyclerView.adapter = VerbListAdapter(verbList)
        verbListRecyclerView.addOnItemTouchListener(RecyclerViewItemTouchListener(
                activity!!,
                verbListRecyclerView,
                object : RecyclerViewItemTouchListener.ClickListener {
                    override fun onClick(view: View, position: Int) {
                    }

                    override fun onLongClick(view: View, position: Int) {
                    }
                }
        ))
    }

    private fun progressListener(viewModel: VerbListViewModel) {
        viewModel.progress.observe(this, Observer {
            when (it) {
                true -> verbListProgressBar.visibility = View.VISIBLE
                false -> verbListProgressBar.visibility = View.GONE
            }
        })
    }
}