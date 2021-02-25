package com.nehad.warehouse.database.Relations

import androidx.room.Embedded
import androidx.room.Relation
import com.nehad.warehouse.model.Shelf
import com.nehad.warehouse.model.Store

data class StoresAndShelfs(

   @Embedded
    val store: Store ,
   @Relation(parentColumn = "store_id", entityColumn = "store_id")
   val shelfs: List<Shelf>
)
