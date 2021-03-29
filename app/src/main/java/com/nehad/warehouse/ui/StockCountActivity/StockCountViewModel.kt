package com.nehad.warehouse.ui.StockCountActivity

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nehad.warehouse.database.WareHouseRepository
import com.nehad.warehouse.model.DocumentHeader
import com.nehad.warehouse.model.Shelf
import com.nehad.warehouse.model.StockCountDetail
import com.nehad.warehouse.model.StockheaderDoc

class StockCountViewModel ( private val repository: WareHouseRepository): ViewModel(), androidx.databinding.Observable {


    fun stockDocumentHeaders() = repository.stockheaderDoc
    fun stockDocDetails() = repository.stockCountDetail





    fun insertStockCountDocHeader(stockheaderDoc: StockheaderDoc){
        repository.insertStockCountDocHeader(stockheaderDoc)
    }

    fun insertStockCountDocDetails(stockCountDetail: StockCountDetail){
        repository.insertStockCountDocDetails(stockCountDetail)
    }


    fun  getStockCountDocHeader():LiveData<List<StockheaderDoc>>{
        return repository.getStockCountDocHeader()
    }


    fun  getStockCountDoccDetails():LiveData<List<StockCountDetail>>{
        return repository.getStockCountDoccDetails()

    }


    override fun addOnPropertyChangedCallback(callback: androidx.databinding.Observable.OnPropertyChangedCallback?) {
        TODO("Not yet implemented")
    }

    override fun removeOnPropertyChangedCallback(callback: androidx.databinding.Observable.OnPropertyChangedCallback?) {
        TODO("Not yet implemented")
    }






}
class StockCountViewModelFactory(private val repository: WareHouseRepository) : ViewModelProvider.NewInstanceFactory() {

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