package com.nehad.warehouse.ui.storesActivity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.nehad.warehouse.R
import com.nehad.warehouse.model.Store
import com.nehad.warehouse.ui.DashBoardActivity.DashBoardActivity
import com.nehad.warehouse.ui.ShelfActivity.ShelfsActivity
import kotlinx.android.synthetic.main.store_item.view.*

class StoresAdapter(storesActivity: StoresActivity) :RecyclerView.Adapter<StoresAdapter.StoresViewHolder>() {
    private var storeList = ArrayList<Store>()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoresViewHolder {
        val inflater =
                LayoutInflater.from(parent.context).inflate(R.layout.store_item, parent, false)
        return StoresViewHolder(inflater)
    }


    fun setStoreList(stores: ArrayList<Store>) {
        this.storeList = stores
        notifyDataSetChanged()

        Log.e("storeSize" , storeList.size.toString()+"")

    }

    override fun getItemCount(): Int {
        return storeList.size
    }
    override fun onBindViewHolder(holder: StoresViewHolder, position: Int) {
        holder.bind(storeList[position])
        holder.itemView.setOnClickListener {
            val context=holder.storeName.context

            val  storeId = storeList.get(position).storeId

            val intent = Intent(context   , ShelfsActivity::class.java)
            intent.putExtra("storeId", storeId)
            context.startActivity(intent)
        }


    }





    inner class StoresViewHolder(view: View):RecyclerView.ViewHolder(view){
        val storeName :TextView = itemView.findViewById(R.id.storeName)
        fun bind(store: Store){
            storeName.text = store.storeNameEn


        }


//        init {
//            itemView.setOnClickListener {
//                val postion: Int = adapterPosition
//
//                Toast.makeText(itemView.context , "You Clicked item # ${postion +1}  " , Toast.LENGTH_LONG).show()
//
//
//
//
//            }
//            }
    }




}
