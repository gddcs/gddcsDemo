package com.example.gddcs.gddcsdemo.viewmodel

import android.databinding.ObservableField
import android.util.Log
import android.view.View
import com.example.gddcs.gddcsdemo.model.People

class PeopleViewModel {
    var people = ObservableField<People>()
    var shout : Int

    constructor (people: People) {
        this.people.set(people)
        this.shout = 0
    }

    fun onClickPeople() {
        shout ++
        var tempage = this.people.get()?.age.toString()
        Log.e("tag",tempage)
        this.people.get()?.name?.set(tempage.toString()+shout.toString())
    }
}