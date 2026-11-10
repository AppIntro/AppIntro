package com.github.appintro.internal

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.gesture.GestureOverlayView
import android.gesture.GestureOverlayView.OnGestureListener
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import androidx.viewpager2.widget.ViewPager2
import com.github.appintro.AppIntroViewPagerListener
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyFloat
import org.mockito.MockedStatic
import org.mockito.Mockito.atLeastOnce
import org.mockito.Mockito.clearInvocations
import org.mockito.Mockito.inOrder
import org.mockito.Mockito.mock
import org.mockito.Mockito.mockStatic
import org.mockito.Mockito.mockingDetails
import org.mockito.Mockito.never
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when` as whenever

class AppIntroViewPagerControllerTest {
    private lateinit var viewConfiguration: MockedStatic<ViewConfiguration>
    private lateinit var viewPager: ViewPager2
    private lateinit var gestureOverlay: GestureOverlayView
    private lateinit var gestureListener: OnGestureListener
    private lateinit var pageListener: AppIntroViewPagerListener
    private lateinit var controller: AppIntroViewPagerController

    @Before
    fun setUp() {
        val touchConfiguration = mock(ViewConfiguration::class.java)
        whenever(touchConfiguration.scaledTouchSlop).thenReturn(TOUCH_SLOP)
        viewConfiguration = mockStatic(ViewConfiguration::class.java)
        val touchSlopStub =
            viewConfiguration.`when`<ViewConfiguration> {
                ViewConfiguration.get(any(Context::class.java))
            }
        touchSlopStub.thenReturn(touchConfiguration)

        viewPager = mock(ViewPager2::class.java)
        gestureOverlay = mock(GestureOverlayView::class.java)
        pageListener = mock(AppIntroViewPagerListener::class.java)
        stubLayoutDirection(View.LAYOUT_DIRECTION_LTR)

        controller = AppIntroViewPagerController(viewPager, gestureOverlay)
        gestureListener = registeredGestureListener()
        controller.onNextPageRequestedListener = pageListener
    }

    @After
    fun tearDown() {
        if (::viewConfiguration.isInitialized) {
            viewConfiguration.close()
        }
    }

    @Test
    fun touchSlopJitter_doesNotReportIllegalPageAndGoToNextSlideAdvances() {
        dispatch(MotionEvent.ACTION_DOWN, 120f, 40f)
        dispatch(MotionEvent.ACTION_MOVE, 120f, 40f)
        dispatch(MotionEvent.ACTION_UP, 120f, 40f)

        dispatch(MotionEvent.ACTION_DOWN, 120f, 40f)
        dispatch(MotionEvent.ACTION_MOVE, 120f - (TOUCH_SLOP - 1), 40f)
        dispatch(MotionEvent.ACTION_UP, 120f - (TOUCH_SLOP - 1), 41f)

        dispatch(MotionEvent.ACTION_DOWN, 80f, 10f)
        dispatch(MotionEvent.ACTION_MOVE, 80f - TOUCH_SLOP, 10f)
        dispatch(MotionEvent.ACTION_UP, 80f - TOUCH_SLOP, 10f)

        verify(pageListener, never()).onIllegallyRequestedNextPage()
        whenever(viewPager.currentItem).thenReturn(0)
        controller.goToNextSlide()
        verify(viewPager).setCurrentItem(1, true)
    }

    @Test
    fun forwardSwipe_reportsIllegalPageUnderThrottleAndBlocksDrag() {
        val startX = 300f
        val forwardX = startX - (TOUCH_SLOP + 30)
        dispatch(MotionEvent.ACTION_DOWN, startX, 20f)
        dispatch(MotionEvent.ACTION_MOVE, forwardX, 22f)
        dispatch(MotionEvent.ACTION_MOVE, forwardX - 20f, 22f)
        dispatch(MotionEvent.ACTION_UP, forwardX - 25f, 22f)

        dispatch(MotionEvent.ACTION_DOWN, startX, 20f)
        dispatch(MotionEvent.ACTION_MOVE, forwardX, 20f)
        dispatch(MotionEvent.ACTION_UP, forwardX, 20f)

        verify(pageListener, times(1)).onIllegallyRequestedNextPage()
        verify(viewPager, never()).fakeDragBy(anyFloat())
    }

    @Test
    fun forwardSwipeInRtl_reportsIllegalPageAndBlocksDrag() {
        stubLayoutDirection(View.LAYOUT_DIRECTION_RTL)
        val startX = 40f
        val forwardX = startX + TOUCH_SLOP + 28
        dispatch(MotionEvent.ACTION_DOWN, startX, 15f)
        dispatch(MotionEvent.ACTION_MOVE, forwardX, 16f)
        dispatch(MotionEvent.ACTION_UP, forwardX, 16f)

        verify(pageListener, times(1)).onIllegallyRequestedNextPage()
        verify(viewPager, never()).fakeDragBy(anyFloat())
    }

    @Test
    fun backwardAndVerticalGestures_doNotReportIllegalForwardPage() {
        val startX = 100f
        dispatch(MotionEvent.ACTION_DOWN, startX, 50f)
        dispatch(MotionEvent.ACTION_MOVE, startX + TOUCH_SLOP + 24, 50f)
        dispatch(MotionEvent.ACTION_UP, startX + TOUCH_SLOP + 24, 50f)
        verify(viewPager).fakeDragBy((TOUCH_SLOP + 24).toFloat())

        dispatch(MotionEvent.ACTION_DOWN, 200f, 0f)
        dispatch(MotionEvent.ACTION_MOVE, 200f - (TOUCH_SLOP + 12), (TOUCH_SLOP + 40).toFloat())
        dispatch(MotionEvent.ACTION_UP, 200f - (TOUCH_SLOP + 12), (TOUCH_SLOP + 40).toFloat())

        verify(pageListener, never()).onIllegallyRequestedNextPage()
    }

    @Test
    fun policyAllowedHorizontalSwipe_retainsFakeDrag() {
        whenever(pageListener.onCanRequestNextPage()).thenReturn(true)
        val startX = 180f
        val endX = startX - 70f
        dispatch(MotionEvent.ACTION_DOWN, startX, 25f)
        dispatch(MotionEvent.ACTION_MOVE, endX, 27f)
        dispatch(MotionEvent.ACTION_UP, endX, 27f)

        val order = inOrder(viewPager)
        order.verify(viewPager).beginFakeDrag()
        order.verify(viewPager).fakeDragBy(endX - startX)
        order.verify(viewPager).endFakeDrag()
        verify(pageListener, never()).onIllegallyRequestedNextPage()
    }

    @Test
    fun permissionSlide_ignoresJitterAndRequestsPermissionOnForwardSwipe() {
        controller.isPermissionSlide = true
        whenever(pageListener.onCanRequestNextPage()).thenReturn(true)

        dispatch(MotionEvent.ACTION_DOWN, 90f, 30f)
        dispatch(MotionEvent.ACTION_MOVE, 90f, 30f)
        dispatch(MotionEvent.ACTION_UP, 90f, 30f)

        dispatch(MotionEvent.ACTION_DOWN, 90f, 30f)
        dispatch(MotionEvent.ACTION_MOVE, 90f - TOUCH_SLOP, 30f)
        dispatch(MotionEvent.ACTION_UP, 90f - (TOUCH_SLOP - 1), 34f)
        verify(pageListener, never()).onUserRequestedPermissionsDialog()

        val forwardX = 90f - (TOUCH_SLOP + 20)
        dispatch(MotionEvent.ACTION_DOWN, 90f, 30f)
        dispatch(MotionEvent.ACTION_MOVE, forwardX, 31f)
        dispatch(MotionEvent.ACTION_UP, forwardX, 31f)
        verify(pageListener, atLeastOnce()).onUserRequestedPermissionsDialog()
    }

    @Test
    fun cancelAndRejectedSwipeUp_endsFakeDragFromFreshCoordinates() {
        dispatch(MotionEvent.ACTION_DOWN, 400f, 10f)
        dispatch(MotionEvent.ACTION_CANCEL, 100f, 10f)
        verify(pageListener, never()).onIllegallyRequestedNextPage()
        verifyDragEndedWithoutForwardMove()

        clearInvocations(viewPager)
        dispatch(MotionEvent.ACTION_DOWN, 100f, 10f)
        dispatch(MotionEvent.ACTION_MOVE, 100f - TOUCH_SLOP, 10f)
        dispatch(MotionEvent.ACTION_UP, 100f - TOUCH_SLOP, 10f)
        verify(pageListener, never()).onIllegallyRequestedNextPage()

        clearInvocations(viewPager)
        val forwardX = 220f - (TOUCH_SLOP + 35)
        dispatch(MotionEvent.ACTION_DOWN, 220f, 10f)
        dispatch(MotionEvent.ACTION_MOVE, forwardX, 10f)
        dispatch(MotionEvent.ACTION_UP, forwardX, 10f)
        verify(pageListener, times(1)).onIllegallyRequestedNextPage()
        verify(viewPager, never()).fakeDragBy(anyFloat())
        verifyDragEndedWithoutForwardMove()
    }

    @Test
    fun disabledPaging_producesNoCallbacks() {
        controller.isFullPagingEnabled = false
        dispatch(MotionEvent.ACTION_DOWN, 250f, 20f)
        dispatch(MotionEvent.ACTION_MOVE, 10f, 20f)
        dispatch(MotionEvent.ACTION_UP, 10f, 20f)
        verify(pageListener, never()).onIllegallyRequestedNextPage()

        controller.isPermissionSlide = true
        whenever(pageListener.onCanRequestNextPage()).thenReturn(true)
        dispatch(MotionEvent.ACTION_DOWN, 250f, 20f)
        dispatch(MotionEvent.ACTION_MOVE, 10f, 20f)
        dispatch(MotionEvent.ACTION_UP, 10f, 20f)
        verify(pageListener, never()).onUserRequestedPermissionsDialog()
        verify(pageListener, never()).onIllegallyRequestedNextPage()
    }

    private fun verifyDragEndedWithoutForwardMove() {
        val order = inOrder(viewPager)
        order.verify(viewPager).beginFakeDrag()
        order.verify(viewPager).endFakeDrag()
    }

    private fun dispatch(
        action: Int,
        x: Float,
        y: Float,
    ) {
        val event = mock(MotionEvent::class.java)
        whenever(event.action).thenReturn(action)
        whenever(event.x).thenReturn(x)
        whenever(event.y).thenReturn(y)
        when (action) {
            MotionEvent.ACTION_DOWN -> gestureListener.onGestureStarted(gestureOverlay, event)
            MotionEvent.ACTION_MOVE -> gestureListener.onGesture(gestureOverlay, event)
            MotionEvent.ACTION_UP -> gestureListener.onGestureEnded(gestureOverlay, event)
            MotionEvent.ACTION_CANCEL -> gestureListener.onGestureCancelled(gestureOverlay, event)
            else -> error("Unexpected action $action")
        }
    }

    private fun stubLayoutDirection(layoutDirection: Int) {
        val context = mock(Context::class.java)
        val resources = mock(Resources::class.java)
        val configuration = mock(Configuration::class.java)
        whenever(context.resources).thenReturn(resources)
        whenever(resources.configuration).thenReturn(configuration)
        whenever(configuration.layoutDirection).thenReturn(layoutDirection)
        whenever(viewPager.context).thenReturn(context)
    }

    private fun registeredGestureListener(): OnGestureListener {
        val invocation =
            mockingDetails(gestureOverlay).invocations.first { call ->
                call.method.name == "addOnGestureListener"
            }
        return invocation.getArgument(0)
    }

    private companion object {
        private const val TOUCH_SLOP = 16
    }
}
