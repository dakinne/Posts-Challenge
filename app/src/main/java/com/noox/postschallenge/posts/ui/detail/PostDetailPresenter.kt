package com.noox.postschallenge.posts.ui.detail

import com.noox.postschallenge.posts.domain.model.Comment
import com.noox.postschallenge.posts.domain.usecase.LoadComments

class PostDetailPresenter(
    private val loadComments: LoadComments
) {

    var view: PostDetailView? = null

    fun init() {
        view?.let {
            it.showLoading()
            loadComments(::onSuccess, ::onError)
        }
    }

    fun destroy() {
        view = null
        loadComments.dispose()
    }

    private fun onSuccess(comments: List<Comment>) {
        view?.run {
            hideLoading()
            when {
                comments.isEmpty() -> showEmptyList()
                else -> showComments(comments)
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