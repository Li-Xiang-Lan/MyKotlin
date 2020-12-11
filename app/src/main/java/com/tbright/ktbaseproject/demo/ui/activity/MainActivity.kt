package com.tbright.ktbaseproject.demo.ui.activity

import android.Manifest
import android.net.Uri
import android.os.Bundle
import com.tbright.ktbaselibrary.mvp.BaseMvpActivity
import com.tbright.ktbaseproject.demo.R
import com.tbright.ktbaseproject.demo.ui.fragment.MyFragmentActivity
import kotlinx.android.synthetic.main.activity_main2.*


class MainActivity : BaseMvpActivity<MainPresenter>(),
    MainContract.MainView {


    override fun getLayoutId(): Int {
        return R.layout.activity_main2
    }

    override fun initView(savedInstanceState: Bundle?) {

    }

    override fun setListener() {
        super.setListener()
        btGotoFragment.setOnClickListener {
            MyFragmentActivity.start(this,"修改前")
        }

        btGetNews.setOnClickListener {
            mPresenter?.singlePoetry()
        }

        btParallelRequest.setOnClickListener {
            mPresenter?.parallelRequest()
        }
        btChange.setOnClickListener {
            mPresenter?.changeBaseUrl()
        }
        btPermission.setOnClickListener {
            requestPer()
        }
    }

    private fun requestPer() {

    }

    override fun showResult(result: String) {
        tvShow.text = result
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}
