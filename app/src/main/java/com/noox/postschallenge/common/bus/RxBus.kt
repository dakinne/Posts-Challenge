package com.noox.postschallenge.common.bus

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

object RxBus {

    private val publisher = PublishSubject.create<Event>()

    fun publish(event: Event) {
        publisher.onNext(event)
    }

    fun <T: Event> listen(eventType: Class<T>): Observable<T> = publisher.ofType(eventType)

}