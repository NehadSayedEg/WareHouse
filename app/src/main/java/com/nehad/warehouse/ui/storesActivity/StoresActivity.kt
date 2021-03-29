package com.nehad.warehouse.ui.storesActivity

import android.graphics.drawable.ClipDrawable.VERTICAL
import android.icu.lang.UCharacter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.nehad.warehouse.R
import com.nehad.warehouse.database.Dao.WareHouseDao
import com.nehad.warehouse.database.WareHouseDB
import com.nehad.warehouse.database.WareHouseRepository
import com.nehad.warehouse.model.Store
import kotlinx.android.synthetic.main.activity_stores.*

class StoresActivity : AppCompatActivity() {
    lateinit  var storesAdapter: StoresAdapter
    lateinit var  viewModelStores: ViewModelStores
//    lateinit var binding :ActivityStoresBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stores)



        val dao:WareHouseDao = WareHouseDB.getInstance(application).wareHouseDao
        val repository = WareHouseRepository(dao)
        val factory = ViewModelStoresFactory(repository)

    storesRV.apply {
        layoutManager =  LinearLayoutManager(this@StoresActivity)
        storesAdapter = StoresAdapter(this@StoresActivity )
        adapter =storesAdapter
        val dividers  = DividerItemDecoration(applicationContext , LinearLayoutManager.VERTICAL)
        addItemDecoration(dividers)
    }
    viewModelStores = ViewModelProvider(this , factory).get(ViewModelStores::class.java)
    viewModelStores.getAllStores().observe(this , Observer {

        storesAdapter.setStoreList( ArrayList(it))
        storesAdapter.notifyDataSetChanged()

         })




    }
}