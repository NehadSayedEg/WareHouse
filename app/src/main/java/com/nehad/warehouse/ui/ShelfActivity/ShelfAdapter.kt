package com.nehad.warehouse.ui.ShelfActivity

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nehad.warehouse.R
import com.nehad.warehouse.model.Shelf

class ShelfAdapter (shelfsActivity: ShelfsActivity) : RecyclerView.Adapter<ShelfAdapter.ShelfViewHolder>() {
    private var shelfList = ArrayList<Shelf>()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShelfViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.shelf_item, parent, false)
        return ShelfViewHolder(inflater)
    }


    fun setStoreList(shelfs: ArrayList<Shelf>) {
        this.shelfList = shelfs
        notifyDataSetChanged()

        Log.e("storeSize" , shelfList.size.toString()+"")

    }

    override fun getItemCount(): Int {
        return shelfList.size
    }
    override fun onBindViewHolder(holder: ShelfViewHolder, position: Int) {
        holder.bind(shelfList[position])
        holder.itemView.setOnClickListener {
            val context=holder.storeName.context

            val  storeId = shelfList.get(position).storeId

            val intent = Intent(context   , ShelfsActivity::class.java)
            intent.putExtra("storeId", storeId)
            context.startActivity(intent)
        }


    }





    inner class ShelfViewHolder(view: View): RecyclerView.ViewHolder(view){
        val storeName : TextView = itemView.findViewById(R.id.shelfName)
        fun bind(shelf: Shelf){
            storeName.text = shelf.shelfNameEn


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
