package com.nehad.warehouse.database.Relations

import androidx.room.Embedded
import androidx.room.Relation
import com.nehad.warehouse.model.Shelf
import com.nehad.warehouse.model.Store
import com.nehad.warehouse.model.User
import com.nehad.warehouse.model.UserGroup

data class UserAndUserGroup (
    @Embedded
    val userGroup: UserGroup,
    @Relation(parentColumn = "user_group_id", entityColumn = "user_group_id")
    val users:List<User>
        )
