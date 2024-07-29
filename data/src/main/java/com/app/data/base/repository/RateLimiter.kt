package com.app.data.base.repository

import android.os.SystemClock
import java.util.concurrent.TimeUnit

class RateLimiter<in KEY>(timeout: Int, timeUnit: TimeUnit) {
    private val timestamps = HashMap<KEY, Long>()
    private val timeoutMillis = timeUnit.toMillis(timeout.toLong())

    @Synchronized
    fun shouldFetch(key: KEY): Boolean {
        val now = now()
        val lastFetched = timestamps[key]

        if (lastFetched == null || now - lastFetched > timeoutMillis) {
            timestamps[key] = now
            return true
        }
        return false
    }

    private fun now() = SystemClock.uptimeMillis()

    @Synchronized
    fun reset(key: KEY) {
        timestamps.remove(key)
    }
}