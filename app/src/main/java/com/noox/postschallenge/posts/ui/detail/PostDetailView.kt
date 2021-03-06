package com.noox.postschallenge.posts.ui.detail

import com.noox.postschallenge.posts.domain.model.Comment
import com.noox.postschallenge.posts.domain.model.Post

interface PostDetailView {
    fun showLoading()
    fun hideLoading()
    fun showError()
    fun showNewCommentPublished()
    fun showPost(post: Post)
    fun showComments(comments: List<Comment>)
    fun showNewComment(comment: Comment)
}