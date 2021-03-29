package com.nehad.warehouse.ui.reports

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nehad.warehouse.R
import com.nehad.warehouse.model.DocumentHeader
import com.nehad.warehouse.model.Store
import com.nehad.warehouse.ui.ShelfActivity.ShelfsActivity

class ReportAdapter (storesActivity: ReportsActivity) :RecyclerView.Adapter<ReportAdapter.ReportViewHolder>() {
    private var storeList = ArrayList<DocumentHeader>()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.report_item, parent, false)
        return ReportViewHolder(inflater)
    }


    fun setStoreList(stores: ArrayList<DocumentHeader>) {
        this.storeList = stores
        notifyDataSetChanged()

        Log.e("DocumentHeaderSize" , storeList.size.toString()+"")

    }

    override fun getItemCount(): Int {
        return storeList.size
    }
    override fun onBindViewHolder(holder: ReportViewHolder, position: Int) {
        holder.bind(storeList[position])
//        holder.itemView.setOnClickListener {
//            val context=holder.storeName.context
//
//            val  storeId = storeList.get(position).documentTypeId
//
//            val intent = Intent(context   , ShelfsActivity::class.java)
//            intent.putExtra("storeId", storeId)
//            context.startActivity(intent)
//        }


    }





    inner class ReportViewHolder(view: View): RecyclerView.ViewHolder(view){
        val storeName : TextView = itemView.findViewById(R.id.reportName)
        fun bind(documentHeader: DocumentHeader){
            storeName.text = documentHeader.documentTypeId


        }



    }




}
