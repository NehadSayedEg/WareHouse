package com.nehad.warehouse.model

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "stock_count_detail_table")
data class StockCountDetail(
//    @NonNull
//    @PrimaryKey(autoGenerate = false)
//    @ColumnInfo( name="stock_count_detail")
//    @SerializedName("stock_count_detail")
//    var documentDetailsId : String  ="",
        @ForeignKey(entity = StockheaderDoc::class, parentColumns = ["stock_header_doc_id"], childColumns = ["stock_header_doc_id"])
        @NonNull
        @PrimaryKey(autoGenerate = false)
        @ColumnInfo(name = "item_barcode")
        @SerializedName("item_barcode")
        var itemBarcode: String = "",

        @NonNull
        @ColumnInfo(name = "stock_header_doc_id")
        @SerializedName("stock_header_doc_id")
        var documentHeaderId: Long?  = null,

        @NonNull
        @ColumnInfo(name = "item_id")
        @SerializedName("item_id")
        var itemId: String? = null,



        @NonNull
        @ColumnInfo(name = "document_id")
        @SerializedName("document_id")
        var documentId: String? = null,


        @NonNull
        @ColumnInfo(name = "qty")
        @SerializedName("qty")
        var qty: Double = 0.0,


        @NonNull
        @ColumnInfo(name = "created_by")
        @SerializedName("created_by")
        var createdBy: String? = null,

        @Nullable
        @ColumnInfo(name = "created_date")
        @SerializedName("created_date")
        var createdDate: String? = null,

        @Nullable
        @ColumnInfo(name = "sync_status")
        @SerializedName("sync_status")
        var syncStatus: String? = null,

        @Nullable
        @ColumnInfo(name = "sync_update_status")
        @SerializedName("sync_update_status")
        var syncUpdateStatus: String? = null,

        @Nullable
        @ColumnInfo(name = "update_date")
        @SerializedName("update_date")
        var updateDate: String? = null,


        @Nullable
        @ColumnInfo(name = "update_by")
        @SerializedName("update_by")
        var UpdateBy: String? = null

): Serializable {

        override fun toString(): String {
                return super.toString()
        }
}
