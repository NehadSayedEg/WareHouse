package com.nehad.warehouse.database.Relations

import androidx.room.Embedded
import androidx.room.Relation
import com.nehad.warehouse.model.DocumentLines
import com.nehad.warehouse.model.Item
import com.nehad.warehouse.model.User
import com.nehad.warehouse.model.UserGroup

data class DocumentLineAndItems(

    @Embedded
    val documentLines: DocumentLines,
    @Relation(parentColumn = "item_id", entityColumn = "item_id")
    val items:List<Item>

)
