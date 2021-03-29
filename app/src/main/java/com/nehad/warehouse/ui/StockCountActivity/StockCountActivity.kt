package com.nehad.warehouse.ui.StockCountActivity

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.nehad.warehouse.R
import com.nehad.warehouse.database.Dao.WareHouseDao
import com.nehad.warehouse.database.WareHouseDB
import com.nehad.warehouse.database.WareHouseRepository
import com.nehad.warehouse.model.StockheaderDoc
import kotlinx.android.synthetic.main.activity_stock_count.*
import kotlinx.android.synthetic.main.activity_stores.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList

class StockCountActivity : AppCompatActivity() {

    lateinit var stockCountViewModel: StockCountViewModel
    lateinit  var stockHeaderAdapter: StockCountHeaderAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock_count)

        val dao: WareHouseDao = WareHouseDB.getInstance(application).wareHouseDao
        val repository = WareHouseRepository(dao)
        val factory = StockCountViewModelFactory(repository)
        stockCountViewModel = ViewModelProvider(this, factory).get(StockCountViewModel::class.java)





        stockCountRV.apply {
           // layoutManager =  LinearLayoutManager(this@StockCountActivity)
            layoutManager =  GridLayoutManager(applicationContext, 2, GridLayoutManager.VERTICAL, false)
            stockHeaderAdapter = StockCountHeaderAdapter(this@StockCountActivity)
            adapter =stockHeaderAdapter
//            val dividers  = DividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL)
//            addItemDecoration(dividers)
        }
        stockCountViewModel.stockDocumentHeaders().observe(this@StockCountActivity, Observer {
            stockHeaderAdapter.setStoreList(ArrayList(it))
            stockHeaderAdapter.notifyDataSetChanged()


        })




        addCount.setOnClickListener {

            val inputEditTextField = EditText(this@StockCountActivity)
            val dialog = AlertDialog.Builder(this@StockCountActivity)
                .setTitle("")
                .setMessage("Enter the document Name")
                .setView(inputEditTextField)
                .setPositiveButton("Add") { _, _ ->
                    val editTextInput = inputEditTextField .text.toString()

                    GlobalScope.launch(Dispatchers.IO) {

                        var stockCountHeader = StockheaderDoc()

                        stockCountHeader.documentName = editTextInput

                        val  currentTime = System.currentTimeMillis()
                        val sdf = SimpleDateFormat("MMM dd,yyyy HH:mm:ss")
                        val resultdate = Date(currentTime)
                        val timeFormate = sdf.format(resultdate)

                        Log.e(" Timeformate " ,timeFormate )



                         stockCountHeader.createdDate = currentTime.toString()
                        stockCountViewModel.insertStockCountDocHeader(stockCountHeader)
                    }


                    GlobalScope.launch(Dispatchers.Main) {
              stockCountViewModel.stockDocumentHeaders().observe(this@StockCountActivity, Observer {
                  Log.e("Size :", it.size.toString())
//                  Log.e("name  :", it.get(0).documentName.toString())
//                  Log.e("id  :", it.get(0).documentHeaderId.toString())


              })



                    }

                    Log.d("editext value is:", editTextInput.toString())
                }
                .setNegativeButton("Cancel", null)
                .create()
            dialog.show()
        }
    }
}