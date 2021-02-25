package com.nehad.warehouse.model

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "balance_table")
data class Balance(

        @NonNull
        @PrimaryKey(autoGenerate = false)
        @ColumnInfo( name="balance_id")
        @SerializedName("balance_id")
        var balanceId : String  ="",

        @ColumnInfo( name="store_id")
        @SerializedName("store_id")
        var storeId: String= "" ,

        @NonNull
        @ColumnInfo( name="item_id")
        @SerializedName("item_id")
        var itemId : String= "" ,

        @NonNull
        @ColumnInfo( name="balance_date")
        @SerializedName("balance_date")
        var balanceDate : String= "" ,



        @NonNull
        @ColumnInfo( name="balance_qty")
        @SerializedName("balance_qty")
        var balanceQty : String= "" ,


        @NonNull
        @ColumnInfo( name="created_by")
        @SerializedName("created_by")
        var createdBy: String ="",

        @Nullable
        @ColumnInfo( name="created_date")
        @SerializedName("created_date")
        var createdDate: String  ="",

        @Nullable
        @ColumnInfo( name="update_date")
        @SerializedName("update_date")
        var updateDate: String  ="" ,


        @Nullable
        @ColumnInfo( name="update_by")
        @SerializedName("update_by")
        var UpdateBy: String  =""

)