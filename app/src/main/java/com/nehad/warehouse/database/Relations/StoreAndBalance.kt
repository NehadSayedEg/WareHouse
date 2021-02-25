package com.nehad.warehouse.database.Relations

import androidx.room.Embedded
import androidx.room.Relation
import com.nehad.warehouse.model.Balance
import com.nehad.warehouse.model.Item
import com.nehad.warehouse.model.Store

data class StoreAndBalance(
        @Embedded
        val store: Store,
        @Relation(parentColumn = "store_id", entityColumn = "store_id")
        val balance: Balance
)
