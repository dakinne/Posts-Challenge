package com.noox.postschallenge.posts.ui.form

import com.noox.postschallenge.common.bus.Event
import com.noox.postschallenge.common.bus.RxBus
import com.noox.postschallenge.posts.domain.model.Comment
import com.noox.postschallenge.posts.domain.usecase.PublishComment

class CommentFormPresenter(
    private val publishComment: PublishComment
) {

    var view: CommentFormView? = null
    private var postId: Int? = null

    fun init(postId: Int) {
        this.postId = postId
    }

    fun destroy() {
        view = null
        publishComment.dispose()
    }

    fun commentDataChanged(name: String, message: String) {
        view?.enablePublishButton(commentIsValid(name, message))
    }

    private fun commentIsValid(name: String, message: String): Boolean {
        return name.isNotBlank() && message.isNotBlank()
    }

    fun publishComment(name: String, message: String) {
        postId?.let {
            publishComment(it, name, message, ::onComplete, ::onError)
        }
    }

    private fun onComplete(comment: Comment) {
        RxBus.publish(Event.CommentPublished(comment))
        view?.close()
    }

    private fun onError(throwable: Throwable) {
        view?.showError()
    }
}