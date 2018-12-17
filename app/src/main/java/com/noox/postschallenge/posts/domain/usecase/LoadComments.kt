package com.noox.postschallenge.posts.domain.usecase

import com.noox.postschallenge.common.domain.usecase.BaseUseCase
import com.noox.postschallenge.posts.data.PostRepository
import com.noox.postschallenge.posts.domain.model.Comment
import io.reactivex.Scheduler

class LoadComments(
    private val repository: PostRepository,
    private val executorThread: Scheduler,
    private val uiThread: Scheduler
): BaseUseCase() {

    operator fun invoke(
        postId: Int,
        onSuccess: (List<Comment>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        addDisposable(repository.loadComments(postId)
            .subscribeOn(executorThread)
            .observeOn(uiThread)
            .subscribe(onSuccess, onError))
    }
}