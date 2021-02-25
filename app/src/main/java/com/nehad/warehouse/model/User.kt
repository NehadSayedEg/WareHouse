package com.nehad.warehouse.model

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity(tableName = "user_table")
data  class User(
    @NonNull
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo( name="user_id")
    @SerializedName("user_id")
    var userId: String  ="",

    @NonNull
    @ColumnInfo( name="created_by")
    @SerializedName("created_by")
    var createdBy: String ="",

    @Nullable
    @ColumnInfo( name="created_date")
    @SerializedName("created_date")
    var createdDate: String  ="",

    @Nullable
    @ColumnInfo( name="sync_status")
    @SerializedName("sync_status")
    var syncStatus: String ="",

    @Nullable
    @ColumnInfo( name="sync_update_status")
    @SerializedName("sync_update_status")
    var syncUpdateStatus: String  ="",

    @Nullable
    @ColumnInfo( name="user_group_id")
    @SerializedName("user_group_id")
    var userGroupId: String  ="",


    @Nullable
    @ColumnInfo( name="user_name_ar")
    @SerializedName("user_name_ar")
    var userNameAr: String  ="",

    @Nullable
    @ColumnInfo( name="user_name_en")
    @SerializedName("user_name_en")
    var userNameEn: String  ="",

    @Nullable
    @ColumnInfo( name="user_password")
    @SerializedName("user_password")
    var userPassword: String =""


)
{
    constructor() : this("",
            "","","","",""
            ,"" ,"" , ""  )

}



