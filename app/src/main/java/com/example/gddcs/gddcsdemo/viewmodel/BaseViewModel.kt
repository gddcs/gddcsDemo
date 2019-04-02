package com.example.gddcs.gddcsdemo.viewmodel

import android.databinding.ObservableField

abstract class BaseViewModel<T> {

    var text = ObservableField<String>()

    /**
     * 用于自定义布局textview显示的文字
     */
    constructor(t : T){
        text.set(bindText(t))
    }

    abstract fun bindText(t : T): String

}