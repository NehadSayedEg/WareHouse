package com.nehad.warehouse.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity(tableName = "self_table")

data class Shelf(

    @NonNull
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo( name="shelf_id")
    @SerializedName("shelf_id")
    var shelfId: String= "",

    @NonNull
    @ColumnInfo( name="created_by")
    @SerializedName("created_by")
    var createdBy: String ="",

    @NonNull
    @ColumnInfo( name="created_date")
    @SerializedName("created_date")
    var createdDate: String ="",

//    @NonNull
    @ColumnInfo( name="shelf_name_ar")
    @SerializedName("shelf_name_ar")
    var shelfNameAr: String ="",

    @NonNull
    @ColumnInfo( name="shelf_name_en")
    @SerializedName("shelf_name_en")
    var shelfNameEn: String ="",

    @NonNull
    @ColumnInfo( name="store_id")
    @SerializedName("store_id")
    var storeId: String ="",

    @NonNull
    @ColumnInfo( name="sync_status")
    @SerializedName("sync_status")
    var syncStatus: String ="",

    @NonNull
    @ColumnInfo( name="sync_update_status")
    @SerializedName("sync_update_status")
    var syncUpdateStatus: String =""
)
{
    constructor() : this("",
        "","","","",""
        ,"" ,""  )
}
