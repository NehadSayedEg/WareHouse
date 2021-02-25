package com.nehad.warehouse.database.Relations

import androidx.room.Embedded
import androidx.room.Relation
import com.nehad.warehouse.model.Balance
import com.nehad.warehouse.model.DocumentHeader
import com.nehad.warehouse.model.DocumentLines
import com.nehad.warehouse.model.Item

data class ItemAndBalance(
        @Embedded
        val item: Item,
        @Relation(parentColumn = "item_id", entityColumn = "item_id")
        val balance: Balance
)
