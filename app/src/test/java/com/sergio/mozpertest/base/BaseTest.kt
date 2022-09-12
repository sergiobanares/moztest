package com.sergio.mozpertest.base

import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import org.junit.After
import org.junit.Before

open class BaseTest {
    @Before
    /** You should call this super before anything else*/
    open fun setup() {
        MockKAnnotations.init(this, relaxed = true)
    }

    @After
    /** You should call this super before anything else*/
    open fun onClear() {
        clearAllMocks()
    }
}