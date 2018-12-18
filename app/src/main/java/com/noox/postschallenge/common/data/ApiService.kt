package com.noox.postschallenge.common.data

import com.noox.postschallenge.posts.data.CommentPublishResponse
import com.noox.postschallenge.posts.domain.model.Comment
import com.noox.postschallenge.posts.domain.model.Post
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/posts")
    fun loadPosts(): Single<List<Post>>

    @GET("/posts/{postId}/comments")
    fun loadComments(@Path("postId") postId: Int): Single<List<Comment>>

    @POST("/posts/{postId}/comments")
    fun publishComment(@Path("postId") postId: Int, @Query("name") name: String, @Query("message") message: String): Single<CommentPublishResponse>
}