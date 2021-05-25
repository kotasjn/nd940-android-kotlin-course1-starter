package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeViewModel : ViewModel() {

    private val _shoe = MutableLiveData<Shoe>()
    val shoe: LiveData<Shoe>
        get() = _shoe

    private val _shoeList = MutableLiveData<List<Shoe>>()
    val shoeList: LiveData<List<Shoe>>
        get() = _shoeList

    private val shoes = listOf(
        Shoe("Asics Gel Nimbus 23", 42.0, "Asics", "Great design, perfect feeling"),
        Shoe("Hoka One Rocket X", 45.0, "Hoka", "Extremely lightweight shoes"),
        Shoe("Brooks Levitate 4", 40.0, "Brooks", "Very durable shoes")
    )

    init {
        _shoeList.value = shoes
    }

    fun chooseShoeItem(position: Int) {
        _shoe.value = _shoeList.value?.get(position)
    }
}