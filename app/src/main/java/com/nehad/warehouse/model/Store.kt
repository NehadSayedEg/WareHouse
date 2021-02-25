package com.nehad.warehouse.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity(tableName = "store_table")

data class Store(

    @NonNull
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo( name="store_id")
    @SerializedName("store_id")
    var storeId: String= "" ,

    @NonNull
    @ColumnInfo( name="store_type_id")
    @SerializedName("store_type_id")
    var storeTypeId: String ="",

    @NonNull
    @ColumnInfo( name="created_by")
    @SerializedName("created_by")
    var createdBy: String ="",

    @NonNull
    @ColumnInfo( name="created_date")
    @SerializedName("created_date")
    var createdDate: String ="",

    @NonNull
    @ColumnInfo( name="store_gps_location")
    @SerializedName("store_gps_location")
    var storeGpsLocation: String ="",


    @NonNull
    @ColumnInfo( name="store_location")
    @SerializedName("store_location")
    var storeLocation: String ="",

    @NonNull
    @ColumnInfo( name="store_name_ar")
    @SerializedName("store_name_ar")
    var storeNameAr: String ="",

    @NonNull
    @ColumnInfo( name="store_name_en")
    @SerializedName("store_name_en")
    var storeNameEn: String ="",


    @NonNull
    @ColumnInfo( name="sync_status")
    @SerializedName("sync_status")
    var  syncStatus: String ="" ,

    @NonNull
    @ColumnInfo( name="sync_update_status")
    @SerializedName("sync_update_status")
    var syncUpdateStatus: String =""
){

//    constructor() : this("",
//        "","","","",""
//        ,"" ,"" , "" , ""    )
}
