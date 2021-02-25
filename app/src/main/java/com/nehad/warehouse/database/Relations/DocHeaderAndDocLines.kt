package com.nehad.warehouse.database.Relations

import androidx.room.Embedded
import androidx.room.Relation
import com.nehad.warehouse.model.DocumentHeader
import com.nehad.warehouse.model.DocumentLines
import com.nehad.warehouse.model.DocumentType

data class DocHeaderAndDocLines(
        @Embedded
        val documentHeader: DocumentHeader,
        @Relation(parentColumn = "document_header_id", entityColumn = "document_header_id")
        val docLines: DocumentLines
)
