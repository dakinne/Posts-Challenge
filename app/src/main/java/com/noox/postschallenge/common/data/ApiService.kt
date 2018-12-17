package com.noox.postschallenge.common.data

import com.noox.postschallenge.posts.domain.model.Comment
import com.noox.postschallenge.posts.domain.model.Post
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/posts")
    fun loadPosts(): Single<List<Post>>

    @GET("/posts/{postId}/comments")
    fun loadComments(@Path("postId") postId: Int): Single<List<Comment>>
}