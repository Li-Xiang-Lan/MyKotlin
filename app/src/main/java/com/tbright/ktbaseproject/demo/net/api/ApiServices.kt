package com.tbright.ktbaseproject.demo.net.api

import com.tbright.ktbaselibrary.extension.create
import com.tbright.ktbaseproject.demo.bean.FuncBean
import com.tbright.ktbaseproject.demo.bean.MusicBean
import com.tbright.ktbaseproject.demo.net.response.BaseMagicResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.*

interface ApiServices {

    companion object {
        val instance: ApiServices by lazy {
            create(ApiServices::class.java)
        }
    }

    @GET("/v1/app/index-icon")
    fun singlePoetry(): Deferred<BaseMagicResponse<ArrayList<FuncBean>>>

    @GET("musicBroadcasting/")
    fun getMusicList(): Deferred<BaseMagicResponse<List<MusicBean>?>>

}