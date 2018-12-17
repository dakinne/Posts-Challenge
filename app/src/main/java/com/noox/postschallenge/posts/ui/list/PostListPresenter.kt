package com.noox.postschallenge.posts.ui.list

import com.noox.postschallenge.posts.domain.model.Post
import com.noox.postschallenge.posts.domain.usecase.LoadPosts

class PostListPresenter(
    private val loadPosts: LoadPosts
) {

    var view: PostListView? = null

    fun init() {
        view?.let {
            it.showLoading()
            loadPosts(::onSuccess, ::onError)
        }
    }

    fun destroy() {
        view = null
        loadPosts.dispose()
    }

    private fun onSuccess(posts: List<Post>) {
        view?.run {
            hideLoading()
            when {
                posts.isEmpty() -> showEmptyList()
                else -> showPosts(posts)
            }
        }
    }

    private fun onError(throwable: Throwable) {
        view?.run {
            hideLoading()
            showError()
        }
    }
}