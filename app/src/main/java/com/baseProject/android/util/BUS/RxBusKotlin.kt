package com.baseProject.android.util.BUS

import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

/**
 *
 * @author Abbas Asadi
 */
object RxBusKotlin {

    private val behaviorSubject = BehaviorSubject.create<Any>()

    fun <T> subscribe(eventType: Class<T>): Observable<T> = behaviorSubject.ofType(eventType)

    fun publish(event: Any) {
        behaviorSubject.onNext(event)
    }
}