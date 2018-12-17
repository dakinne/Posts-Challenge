package com.noox.postschallenge.posts.ui.detail

import com.noox.postschallenge.posts.domain.model.Comment
import com.noox.postschallenge.posts.domain.model.Post
import com.noox.postschallenge.posts.domain.usecase.LoadComments

class PostDetailPresenter(
    private val loadComments: LoadComments
) {

    var view: PostDetailView? = null

    fun init(post: Post) {
        view?.let {
            it.showPost(post)
            it.showLoading()
            loadComments(post.id, ::onSuccess, ::onError)
        }
    }

    fun destroy() {
        view = null
        loadComments.dispose()
    }

    private fun onSuccess(comments: List<Comment>) {
        view?.run {
            hideLoading()
            showComments(comments)
        }
    }

    private fun onError(throwable: Throwable) {
        view?.run {
            hideLoading()
            showError()
        }
    }
}