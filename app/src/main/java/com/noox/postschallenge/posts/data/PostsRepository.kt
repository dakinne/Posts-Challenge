package com.noox.postschallenge.posts.data

class PostsRepository(private val postsDataSource: PostsDataSource) {

    fun loadPosts() = postsDataSource.loadPosts()

    fun loadComments() = postsDataSource.loadComments()
}