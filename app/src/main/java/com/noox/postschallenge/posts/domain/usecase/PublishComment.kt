package com.noox.postschallenge.posts.domain.usecase

import com.noox.postschallenge.common.domain.usecase.BaseUseCase
import com.noox.postschallenge.posts.data.PostRepository
import com.noox.postschallenge.posts.domain.model.Comment
import io.reactivex.Scheduler

class PublishComment(
    private val repository: PostRepository,
    private val executorThread: Scheduler,
    private val uiThread: Scheduler
): BaseUseCase() {

    operator fun invoke(
        postId: Int,
        name: String,
        message: String,
        onSuccess: (Comment) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        addDisposable(repository.publishComment(postId, name, message)
            .subscribeOn(executorThread)
            .observeOn(uiThread)
            .subscribe({
                onSuccess(Comment(
                    id = it.id,
                    postId = postId,
                    name = name,
                    body = message
                ))
            }, onError)
        )
    }
}