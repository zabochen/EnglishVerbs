package ua.ck.zabochen.englishverbs.view.verblist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_adapter_verb_list.view.*
import ua.ck.zabochen.englishverbs.R
import ua.ck.zabochen.englishverbs.model.realm.Verb

class VerbListAdapter(verbList: ArrayList<Verb>) : RecyclerView.Adapter<VerbListAdapter.VerbListViewHolder>() {

    private var mVerbList: ArrayList<Verb> = verbList

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): VerbListViewHolder {
        return VerbListViewHolder(LayoutInflater.from(parent?.context)
                .inflate(R.layout.item_adapter_verb_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: VerbListViewHolder?, position: Int) {
        holder?.bind(mVerbList[position])
    }

    override fun getItemCount(): Int {
        return if (!mVerbList.isEmpty()) mVerbList.size else 0
    }

    class VerbListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(verb: Verb) {
            itemView.itemAdapterVerbList_verbName.text = verb.verb
            itemView.itemAdapterVerbList_verbTranscription.text = verb.verbTranscription
            if (!verb.verbExamples.isEmpty()) {
                itemView.itemAdapterVerbList_verbExample.text = verb.verbExamples[0]?.example
            }
        }
    }

}
