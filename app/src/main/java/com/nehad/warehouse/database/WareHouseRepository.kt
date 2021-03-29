package com.nehad.warehouse.database

import androidx.lifecycle.LiveData
import com.nehad.warehouse.database.Dao.WareHouseDao
import com.nehad.warehouse.database.Relations.UserAndUserGroup
import com.nehad.warehouse.model.*

class WareHouseRepository (private val  dao: WareHouseDao) {
    val users  =  dao.getAllUsers()
    val stores  =  dao.getAllStores()
    val items  =  dao.getAllItems()
    val shelfs  =  dao.getAllSelf()
    val userGroups  =  dao.getAllUserGroups()
    val documentTypes  =  dao.getAllDocumentType()
    val storeTypes  =  dao.getAllStoreType()

    val documentHeader  =  dao.getDocumentHeader()

    val stockheaderDoc = dao.getStockCountDocHeader()
    val stockCountDetail = dao.getStockCountDoccDetails()



    suspend fun insertUsers(users:List<User>){
        dao.insertUsers(users)

    }

    fun  getSelfofStore( store_id :String):LiveData<List<Shelf>>{
        return dao.getSelfofStore(store_id)
    }

    fun  getuserWithUserGroup( userGroupId :String):LiveData<List<UserAndUserGroup>>{
        return  dao.getuserWithUserGroup(userGroupId)
    }

     fun login(userName: String, password: String): Boolean {

        return dao.login(userName , password)
    }


    fun loginUser(userName: String, password: String): User {
        return dao.loginUser(userName, password)
    }



    fun insertUserGroup(usergroups: List<UserGroup>){
        dao.insertUserGroup(usergroups)

    }

    fun insertItem(items: List<Item>){
        dao.insertItem(items)
    }


    fun insertSelf(shelves: List<Shelf>){
        dao.insertSelf(shelves)
    }

    fun insertStore(stores: List<Store>){
        dao.insertStore(stores)
    }
    fun insertStoreType(storetypes: List<StoreType>){
        dao.insertStoreType(storetypes)
    }
    fun getStoreByName(): LiveData<List<String>>{
         return dao.getStoreByName()
    }
    fun getShelfByName(store_id: String): LiveData<List<String>>{
        return dao.getShelfByName(store_id)
    }
    fun getStoreIDByName(shelf_name_en: String): LiveData<List<String>> {
        return dao.getStoreIDByName(shelf_name_en)
    }

    fun getStoreID(store_name_en: String): String{
        return dao.getStoreID(store_name_en)

    }
    fun findItemByName(itemName: String): LiveData<List<String>>{
        return dao.findItemByName(itemName)

    }

    fun getItemByName(): LiveData<List<String>>{
        return dao.getItemByName()

    }

    fun getShelfByStoreID(storeId:String): LiveData<List<String>>{
        return dao.getShelfByStoreID(storeId)
    }


    fun insertDocumentType(documenttypes: List<DocumentType>){
        dao.insertDocumentType(documenttypes)
    }

    fun insertDocumentHeader(documentHeader: DocumentHeader){
        return dao.insertDocumentHeader(documentHeader)
    }
    fun insertStockCountDocHeader(stockheaderDoc: StockheaderDoc){
        dao.insertStockCountDocHeader(stockheaderDoc)
    }

    fun insertStockCountDocDetails(stockCountDetail: StockCountDetail){
         dao.insertStockCountDocDetails(stockCountDetail)
    }

    fun deleteStockCountDetail(stockCountDetail: StockCountDetail){
        dao.deleteStockCountDetail(stockCountDetail)

    }

//    fun updateAddDialog(barcode: String, value: Float , update: String)
//    {
//       return dao.updateAddDialog(barcode , value ,update)
//
//    }



    fun  getStockCountDocHeader():LiveData<List<StockheaderDoc>>{
        return dao.getStockCountDocHeader()
    }


    fun  getStockCountDoccDetails():LiveData<List<StockCountDetail>>{
        return dao.getStockCountDoccDetails()

    }

    fun getStockDetailByDocID(docId: String):LiveData<List<StockCountDetail>>{
        return dao.getStockDetailByDocID(docId)
    }





}