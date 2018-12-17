package com.noox.postschallenge.posts.ui.detail

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.noox.postschallenge.R
import com.noox.postschallenge.common.extensions.inflate
import com.noox.postschallenge.posts.domain.model.Comment
import com.noox.postschallenge.posts.domain.model.Post
import java.security.InvalidParameterException

class PostDetailAdapter(
    post: Post
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items = arrayListOf<PostDetailItemType>()

    init {
        items.add(PostItemType(post))
    }

    fun addComments(comments: List<Comment>) {
        items.add(NumCommentsItemType(comments.size))
        comments.forEach {
            items.add(CommentItemType(it))
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when(viewType) {
            PostDetailItemType.POST -> PostViewHolder(parent.inflate(R.layout.item_post))
            PostDetailItemType.COMMENT -> CommentViewHolder(parent.inflate(R.layout.item_comment))
            PostDetailItemType.NUM_COMMENTS -> NumCommentsViewHolder(parent.inflate(R.layout.item_num_comments))
            else -> throw InvalidParameterException()
        }
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun getItemViewType(position: Int) : Int {
        return items[position].itemType
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        when(item) {
            is PostItemType -> { (holder as PostViewHolder).bind(item.post) }
            is CommentItemType -> { (holder as CommentViewHolder).bind(item.comment) }
            is NumCommentsItemType -> { (holder as NumCommentsViewHolder).bind(item.numComments) }
        }
    }
}
