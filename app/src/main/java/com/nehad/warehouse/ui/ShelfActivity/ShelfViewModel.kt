package com.nehad.warehouse.ui.ShelfActivity

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nehad.warehouse.database.Relations.StoresAndShelfs
import com.nehad.warehouse.database.WareHouseRepository
import com.nehad.warehouse.model.Shelf

class ShelfViewModel ( private val repository: WareHouseRepository): ViewModel(), androidx.databinding.Observable {


    fun getAllshelfs() = repository.shelfs
    fun getAllStores() = repository.stores

    override fun addOnPropertyChangedCallback(callback: androidx.databinding.Observable.OnPropertyChangedCallback?) {
        TODO("Not yet implemented")
    }

    override fun removeOnPropertyChangedCallback(callback: androidx.databinding.Observable.OnPropertyChangedCallback?) {
        TODO("Not yet implemented")
    }



    fun  getSelfofStore( store_id :String):LiveData<List<Shelf>>  {
        return repository.getSelfofStore(store_id)
    }




}


class ShelfViewModelFactory(private val repository: WareHouseRepository) : ViewModelProvider.NewInstanceFactory() {

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