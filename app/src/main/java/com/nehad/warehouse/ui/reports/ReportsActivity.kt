package com.nehad.warehouse.ui.reports

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.nehad.warehouse.R
import com.nehad.warehouse.database.Dao.WareHouseDao
import com.nehad.warehouse.database.WareHouseDB
import com.nehad.warehouse.database.WareHouseRepository
import com.nehad.warehouse.ui.storesActivity.StoresAdapter
import com.nehad.warehouse.ui.storesActivity.ViewModelStores
import com.nehad.warehouse.ui.storesActivity.ViewModelStoresFactory
import kotlinx.android.synthetic.main.activity_reports.*

class ReportsActivity : AppCompatActivity() {
    lateinit  var reportAdapter: ReportAdapter
    lateinit var  reportsViewModel: ReportsViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reports)


        val dao: WareHouseDao = WareHouseDB.getInstance(application).wareHouseDao
        val repository = WareHouseRepository(dao)
        val factory = ViewModelStoresFactory(repository)
        reports_rv.apply {

            layoutManager =  LinearLayoutManager(this@ReportsActivity)
            reportAdapter = ReportAdapter(this@ReportsActivity)
            adapter =reportAdapter
            val dividers  = DividerItemDecoration(applicationContext , LinearLayoutManager.VERTICAL)
            addItemDecoration(dividers)
        }


        reportsViewModel = ViewModelProvider(this , factory).get(ReportsViewModel::class.java)
        reportsViewModel.getAlldocuments().observe(this@ReportsActivity , Observer {
            Log.e(" Report Doc header " , it.size.toString())
            reportAdapter.setStoreList( ArrayList(it))
            reportAdapter.notifyDataSetChanged()

        })

        reportsViewModel.getAlldocuments().observe( this@ReportsActivity  ,
            Observer {

                Log.e("Size :" ,   it.size.toString())



            })

    }
}