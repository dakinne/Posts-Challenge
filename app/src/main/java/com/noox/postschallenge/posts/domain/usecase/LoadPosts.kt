package com.noox.postschallenge.posts.domain.usecase

import com.noox.postschallenge.common.domain.usecase.BaseUseCase
import com.noox.postschallenge.posts.data.PostRepository
import com.noox.postschallenge.posts.domain.model.Post
import io.reactivex.Scheduler

class LoadPosts(
    private val repository: PostRepository,
    private val executorThread: Scheduler,
    private val uiThread: Scheduler
): BaseUseCase() {

    operator fun invoke(
        onSuccess: (List<Post>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        addDisposable(repository.loadPosts()
            .subscribeOn(executorThread)
            .observeOn(uiThread)
            .subscribe(onSuccess, onError))
    }
}