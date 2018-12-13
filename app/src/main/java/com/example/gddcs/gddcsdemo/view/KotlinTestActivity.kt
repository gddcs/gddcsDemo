package com.example.gddcs.gddcsdemo.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.gddcs.gddcsdemo.R
import com.example.gddcs.gddcsdemo.databinding.ActivityKotlinTestBinding
import com.example.gddcs.gddcsdemo.model.Animal
import com.example.gddcs.gddcsdemo.model.People
import com.example.gddcs.gddcsdemo.viewmodel.PeopleViewModel

class KotlinTestActivity : AppCompatActivity() {
    lateinit var mBinding : ActivityKotlinTestBinding
    lateinit var peopleViewmodel : PeopleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_kotlin_test);

        var a = People()
        a.name.set("old man")
        a.age.set(78)

        peopleViewmodel = PeopleViewModel(a)

        mBinding.setPeopleViewModel(peopleViewmodel)
    }
}
