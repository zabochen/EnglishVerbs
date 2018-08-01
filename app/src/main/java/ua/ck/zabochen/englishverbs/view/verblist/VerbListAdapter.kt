package ua.ck.zabochen.englishverbs.view.verblist

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import ua.ck.zabochen.englishverbs.R
import ua.ck.zabochen.englishverbs.model.realm.Verb
import ua.ck.zabochen.englishverbs.utils.Tools

class VerbListAdapter(verbList: ArrayList<Verb>) : RecyclerView.Adapter<VerbListAdapter.VerbListViewHolder>() {

    private var mVerbList: ArrayList<Verb> = verbList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerbListViewHolder {
        return VerbListViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.adapter_item_verb_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: VerbListViewHolder, position: Int) {
        holder.bind(mVerbList[position])
    }

    override fun getItemCount(): Int {
        return if (!mVerbList.isEmpty()) mVerbList.size else 0
    }

    class VerbListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val verbInfinitive: TextView by lazy { itemView.findViewById<TextView>(R.id.adapterItemVerbList_textView_verbInfinitive) }
        private val verbInfinitiveTranscription: TextView by lazy { itemView.findViewById<TextView>(R.id.adapterItemVerbList_textView_verbInfinitiveTranscription) }
        private val verbImage: ImageView by lazy { itemView.findViewById<ImageView>(R.id.adapterItemVerbList_imageView_verbImage) }
        private val verbExample: TextView by lazy { itemView.findViewById<TextView>(R.id.adapterItemVerbList_textView_verbExample) }

        fun bind(verb: Verb) {

            // Verb Infinitive
            verbInfinitive.text = verb.verbInfinitive + " - "

            // Verb Infinitive Transcription
            verbInfinitiveTranscription.text = verb.verbTranslation

            // Verb Image
            verbImage.setImageBitmap(Tools.bitmapImageFromAssets(itemView.context, verb.verbImage))

            // Verb Example
            if (!verb.verbExamples.isEmpty()) {
                verbExample.text = verb.verbExamples[0]?.example
            }
        }
    }

}
