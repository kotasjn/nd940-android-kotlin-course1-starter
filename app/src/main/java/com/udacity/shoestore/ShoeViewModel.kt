package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeViewModel : ViewModel() {

    private val _shoe = MutableLiveData<Shoe>()
    val shoe: LiveData<Shoe>
        get() = _shoe

    private val _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList: LiveData<MutableList<Shoe>>
        get() = _shoeList

    private val shoes = mutableListOf(
        Shoe("Asics Gel Nimbus 23", 42.0, "Asics", "Great design, perfect feeling"),
        Shoe("Hoka One Rocket X", 45.0, "Hoka", "Extremely lightweight shoes"),
        Shoe("Brooks Levitate 4", 40.0, "Brooks", "Very durable shoes")
    )

    private var position: Int? = null

    init {
        _shoeList.value = shoes
    }

    fun chooseShoeItem(position: Int) {
        _shoe.value = _shoeList.value?.get(position)?.copy()
        this.position = position
    }

    fun saveShoeItem() {
        _shoe.value?.let { shoe ->
            position.let { position ->
                if (position != null) {
                    _shoeList.value?.set(position, shoe)
                } else {
                    _shoeList.value?.add(shoe)
                }
            }
        }
    }

    fun newShoeItem() {
        _shoe.value = Shoe()
        position = null
    }
}