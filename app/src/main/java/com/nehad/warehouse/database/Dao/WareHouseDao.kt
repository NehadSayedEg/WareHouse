package com.nehad.warehouse.database.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.nehad.warehouse.database.Relations.StoresAndShelfs
import com.nehad.warehouse.database.Relations.UserAndUserGroup
import com.nehad.warehouse.model.*

@Dao
interface WareHouseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(users: List<User>)

    @Transaction
    @Query( "SELECT  * FROM  user_table")
    fun  getAllUsers():LiveData<List<User>>


    @Query("SELECT * FROM  user_table WHERE user_name_en LIKE :userName   AND user_password LIKE :password  LIMIT 1")
    fun loginUser(userName: String, password: String): User


    @Query("SELECT * FROM  user_table WHERE user_name_en = :userName   AND user_password = :password  LIMIT 1")
     fun login(userName: String, password: String): Boolean

    @Query("SELECT EXISTS (SELECT 1 FROM user_table WHERE user_name_en = :userName)")
    fun exists(userName: String): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserGroup(usergroups: List<UserGroup>)


    @Transaction
    @Query( "SELECT  * FROM  userGroup_table")
    fun  getAllUserGroups(): LiveData<List<UserGroup>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItem(items: List<Item>)

    @Transaction
    @Query( "SELECT  * FROM  item_table")
    fun  getAllItems(): LiveData<List<Item>>



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSelf(shelves: List<Shelf>)

    @Transaction
    @Query( "SELECT  * FROM  self_table  where  store_id =:store_id")
    fun  getSelfofStore( store_id :String): LiveData<List<Shelf>>


    @Transaction
    @Query( "SELECT  * FROM  store_table  where  store_id =:store_id")
    fun  getSelfWithStore( store_id :String): LiveData<List<StoresAndShelfs>>

    @Transaction
    @Query( "SELECT  * FROM  userGroup_table  where  user_group_id =:userGroupId")
    fun  getuserWithUserGroup( userGroupId :String): LiveData<List<UserAndUserGroup>>


    @Transaction
    @Query( "SELECT  * FROM  self_table")
    fun  getAllSelf():LiveData<List<Shelf>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStore(stores: List<Store>)

    @Transaction
    @Query( "SELECT  * FROM  store_table")
    fun  getAllStores():LiveData<List<Store>>

    @Transaction
    @Query("SELECT store_name_en FROM store_table WHERE store_name_en = :storeName")
    fun findStoreByName(storeName: String): LiveData<List<String>>

    @Transaction
    @Query("SELECT item_name_en FROM item_table WHERE item_name_en = :itemName")
    fun findItemByName(itemName: String): LiveData<List<String>>

    //get the store name by  user id  in the second update

    @Transaction
    @Query("SELECT store_name_en FROM store_table")
    fun getStoreByName(): LiveData<List<String>>


    @Transaction
    @Query("SELECT item_name_en FROM item_table")
    fun getItemByName(): LiveData<List<String>>

    @Transaction
    @Query("SELECT shelf_name_en FROM  self_table WHERE store_id =:storeId")
    fun getShelfByStoreID(storeId:String): LiveData<List<String>>

    @Transaction
    @Query("SELECT shelf_name_en FROM self_table  WHERE store_id = :store_id")
    fun getShelfByName(store_id: String): LiveData<List<String>>

    @Transaction
    @Query("SELECT store_id FROM store_table  WHERE store_name_en = :store_name_en LIMIT 1")
    fun getStoreIDByName(store_name_en: String): LiveData<List<String>>

    @Transaction
    @Query("SELECT  store_id  FROM  store_table WHERE store_name_en = :store_name_en LIMIT 1")
    fun getStoreID(store_name_en: String):String


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStoreType(storetypes: List<StoreType>)


    @Transaction
    @Query( "SELECT  * FROM  storeType_table")
    fun  getAllStoreType():LiveData<List<StoreType>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDocumentType(documenttypes: List<DocumentType>)

    @Transaction
    @Query( "SELECT  * FROM  documentType_table")
    fun  getAllDocumentType():LiveData<List<DocumentType>>

}