package com.nehad.warehouse.ui.reports

import android.util.Log
import androidx.databinding.Observable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nehad.warehouse.database.WareHouseRepository

class ReportsViewModel ( private val repository: WareHouseRepository): ViewModel(), androidx.databinding.Observable {

    fun getAlldocuments() = repository.documentHeader

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        TODO("Not yet implemented")
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        TODO("Not yet implemented")
    }


    class ViewModelStoresFactory(private val repository: WareHouseRepository) :
        ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            try {
                val constructor = modelClass.getDeclaredConstructor(WareHouseRepository::class.java)
                return constructor.newInstance(repository)
            } catch (e: Exception) {
                Log.e("", e.message.toString())
            }
            return super.create(modelClass)
        }
    }
}