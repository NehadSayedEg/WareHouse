package com.nehad.warehouse.ui.storesActivity

import android.database.Observable
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nehad.warehouse.database.WareHouseRepository
import java.util.*

class ViewModelStores ( private val repository: WareHouseRepository): ViewModel(), androidx.databinding.Observable {


    fun getAllStores() = repository.stores
    override fun addOnPropertyChangedCallback(callback: androidx.databinding.Observable.OnPropertyChangedCallback?) {
        TODO("Not yet implemented")
    }

    override fun removeOnPropertyChangedCallback(callback: androidx.databinding.Observable.OnPropertyChangedCallback?) {
        TODO("Not yet implemented")
    }


}


class ViewModelStoresFactory(private val repository: WareHouseRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        try {
            val constructor = modelClass.getDeclaredConstructor(WareHouseRepository::class.java)
            return constructor.newInstance(repository)
        } catch (e: Exception) {
            Log.e("" , e.message.toString())
        }
        return super.create(modelClass)
    }

}