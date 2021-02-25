package com.nehad.warehouse.ui.LoginActivity

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nehad.warehouse.database.WareHouseRepository
import com.nehad.warehouse.model.*


class LoginViewModel( private val repository: WareHouseRepository): ViewModel() {


    suspend fun insertUsers(users: List<User>) = repository.insertUsers(users)
    fun insertItems(items: List<Item>) = repository.insertItem(items)
    fun insertUserGroup(usergroups: List<UserGroup>) = repository.insertUserGroup(usergroups)
    fun insertSelf(shelves: List<Shelf>) = repository.insertSelf(shelves)
    fun insertStore(stores: List<Store>) = repository.insertStore(stores)
    fun insertDocumentType(documenttypes: List<DocumentType>) = repository.insertDocumentType(documenttypes)
    fun insertStoreType(storetypes: List<StoreType>) = repository.insertStoreType(storetypes)





    fun loginUser(userName: String, password: String): User {
        return repository.loginUser(userName, password)
    }

     fun login(userName: String, password: String): Boolean{
        return repository.login(userName , password)
    }


    fun getAllUsers() = repository.users
}


class LoginViewModelFactory(private val repository: WareHouseRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        try {
            val constructor = modelClass.getDeclaredConstructor(WareHouseRepository::class.java)
            return constructor.newInstance(repository)
        } catch (e: Exception) {
            Log.e("" , e.message.toString())
        }
        return super.create(modelClass)
    }
}