package com.nehad.warehouse.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "documentType_table")

data class DocumentType(
    @NonNull
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo( name="document_type_id")
    @SerializedName("document_type_id")
    var documentTypeId: String = "",

@NonNull
@ColumnInfo( name="created_by")
@SerializedName("created_by")
var createdBy: String ="",

@NonNull
@ColumnInfo( name="created_date")
@SerializedName("created_date")
var createdDate: String ="",

@NonNull
@ColumnInfo( name="document_subtype_name_ar")
@SerializedName("document_subtype_name_ar")
var documentSubtypeNameAr: String ="",

@NonNull
@ColumnInfo( name="document_subtype_name_en")
@SerializedName("document_subtype_name_en")
var documentSubtypeNameEn: String ="",

@NonNull

@ColumnInfo( name="document_type_name_ar")
@SerializedName("document_type_name_ar")
var documentTypeNameAr: String =""   ,

@NonNull
@ColumnInfo( name="document_type_name_en")
@SerializedName("document_type_name_en")
var documentTypeNameEn: String ="" ,

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
        ,"" ,"" , ""    )
}
