package com.noox.postschallenge.posts.ui.form

interface CommentFormView {
    fun enablePublishButton(bool: Boolean)
    fun showError()
    fun close()
}