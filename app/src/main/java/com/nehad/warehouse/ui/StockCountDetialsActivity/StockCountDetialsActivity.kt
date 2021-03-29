package com.nehad.warehouse.ui.StockCountDetialsActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.nehad.warehouse.R
import com.nehad.warehouse.database.Dao.WareHouseDao
import com.nehad.warehouse.database.WareHouseDB
import com.nehad.warehouse.database.WareHouseRepository
import com.nehad.warehouse.model.StockCountDetail
import kotlinx.android.synthetic.main.activity_dash_board.*
import kotlinx.android.synthetic.main.activity_stock_count.*
import kotlinx.android.synthetic.main.activity_stock_count_detials.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class StockCountDetialsActivity : AppCompatActivity() {
    var docId :String = ""

    lateinit var stockDetailsViewModel: StockCountDetialViewModel
    lateinit  var stockDetailsAdapter: StockDetailsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock_count_detials)

        val dao: WareHouseDao = WareHouseDB.getInstance(application).wareHouseDao
        val repository = WareHouseRepository(dao)
        val factory = StockCountDetailViewModelFactory(repository)
        stockDetailsViewModel = ViewModelProvider(this, factory).get(StockCountDetialViewModel::class.java)





        docId =  getIntent().getStringExtra("docId").toString()

            Log.e("docId******", docId.toString())


        scanRv.apply {
             layoutManager =  LinearLayoutManager(this@StockCountDetialsActivity)
            stockDetailsAdapter = StockDetailsAdapter(this@StockCountDetialsActivity, dao)
            adapter =stockDetailsAdapter
         val dividers  = DividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL)
         addItemDecoration(dividers)
        }

        stockDetailsViewModel.getStockDetailByDocID(docId).observe( this@StockCountDetialsActivity , {
            stockDetailsAdapter.setStoreList(ArrayList(it))
            stockDetailsAdapter.notifyDataSetChanged()

        })

        scanEt.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                insertDetails()
               // scanEt.text.clear()



                stockDetailsViewModel.getStockDetailByDocID(docId).observe( this@StockCountDetialsActivity , {

                } )


            }

            override fun afterTextChanged(s: Editable?) {
            }

        })


    }

    private fun insertDetails() {
         if(scanEt.text.isNotEmpty()) {
             val barcode = scanEt.text.toString()
             Log.e("text ", barcode)



             GlobalScope.launch(Dispatchers.IO) {
                 val stockCountDetail = StockCountDetail()
                 stockCountDetail.documentHeaderId = docId.toLong()
                 val defaultValue:Int  = 1
                 stockCountDetail.qty = defaultValue.toDouble()
                 stockCountDetail.itemBarcode = barcode

                 val currentTime = System.currentTimeMillis()
                 val sdf = SimpleDateFormat("MMM dd,yyyy HH:mm:ss")
                 val resultdate = Date(currentTime)
                 val timeFormate = sdf.format(resultdate)
                 stockCountDetail.createdDate = currentTime.toString()
                 Log.e(" Timeformate ", timeFormate)

                 stockDetailsViewModel.insertStockCountDocDetails(stockCountDetail)

             }
             GlobalScope.launch(Dispatchers.Main) {
                   stockDetailsAdapter.notifyDataSetChanged()
                    scanEt.text.isEmpty()
               scanEt.focusable

                 stockDetailsViewModel.getStockDetailByDocID(docId).observe(this@StockCountDetialsActivity
                         , {


                     Log.e("  size  ",   it.get(2).qty.toString())
                     Log.e("  barcode  ",   it.get(2).itemBarcode.toString())



//                     Log.e(" Item barcode  ", it.get(0).itemBarcode)
//                     Log.e(" Item barcode  ", it.get(5).itemBarcode)

                     Log.e("  size  ", it.size.toString())

                 })
             }



         }



    }
}