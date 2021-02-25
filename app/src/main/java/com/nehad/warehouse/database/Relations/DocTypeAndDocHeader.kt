package com.nehad.warehouse.database.Relations

import androidx.room.Embedded
import androidx.room.Relation
import com.nehad.warehouse.model.DocumentHeader
import com.nehad.warehouse.model.DocumentType
import com.nehad.warehouse.model.Shelf
import com.nehad.warehouse.model.Store

data class DocTypeAndDocHeader(
        @Embedded
        val documentType: DocumentType,
        @Relation(parentColumn = "document_type_id", entityColumn = "document_type_id")
        val docHeader: List<DocumentHeader>
)
