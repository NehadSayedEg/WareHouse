package com.nehad.warehouse.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName



@Entity(tableName = "item_table")
data class Item(
    @NonNull
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo( name="item_id")
    @SerializedName("item_id")
    var itemId: String ,

    @NonNull
    @ColumnInfo( name="created_by")
    @SerializedName("created_by")
    var createdBy: String ,

    @NonNull
    @ColumnInfo( name="created_date")
    @SerializedName("created_date")
    var  createdDate: String ,

    @NonNull
    @ColumnInfo( name="item_category")
    @SerializedName("item_category")
    var itemCategory: String ,

    @NonNull
    @ColumnInfo( name="item_class")
    @SerializedName("item_class")
    var itemClass: String,


    @ColumnInfo( name="item_name_ar")
    @SerializedName("item_name_ar")
    var itemNameAr: String  ,

    @NonNull
    @ColumnInfo( name="item_name_en")
    @SerializedName("item_name_en")
    var itemNameEn: String ,

    @NonNull
    @ColumnInfo( name="item_type_id")
    @SerializedName("item_type_id")
    var itemTypeId: String ,

    @NonNull
    @ColumnInfo( name="sync_status")
    @SerializedName("sync_status")
    var syncStatus: String ,

    @NonNull
    @ColumnInfo( name="sync_update_status")
    @SerializedName("sync_update_status")
    var syncUpdateStatus: String
)
{
    constructor() : this("",
        "","","","",""
        ,"" ,"" , "" , ""   )
}
