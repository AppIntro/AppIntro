package com.github.paolorotolo.appintro.internal

import android.util.Log
import kotlin.reflect.KClass

private const val LOG_PREFIX = "Log: "
private const val LOG_PREFIX_LENGTH = LOG_PREFIX.length
private const val MAX_LOG_TAG_LENGTH = 23

/**
 * Helper object to interact with the Android [Log] class.
 */
internal object LogHelper {

    /**
     * Creates a tag for the logs from a [Class]
     * Don't use this when obfuscating class names!
     */
    @JvmStatic
    fun makeLogTag(cls: Class<*>) = LOG_PREFIX +
            cutTagLength(cls.simpleName, MAX_LOG_TAG_LENGTH - LOG_PREFIX_LENGTH)

    /**
     * Creates a tag for the logs from a [KClass]
     * Don't use this when obfuscating class names!
     */
    fun makeLogTag(cls: KClass<*>) = LOG_PREFIX +
            cutTagLength(cls.simpleName ?: "", MAX_LOG_TAG_LENGTH - LOG_PREFIX_LENGTH)

    private fun cutTagLength(tag: String, length: Int): String {
        return if (tag.length > length) {
            tag.substring(0, length - 1)
        } else {
            tag
        }
    }

    @JvmStatic
    fun d(tag: String, message: String) = Log.d(tag, message)

    @JvmStatic
    fun v(tag: String, message: String) = Log.v(tag, message)

    @JvmStatic
    fun i(tag: String, message: String) = Log.i(tag, message)

    @JvmOverloads
    @JvmStatic
    fun w(tag: String, message: String, throwable: Throwable? = null) {
        Log.w(tag, message, throwable)
    }

    @JvmOverloads
    @JvmStatic
    fun e(tag: String, message: String, throwable: Throwable? = null) {
        Log.e(tag, message, throwable)
    }

    @JvmOverloads
    @JvmStatic
    fun wtf(tag: String, message: String, throwable: Throwable? = null) {
        Log.wtf(tag, message, throwable)
    }
}
