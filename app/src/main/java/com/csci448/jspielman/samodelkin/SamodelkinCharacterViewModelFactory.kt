package com.csci448.jspielman.samodelkin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SamodelkinCharacterViewModelFactory : ViewModelProvider.Factory {
    fun getViewModelClass() = SamodelkinCharacterViewModel::class.java
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(getViewModelClass()))
            return modelClass.getConstructor(Int::class.java, Int::class.java).newInstance()
        throw IllegalArgumentException("Unknown Viewmodel")
    }
}
