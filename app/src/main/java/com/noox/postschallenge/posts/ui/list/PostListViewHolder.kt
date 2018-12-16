package com.noox.postschallenge.posts.ui.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.noox.postschallenge.posts.domain.model.Post

import kotlinx.android.synthetic.main.item_post.view.*

class PostListViewHolder(
    itemView: View,
    private val onItemClicked: (Post) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private var post: Post? = null

    init {
        itemView.setOnClickListener {
            post?.let(onItemClicked)
        }
    }

    fun bind(post: Post) {
        this.post = post
        itemView.title.text = post.title
        itemView.body.text = post.body.take(80)
    }
}
