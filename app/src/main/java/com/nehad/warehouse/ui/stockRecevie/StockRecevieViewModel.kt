package com.nehad.warehouse.ui.stockRecevie

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nehad.warehouse.database.WareHouseRepository
import com.nehad.warehouse.model.Shelf

class StockRecevieViewModel ( private val repository: WareHouseRepository): ViewModel(), androidx.databinding.Observable {


    fun getAllshelfs() = repository.shelfs
    fun getAllStores() = repository.stores

    override fun addOnPropertyChangedCallback(callback: androidx.databinding.Observable.OnPropertyChangedCallback?) {
        TODO("Not yet implemented")
    }

    override fun removeOnPropertyChangedCallback(callback: androidx.databinding.Observable.OnPropertyChangedCallback?) {
        TODO("Not yet implemented")
    }


    fun getSelfofStore(store_id: String): LiveData<List<Shelf>> {
        return repository.getSelfofStore(store_id)
    }

    fun getStoreIDByName(store_name_en: String): LiveData<List<String>> {
        return repository.getStoreIDByName(store_name_en)
    }

    fun getStoreID(store_name_en: String): String {
        return repository.getStoreID(store_name_en)
    }

    fun getStoreByName(): LiveData<List<String>> {
        return repository.getStoreByName()
    }

    fun getShelfByName(store_id: String): LiveData<List<String>> {
        return repository.getShelfByName(store_id)
    }

    fun findItemByName(itemName: String): LiveData<List<String>> {

        return repository.findItemByName(itemName)
    }

    fun getItemByName(): LiveData<List<String>> {
        return repository.getItemByName()
    }

    fun getShelfByStoreID(storeId:String): LiveData<List<String>> {
        return repository.getShelfByStoreID(storeId)
    }
}
class StockRecevieViewModelFactory(private val repository: WareHouseRepository) : ViewModelProvider.NewInstanceFactory() {

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