package com.nehad.warehouse.database.Relations

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Relation
import com.google.gson.annotations.SerializedName
import com.nehad.warehouse.model.DocumentHeader
import com.nehad.warehouse.model.DocumentLines
import com.nehad.warehouse.model.StockCountDetail
import com.nehad.warehouse.model.StockheaderDoc

data class StockCountHeaderAndDetials(
    @Embedded
    val stockheaderDoc:StockheaderDoc ,


    @Relation(parentColumn = "stock_header_doc_id", entityColumn = "stock_header_doc_id")
    val stockCountDetails:List<StockCountDetail>

)

