package com.tbright.ktbaseproject.demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tbright.ktbaseproject.demo.ui.activity.MainActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        click_btn.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}