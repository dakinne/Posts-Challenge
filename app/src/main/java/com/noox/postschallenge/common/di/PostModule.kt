package com.noox.postschallenge.common.di

import com.noox.postschallenge.posts.data.PostDataSource
import com.noox.postschallenge.posts.data.PostRepository
import com.noox.postschallenge.posts.domain.usecase.LoadComments
import com.noox.postschallenge.posts.domain.usecase.LoadPosts
import com.noox.postschallenge.posts.domain.usecase.PublishComment
import com.noox.postschallenge.posts.ui.detail.PostDetailPresenter
import com.noox.postschallenge.posts.ui.form.CommentFormPresenter
import com.noox.postschallenge.posts.ui.list.PostListPresenter
import org.koin.dsl.module.module

val postModule = module {

    factory { PostDetailPresenter(loadComments = get()) }
    factory { CommentFormPresenter(publishComment = get() ) }
    factory { PostListPresenter(loadPosts = get()) }

    factory { LoadComments(
        repository = get(),
        executorThread = get("executor_thread"),
        uiThread = get("ui_thread")
    ) }

    factory { LoadPosts(
        repository = get(),
        executorThread = get("executor_thread"),
        uiThread = get("ui_thread")
    ) }

    factory { PublishComment(
        repository = get(),
        executorThread = get("executor_thread"),
        uiThread = get("ui_thread")

    ) }

    single { PostRepository(
        postsDataSource = get()
    ) }

    single { PostDataSource(
        apiService = get()
    ) }
}