package com.nehad.warehouse.model

import com.google.gson.annotations.SerializedName

data class Data(

    @SerializedName("document_types")
    var documenttypes: List<DocumentType>,


    @SerializedName("Items")
    var items: List<Item>,


    @SerializedName("Shelfs")
    var shelves: List<Shelf>,


    @SerializedName("store_types")
    var storetypes: List<StoreType>,


    @SerializedName("Stores")
    var stores: List<Store>,


    @SerializedName("user_group")
    var usergroup: List<UserGroup>,


    @SerializedName("Users")
    var users: List<User>

)





