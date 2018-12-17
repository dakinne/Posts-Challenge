package com.noox.postschallenge.posts.ui.detail

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.noox.postschallenge.posts.domain.model.Comment
import kotlinx.android.synthetic.main.item_comment.view.*

class CommentViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    fun bind(comment: Comment) {
        itemView.name.text = comment.name
        itemView.body.text = comment.body
    }
}
