package com.noox.postschallenge.posts.ui.list

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.noox.postschallenge.R
import com.noox.postschallenge.common.extensions.inflate
import com.noox.postschallenge.posts.domain.model.Post

class PostListAdapter(
    private val items: List<Post>,
    private val onItemClick: (Post) -> Unit
) : RecyclerView.Adapter<PostListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostListViewHolder {
        val itemView = parent.inflate(R.layout.item_post)
        return PostListViewHolder(itemView, onItemClick)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: PostListViewHolder, position: Int) {
        holder.bind(items[position])
    }
}
