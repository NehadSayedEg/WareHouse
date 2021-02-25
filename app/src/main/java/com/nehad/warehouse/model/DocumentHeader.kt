package com.nehad.warehouse.model

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "document_header_table")
data class DocumentHeader(
        @NonNull
        @PrimaryKey(autoGenerate = false)
        @ColumnInfo( name="document_header_id")
        @SerializedName("document_header_id")
        var documentHeaderId : String  ="",

        @NonNull
        @ColumnInfo( name="document_type_id")
        @SerializedName("document_type_id")
        var documentTypeId: String = "",


        @NonNull
        @ColumnInfo( name="store_to_id")
        @SerializedName("store_to_id")
        var storeToId: String= "" ,


        @NonNull
        @ColumnInfo( name="store_from_id")
        @SerializedName("store_from_id")
        var storeFromId: String= "" ,


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
        @ColumnInfo( name="update_date")
        @SerializedName("update_date")
        var updateDate: String  ="" ,


        @Nullable
        @ColumnInfo( name="update_by")
        @SerializedName("update_by")
        var UpdateBy: String  =""

)