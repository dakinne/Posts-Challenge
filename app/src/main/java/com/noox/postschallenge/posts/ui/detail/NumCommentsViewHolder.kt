package com.noox.postschallenge.posts.ui.detail

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.noox.postschallenge.R
import kotlinx.android.synthetic.main.item_num_comments.view.*
class NumCommentsViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    fun bind(numComments: Int) {
        itemView.message.text = getQuantityString(R.plurals.post_detail_num_comments, numComments)
    }

    private fun getQuantityString(stringResId: Int, numOfItems: Int): CharSequence {
        return itemView.resources.getQuantityString(stringResId, numOfItems, numOfItems, numOfItems)
    }
}
