package com.nehad.warehouse.ui.StockCountActivity

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nehad.warehouse.R
import com.nehad.warehouse.model.StockheaderDoc
import com.nehad.warehouse.ui.StockCountDetialsActivity.StockCountDetialsActivity

class StockCountHeaderAdapter (stockCountActivity: StockCountActivity) : RecyclerView.Adapter<StockCountHeaderAdapter.StockCountViewHolder>() {
    private var storeList = ArrayList<StockheaderDoc>()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockCountViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.stock_count_doc_header_item, parent, false)
        return StockCountViewHolder(inflater)
    }


    fun setStoreList(stockheaderDoc: ArrayList<StockheaderDoc>) {
        this.storeList = stockheaderDoc
        notifyDataSetChanged()

        Log.e("storeSize" , storeList.size.toString()+"")


    }

    override fun getItemCount(): Int {
        return storeList.size
    }
    override fun onBindViewHolder(holder: StockCountViewHolder, position: Int) {
        holder.bind(storeList[position])
        holder.itemView.setOnClickListener {

            val context=holder.docName.context
            val con  = holder.docDate.context
            Log.e("date" , con.toString()+"")


            val  docId = storeList.get(position).documentHeaderId.toString()
            Log.e("docId" , docId.toString()+"")

            val intent = Intent(context   , StockCountDetialsActivity::class.java)
            intent.putExtra("docId", docId)
            context.startActivity(intent)

        }


    }





    inner class StockCountViewHolder(view: View): RecyclerView.ViewHolder(view){
        val docName : TextView = itemView.findViewById(R.id.DocName)
        val docDate : TextView = itemView.findViewById(R.id.docDate)


        fun bind(stockheaderDoc: StockheaderDoc){
            docName.text = stockheaderDoc.documentName
            docDate.text = stockheaderDoc.createdDate
        }


    }




}
