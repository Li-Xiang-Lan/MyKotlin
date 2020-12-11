package com.tbright.ktbaselibrary.base

import android.content.Context
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.annotation.ColorInt
import androidx.annotation.IntRange
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.ColorUtils
import com.tbright.ktbaselibrary.event.MessageEvent
import com.tbright.ktbaselibrary.global.GlobalConfig
import com.tbright.ktbaselibrary.utils.ActivityUtils
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

abstract class BaseActivity : AppCompatActivity() {
    private val SPACE_TIME = 500
    private var lastClickTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        ActivityUtils.addActivity(this)
        if(initEventBus()&&!EventBus.getDefault().isRegistered(this)) EventBus.getDefault().register(this)
        initView(savedInstanceState)
        setListener()
    }

    abstract fun getLayoutId(): Int

    abstract fun initView(savedInstanceState: Bundle?)

    open fun setListener() {}

    protected open fun initEventBus()=false

    @Synchronized
    fun isDoubleClick(): Boolean {
        val currentTime = System.currentTimeMillis()
        val isClick2: Boolean
        if (currentTime - lastClickTime > SPACE_TIME) {
            isClick2 = false
        } else {
            isClick2 = true
        }
        lastClickTime = currentTime
        return isClick2
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (ev.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (isShouldHideInput(v, ev)) {
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm?.hideSoftInputFromWindow(v!!.windowToken, 0)
            }
            return super.dispatchTouchEvent(ev)
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        return if (window.superDispatchTouchEvent(ev)) {
            true
        } else onTouchEvent(ev)
    }

    fun isShouldHideInput(v: View?, event: MotionEvent): Boolean {
        if (v != null && v is EditText) {
            val leftTop = intArrayOf(0, 0)
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop)
            val left = leftTop[0]
            val top = leftTop[1]
            val bottom = top + v.height
            val right = left + v.width
            return if (event.x > left && event.x < right
                && event.y > top && event.y < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                false
            } else {
                true
            }
        }
        return false
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityUtils.removeActivity(this)
        if(initEventBus()&&EventBus.getDefault().isRegistered(this)) EventBus.getDefault().unregister(this)
    }
}