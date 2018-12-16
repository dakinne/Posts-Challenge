package com.noox.postschallenge.common.domain.usecase

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseUseCase {

    private val disposables = CompositeDisposable()

    open fun dispose() {
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
    }

    protected fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }
}