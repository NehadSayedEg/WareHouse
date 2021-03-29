package com.nehad.warehouse.model

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable


@Entity(tableName = "Stock_header_doc_table")
data class StockheaderDoc(
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo( name="stock_header_doc_id")
    @SerializedName("stock_header_doc_id")
    var documentHeaderId : Long? = null ,


    @NonNull
    @ColumnInfo( name="document_name-header")
    @SerializedName("document_name-header")
    var documentName: String?= null ,

    @NonNull
    @ColumnInfo( name="document_type_id")
    @SerializedName("document_type_id")
    var documentTypeId: String?= null ,


    @NonNull
    @ColumnInfo( name="store_to_id")
    @SerializedName("store_to_id")
    var storeToId: String?= null  ,


    @NonNull
    @ColumnInfo( name="store_from_id")
    @SerializedName("store_from_id")
    var storeFromId: String?= null  ,


    @NonNull
    @ColumnInfo( name="created_by")
    @SerializedName("created_by")
    var createdBy: String?= null ,

    @Nullable
    @ColumnInfo( name="created_date")
    @SerializedName("created_date")
    var createdDate: String  ?= null ,

    @Nullable
    @ColumnInfo( name="sync_status")
    @SerializedName("sync_status")
    var syncStatus: String?= null ,

    @Nullable
    @ColumnInfo( name="sync_update_status")
    @SerializedName("sync_update_status")
    var syncUpdateStatus: String?= null ,

    @Nullable
    @ColumnInfo( name="update_date")
    @SerializedName("update_date")
    var updateDate: String?= null ,


    @Nullable
    @ColumnInfo( name="update_by")
    @SerializedName("update_by")
    var UpdateBy: String?= null

):Serializable{

    override fun toString(): String {
        return super.toString()
    }
}