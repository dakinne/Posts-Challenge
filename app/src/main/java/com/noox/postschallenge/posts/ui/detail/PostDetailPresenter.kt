package com.noox.postschallenge.posts.ui.detail

import com.noox.postschallenge.common.bus.Event
import com.noox.postschallenge.common.bus.RxBus
import com.noox.postschallenge.posts.domain.model.Comment
import com.noox.postschallenge.posts.domain.model.Post
import com.noox.postschallenge.posts.domain.usecase.LoadComments
import io.reactivex.disposables.CompositeDisposable

class PostDetailPresenter(
    private val loadComments: LoadComments
) {

    var view: PostDetailView? = null
    private lateinit var post: Post

    private val eventDisposables = CompositeDisposable()

    fun init(post: Post) {
        this.post = post

        view?.let {
            it.showPost(post)
            it.showLoading()
            loadComments(post.id, ::onSuccess, ::onError)
        }

        eventDisposables.add(
            RxBus.listen(Event.CommentPublished::class.java)
                .subscribe { onCommentPublished(it.comment) }
        )
    }

    private fun onCommentPublished(comment: Comment) {
        view?.run {
            showNewComment(comment)
            showNewCommentPublished()
        }
    }

    fun destroy() {
        view = null
        loadComments.dispose()
        eventDisposables.dispose()
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

    fun newCommentPublished() {
        view?.showNewCommentPublished()
        loadComments(post.id, ::onSuccess, ::onError)
    }
}