package com.noox.postschallenge.posts.ui.detail

import com.noox.postschallenge.posts.domain.model.Comment
import com.noox.postschallenge.posts.domain.model.Post

interface PostDetailView {
    fun showError()
    fun showPost(post: Post)
    fun showComments(comments: List<Comment>)
}