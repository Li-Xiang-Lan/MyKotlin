package com.tbright.ktbaseproject.demo.net.response

import com.tbright.ktbaselibrary.base.BaseResponse

class BaseMagicResponse<T> : BaseResponse<T>() {

    var data: T? = null

    var msg : String? = null

    var code :String?=null

    override fun isResponseSuccess(): Boolean {
        return code =="1"
    }

    override fun getResponseData(): T? {
        return data
    }

    override fun getResponseMessage(): String? {
        return msg
    }

    override fun getResponseStatus(): Any? {
        return code
    }
}