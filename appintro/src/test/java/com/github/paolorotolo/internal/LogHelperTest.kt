package com.github.paolorotolo.appintro.internal

import org.junit.Assert.assertEquals
import org.junit.Test

class LogHelperTest {

    @Test
    fun testMakeLogTag_withJavaClass() {
        val logTag = LogHelper.makeLogTag(Object::class.java)
        assertEquals("Log: Object", logTag)
    }

    @Test
    fun testMakeLogTag_withKClass() {
        val logTag = LogHelper.makeLogTag(KotlinVersion::class)
        assertEquals("Log: KotlinVersion", logTag)
    }

    @Test
    fun testMakeLogTag_withLongName_nameIsCropped() {
        val logTag = LogHelper.makeLogTag(KotlinReflectionNotSupportedError::class.java)
        assertEquals("Log: KotlinReflectionN", logTag)
    }
}
