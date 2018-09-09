package ua.ck.zabochen.englishverbs.utils.listener

import android.view.View

interface RecyclerViewClickListener {
    fun onClick(view: View, position: Int)
    fun onLongClick(view: View, position: Int)
}