package com.noox.postschallenge.common.bus

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject

object RxBus {

    private val disposablesMap: HashMap<Any, CompositeDisposable?> by lazy {
        HashMap<Any, CompositeDisposable?>()
    }
    private val publisher = PublishSubject.create<Event>()

    fun publish(event: Event) {
        publisher.onNext(event)
    }

    fun <T: Event> listen(eventType: Class<T>, block: (T) -> Unit): Disposable = publisher.ofType(eventType).subscribe { block(it) }

    fun register(subscriber: Any, disposable: Disposable) {
        var compositeDisposable = disposablesMap[subscriber]
        if (compositeDisposable == null) {
            compositeDisposable = CompositeDisposable()
            disposablesMap[subscriber] = compositeDisposable
        }
        compositeDisposable.add(disposable)
    }

    fun unregister(subscriber: Any) {
        disposablesMap.remove(subscriber)?.dispose()
    }
}
