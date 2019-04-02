package com.example.gddcs.gddcsdemo.view.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.gddcs.gddcsdemo.util.LogUtil

open class AppBaseActivity : AppCompatActivity() {
    val TAG = this.javaClass.name

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtil.d(TAG,"onCreate")
    }

    override fun onStart() {
        super.onStart()
        LogUtil.d(TAG,"onStart")
    }

    override fun onResume() {
        super.onResume()
        LogUtil.d(TAG,"onResume")

    }

    override fun onPause() {
        super.onPause()
        LogUtil.d(TAG,"onPause")

    }

    override fun onStop() {
        super.onStop()
        LogUtil.d(TAG,"onStop")

    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtil.d(TAG,"onDestroy")

    }

    override fun onBackPressed() {
        super.onBackPressed()
        LogUtil.d(TAG,"onBackPressed")

    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        LogUtil.d(TAG,"onNewIntent")

    }

    override fun onRestart() {
        super.onRestart()
        LogUtil.d(TAG,"onRestart")

    }
}