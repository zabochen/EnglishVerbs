package ua.ck.zabochen.englishverbs.ui.verblist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import ua.ck.zabochen.englishverbs.R
import ua.ck.zabochen.englishverbs.database.entity.Verb
import ua.ck.zabochen.englishverbs.utils.Tools

class VerbListAdapter(private val verbList: ArrayList<Verb>) : RecyclerView.Adapter<VerbListAdapter.VerbListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerbListViewHolder {
        return VerbListViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.adapter_item_verb_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: VerbListViewHolder, position: Int) {
        holder.bind(verbList[position])
    }

    override fun getItemCount(): Int {
        return if (!verbList.isEmpty()) verbList.size else 0
    }

    class VerbListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            ButterKnife.bind(this, itemView)
        }

        @BindView(R.id.adapterItemVerbList_textView_verbInfinitiveAndTranslation)
        lateinit var verbInfinitiveAndTranslation: TextView

        @BindView(R.id.adapterItemVerbList_imageView_verbImage)
        lateinit var verbImage: ImageView

        @BindView(R.id.adapterItemVerbList_textView_verbExample)
        lateinit var verbExample: TextView

        fun bind(verb: Verb) {
            // Verb Infinitive & Translation
            val verbAndTranslationBuilder = StringBuilder()
            verbAndTranslationBuilder.append(verb.verbInfinitive)
            verbAndTranslationBuilder.append(" - ")
            verbAndTranslationBuilder.append(verb.verbTranslation)
            verbInfinitiveAndTranslation.text = verbAndTranslationBuilder.toString()

            // Verb Image
            verbImage.setImageBitmap(Tools.bitmapImageFromAssets(itemView.context, verb.verbImage))

            // Verb Example
            verbExample.text = verb.verbExample
        }
    }

}
