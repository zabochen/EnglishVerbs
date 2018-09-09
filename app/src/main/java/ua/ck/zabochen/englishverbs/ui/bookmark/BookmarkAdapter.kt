package ua.ck.zabochen.englishverbs.ui.bookmark

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
import ua.ck.zabochen.englishverbs.utils.listener.RecyclerViewClickListener

class BookmarkAdapter(
        private val recyclerViewClickListener: RecyclerViewClickListener
) : RecyclerView.Adapter<BookmarkAdapter.BookmarkViewHolder>() {

    private val bookmarkVerbList: ArrayList<Verb> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder {
        return BookmarkViewHolder(
                itemView = LayoutInflater.from(parent.context)
                        .inflate(R.layout.adapter_item_bookmark, parent, false),
                recyclerViewClickListener = recyclerViewClickListener
        )
    }

    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) {
        holder.bind(bookmarkVerbList[position])
    }

    override fun getItemCount(): Int {
        return if (!bookmarkVerbList.isEmpty()) bookmarkVerbList.size else 0
    }

    fun setData(bookmarkVerbList: ArrayList<Verb>) {
        this.bookmarkVerbList.clear()
        this.bookmarkVerbList.addAll(bookmarkVerbList)
    }

    inner class BookmarkViewHolder(
            itemView: View,
            private val recyclerViewClickListener: RecyclerViewClickListener
    ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        init {
            // ClickListener
            itemView.setOnClickListener(this)
            // ButterKnife
            ButterKnife.bind(this, itemView)
        }

        @BindView(R.id.adapterItemBookmark_textView_verbInfinitiveAndTranslation)
        lateinit var verbInfinitiveAndTranslation: TextView

        @BindView(R.id.adapterItemBookmark_imageView_verbImage)
        lateinit var verbImage: ImageView

        @BindView(R.id.adapterItemBookmark_textView_verbExample)
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

        override fun onClick(view: View?) {
            if (view != null) {
                recyclerViewClickListener.onClick(view, adapterPosition)
            }
        }
    }
}
