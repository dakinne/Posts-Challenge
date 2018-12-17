package com.noox.postschallenge.posts.data

import com.noox.postschallenge.common.data.ApiService

class PostDataSource(private val apiService: ApiService) {

    fun loadPosts() = apiService.loadPosts()

    fun loadComments(postId: Int) = apiService.loadComments(postId)

    fun publishComment(postId: Int, name: String, message: String) = apiService.publishComment(postId, name, message)
}