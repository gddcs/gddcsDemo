package com.example.gddcs.gddcsdemo.viewmodel

import android.databinding.ObservableField

abstract class BaseViewModel<T> {

    var text = ObservableField<String>()

    constructor(t : T){
        text.set(bindText(t))
    }

    abstract fun bindText(t : T): String
}