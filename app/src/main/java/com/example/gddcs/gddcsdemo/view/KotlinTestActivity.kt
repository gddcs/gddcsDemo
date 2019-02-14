package com.example.gddcs.gddcsdemo.view

import android.app.AlertDialog
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Window
import android.view.WindowManager
import com.example.gddcs.gddcsdemo.R
import com.example.gddcs.gddcsdemo.databinding.ActivityKotlinTestBinding
import com.example.gddcs.gddcsdemo.model.Animal
import com.example.gddcs.gddcsdemo.model.People
import com.example.gddcs.gddcsdemo.viewmodel.PeopleViewModel

class KotlinTestActivity : AppCompatActivity() {
    lateinit var mBinding : ActivityKotlinTestBinding
    lateinit var peopleViewmodel : PeopleViewModel
    var a = People()
    var numb : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        windowConfig()
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_kotlin_test);

        a = People()
        a.name.set("old man")
        a.age.set(78)

        peopleViewmodel = PeopleViewModel(a)

        mBinding.setPeopleViewModel(peopleViewmodel)
    }

    override fun onResume() {
        super.onResume()

    }

    fun windowConfig(){
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

}
