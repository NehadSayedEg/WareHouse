package com.nehad.warehouse.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity(tableName = "userGroup_table")
  data class UserGroup (

        @NonNull
        @PrimaryKey(autoGenerate = false)
        @ColumnInfo(name = "user_group_id")
        @SerializedName("user_group_id")
        var userGroupId: String  ="",


@ColumnInfo(name = "created_by")
@SerializedName("created_by")
var createdBy: String  ="",


@ColumnInfo(name = "created_date")
@SerializedName("created_date")
var createdDate: String  ="",


@ColumnInfo(name = "sync_status")
@SerializedName("sync_status")
var syncStatus: String  ="",



@ColumnInfo(name = "sync_update_status")
@SerializedName("sync_update_status")
var syncUpdateStatus: String  ="",



@ColumnInfo(name = "user_group_name_ar")
@SerializedName("user_group_name_ar")
var userGroupNameAr: String  ="",


@ColumnInfo(name = "user_group_name_en")
@SerializedName("user_group_name_en")
var userGroupNameEn: String =""





)
{


    constructor() : this("",
            "", "", "", "", "", "")
}




