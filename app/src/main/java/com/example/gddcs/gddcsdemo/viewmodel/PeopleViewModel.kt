package com.example.gddcs.gddcsdemo.viewmodel

import android.databinding.ObservableField
import android.util.Log
import com.example.gddcs.gddcsdemo.model.People

class PeopleViewModel : BaseViewModel<People>{

    var people = ObservableField<People>()
    var shout : Int

    constructor (people: People) : super(people) {
        Log.e("tag","constructor---PeopleViewModel-------")
        this.people.set(people)
        this.shout = 0
    }

    fun onClickPeople() {
        shout ++
        text.set(text.get().toString()+"点击"+shout)
    }

    override fun bindText(t: People): String {
        Log.e("tag","bindText----------")
        return t.name.get().toString()+"同学"
    }

}