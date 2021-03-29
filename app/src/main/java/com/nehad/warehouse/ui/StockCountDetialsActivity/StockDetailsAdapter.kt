package com.nehad.warehouse.ui.StockCountDetialsActivity

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.nehad.warehouse.R
import com.nehad.warehouse.database.Dao.WareHouseDao
import com.nehad.warehouse.model.StockCountDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class StockDetailsAdapter(stockCountDetialsActivity: StockCountDetialsActivity, private var dao: WareHouseDao) : RecyclerView.Adapter<StockDetailsAdapter.StockCountDetailViewHolder>() {
        private var countList = ArrayList<StockCountDetail>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockCountDetailViewHolder {
            val inflater =
                LayoutInflater.from(parent.context).inflate(R.layout.scan_item, parent, false)
            return StockCountDetailViewHolder(inflater)
        }


        fun setStoreList(stockCountDetail: ArrayList<StockCountDetail>) {
            this.countList = stockCountDetail
            notifyDataSetChanged()

            Log.e("storeSize", countList.size.toString() + "")


        }

        override fun getItemCount(): Int {
            return countList.size
        }
        override fun onBindViewHolder(holder: StockCountDetailViewHolder, position: Int) {
            holder.bind(countList[position])
            val pos = holder.bind(countList[position])

            holder.delete.setOnClickListener(View.OnClickListener {
                val postion = holder.adapterPosition
                GlobalScope.launch(Dispatchers.IO) {
                    val stockDetail: StockCountDetail = countList.get(position)
                    dao.deleteStockCountDetail(stockDetail)
                }


            })


            holder.decreaseQty.setOnClickListener(View.OnClickListener {
                Log.e(" you press decrease  btn", "press decrease  btn")


                val inputEditTextField = EditText(it.context)
                val dialog = AlertDialog.Builder(it.context)
                        .setTitle("Title")
                        .setMessage("Message")
                        .setView(inputEditTextField)
                        .setPositiveButton("OK") { _, _ ->
                            val editTextInput = inputEditTextField.text.toString()
                            val stockDetail: StockCountDetail = countList.get(position)
                            Log.e("stock postion qty ", stockDetail.qty.toString())

                            val valueET = inputEditTextField.text.toString()

                            GlobalScope.launch(Dispatchers.IO) {
                                val stockCountDetail: StockCountDetail = countList.get(position)
                                val stockqty = stockCountDetail.qty

                                val docNo: Long? = stockDetail.documentHeaderId
                                val updateQty = valueET.toFloat()

                                Log.e("updateQty value is: ", updateQty.toString())

                                val stockValue: Double = stockDetail.qty
                                Log.e("stock value is: ", stockDetail.qty.toString())

//                                Log.e("stock value is: "  , stockValue.toString() )

                                val newValue = stockValue?.minus(updateQty)
                                Log.e("newValue value is: ", newValue.toString())

                                stockDetail.qty = newValue
                                Log.e(" stockDetail.qty  value is: ", stockDetail.qty.toString())
                                val qty = newValue.toString()

                                val barcode: String = stockDetail.itemBarcode

                                val currentTime = System.currentTimeMillis()
                                val sdf = SimpleDateFormat("MMM dd,yyyy HH:mm:ss")
                                val resultdate = Date(currentTime)
                                val timeFormate = sdf.format(resultdate)
                                val updateTime = currentTime.toString()
                                stockDetail.updateDate = currentTime.toString()
                                dao.updateItem(barcode, newValue, docNo)

                                Log.e("Update function " , dao.updateItem(barcode, newValue, docNo).toString())
                                Log.e("stock value is  ********: ", stockDetail.qty.toString())

                                holder.itemQty.setText(qty)
                            }
                            countList.clear()
                            val docId = stockDetail.documentId.toString()
                             countList.addAll(dao.getStockDetailByID(docId))
                             notifyDataSetChanged()


                        }
                        .setNegativeButton("Cancel", null)
                        .create()
                dialog.show()


            })


            holder.increaseQty.setOnClickListener(View.OnClickListener {
                Log.e(" you press increase  btn", "press increase  btn")
                val inputEditTextField = EditText(it.context)
                val dialog = AlertDialog.Builder(it.context)
                        .setTitle("Title")
                        .setMessage("Message")
                        .setView(inputEditTextField)
                        .setPositiveButton("OK") { _, _ ->
                            val editTextInput = inputEditTextField.text.toString()
                            val stockDetail: StockCountDetail = countList.get(position)
                            Log.e("stock postion qty ", stockDetail.qty.toString())

                            val valueET = inputEditTextField.text.toString()
                            GlobalScope.launch(Dispatchers.IO) {
                                val docNo: Long? = stockDetail.documentHeaderId
                                val updateQty = valueET.toFloat()
                                Log.e("updateQty value is: ", updateQty.toString())
                                val stockValue: Double = stockDetail.qty
                                Log.e("stock value is: ", stockDetail.qty.toString())

                                val newValue = stockValue?.plus(updateQty)
                                Log.e("newValue value is: ", newValue.toString())

                              //  stockDetail.qty = newValue
                                Log.e(" stockDetail.qty  value is: ", stockDetail.qty.toString())
                                val qty = newValue.toString()
                                val barcode: String = stockDetail.itemBarcode
                                // dao.updateItem(barcode , newValue , docNo)
//                                stockDetail.itemBarcode = editTextInput
                                val currentTime = System.currentTimeMillis()
                                val sdf = SimpleDateFormat("MMM dd,yyyy HH:mm:ss")
                                val resultdate = Date(currentTime)
                                val timeFormate = sdf.format(resultdate)
                                val updateTime = currentTime.toString()
                              //stockDetail.updateDate = currentTime.toString()
                                dao.updateAddDialog(barcode , newValue , updateTime)

                              //  dao.updateItem(barcode, newValue, docNo)
                            }
                         //   holder.itemQty.setText(qty)

                            notifyDataSetChanged()
                            holder.itemQty.text = stockDetail.qty.toString()

                        }
                        .setNegativeButton("Cancel", null)
                        .create()
                dialog.show()


            })


//            holder.itemView.setOnClickListener {
//
//                val context=holder.itemBarcode.context
//                val con  = holder.itemQty.context
//                Log.e("date" , con.toString()+"")
//
//
//                val  docId = storeList.get(position).documentHeaderId.toString()
//                Log.e("docId" , docId.toString()+"")
//
//                val intent = Intent(context   , StockCountDetialsActivity::class.java)
//                intent.putExtra("docId", docId)
//                context.startActivity(intent)
//
//            }


        }





        inner class StockCountDetailViewHolder(view: View): RecyclerView.ViewHolder(view){
            val itemBarcode : TextView = itemView.findViewById(R.id.tvBarcode)
            val itemQty : TextView = itemView.findViewById(R.id.tvQty)

            val delete : ImageView = itemView.findViewById(R.id.ivDeleteItem)
            val increaseQty : ImageView = itemView.findViewById(R.id.ivInc)
            val decreaseQty : ImageView = itemView.findViewById(R.id.ivDec)

            fun bind(stockCountDetail: StockCountDetail){
                itemBarcode.text = stockCountDetail.itemBarcode
                itemQty.text = stockCountDetail.qty.toString()
            }


        }




    }
