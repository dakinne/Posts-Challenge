package com.noox.postschallenge.posts.data

import com.noox.postschallenge.posts.domain.model.Comment

class PostRepository(private val postsDataSource: PostDataSource) {

    fun loadPosts() = postsDataSource.loadPosts()

    fun loadComments(postId: Int) = postsDataSource.loadComments(postId)

    fun publishComment(postId: Int, name: String, message: String) = postsDataSource.publishComment(postId, name, message)
}