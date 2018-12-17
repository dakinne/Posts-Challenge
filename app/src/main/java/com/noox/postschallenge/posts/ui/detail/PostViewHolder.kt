package com.noox.postschallenge.posts.ui.detail

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.noox.postschallenge.posts.domain.model.Post

import kotlinx.android.synthetic.main.item_post.view.*

class PostViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    fun bind(post: Post) {
        itemView.title.text = post.title
        itemView.body.text = post.body.take(80)
    }
}
