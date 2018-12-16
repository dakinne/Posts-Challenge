package com.noox.postschallenge.posts.data

class PostRepository(private val postsDataSource: PostDataSource) {

    fun loadPosts() = postsDataSource.loadPosts()

    fun loadComments() = postsDataSource.loadComments()
}