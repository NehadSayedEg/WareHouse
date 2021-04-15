package com.nehad.warehouse.model

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity(tableName = "document_lines_table")
data class DocumentLines(

         //  "documentline_id": "1",
        @NonNull
        @PrimaryKey(autoGenerate = false)
        @ColumnInfo( name="document_line_id")
        @SerializedName("documentline_id")
        var documentLinesId : String  ="",


        //"documentline_seq": "1",

        @NonNull
        @ColumnInfo( name="document_header_id")
        @SerializedName("documentline_seq")
        var documentHeaderId : String  ="",


        //"item_id": "1",
        @NonNull
        @ColumnInfo( name="item_id")
        @SerializedName("item_id")
        var itemId : String= "" ,

       // "document_id": "1",
        @NonNull
        @ColumnInfo( name="document_id")
        @SerializedName("document_id")
        var documentId : String= "" ,

        //"qty": "100"
        @NonNull
        @ColumnInfo( name="qty")
        @SerializedName("qty")
        var qty : String= "" ,


        //"created_by": "1",
        @NonNull
        @ColumnInfo( name="created_by")
        @SerializedName("created_by")
        var createdBy: String ="",


        //"created_date": "2/17/21 18:52",
        @Nullable
        @ColumnInfo( name="created_date")
        @SerializedName("created_date")
        var createdDate: String  ="",


         //"sync_status": "0",
        @Nullable
        @ColumnInfo( name="sync_status")
        @SerializedName("sync_status")
        var syncStatus: String ="",



          //"sync_update_status": "0",
        @Nullable
        @ColumnInfo( name="sync_update_status")
        @SerializedName("sync_update_status")
        var syncUpdateStatus: String  ="",


        //"updated_date": "2/17/21 18:52",

        @Nullable
        @ColumnInfo( name="update_date")
        @SerializedName("update_date")
        var updateDate: String  ="" ,


        //"updated_by": "1",

        @Nullable
        @ColumnInfo( name="update_by")
        @SerializedName("update_by")
        var UpdateBy: String  =""

)



