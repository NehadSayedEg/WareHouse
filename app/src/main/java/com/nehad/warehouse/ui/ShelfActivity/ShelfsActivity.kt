package com.nehad.warehouse.ui.ShelfActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.nehad.warehouse.R
import com.nehad.warehouse.database.Dao.WareHouseDao
import com.nehad.warehouse.database.WareHouseDB
import com.nehad.warehouse.database.WareHouseRepository
import com.nehad.warehouse.ui.storesActivity.ViewModelStoresFactory
import kotlinx.android.synthetic.main.activity_shelfs.*
import kotlinx.android.synthetic.main.activity_stores.*
import kotlinx.android.synthetic.main.activity_stores.storesRV

class ShelfsActivity : AppCompatActivity() {

    lateinit  var shelfAdapter: ShelfAdapter

    lateinit var  shelfViewModel: ShelfViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shelfs)


        val storeId =  getIntent().getStringExtra("storeId").toString()
        Log.e("Store Id  shelf " , storeId.toString())



        val dao: WareHouseDao = WareHouseDB.getInstance(application).wareHouseDao
        val repository = WareHouseRepository(dao)
        val factory = ViewModelStoresFactory(repository)

        shelfRV.apply {
            layoutManager =  LinearLayoutManager(this@ShelfsActivity)
            shelfAdapter = ShelfAdapter(this@ShelfsActivity )
            adapter = shelfAdapter
            val dividers  = DividerItemDecoration(applicationContext , LinearLayoutManager.VERTICAL)
            addItemDecoration(dividers)
        }
        shelfViewModel = ViewModelProvider(this , factory).get(ShelfViewModel::class.java)
          val store  = storeId.toInt()
         shelfViewModel.getSelfofStore(storeId).observe(this  , {
             shelfAdapter.setStoreList(ArrayList(it))
             shelfAdapter.notifyDataSetChanged()
         })












    }
}