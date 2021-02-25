package com.nehad.warehouse.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nehad.warehouse.database.Dao.WareHouseDao
import com.nehad.warehouse.model.*

@Database(entities = [ UserGroup::class  , User::class , Store::class  , StoreType::class , Item::class , DocumentType::class
    , Shelf::class  , Balance::class  ,DocumentHeader::class , DocumentLines::class], version = 3  ,exportSchema = false )
abstract class WareHouseDB : RoomDatabase(){

    abstract val wareHouseDao: WareHouseDao

    companion object{
        @Volatile
        private var INSTANCE : WareHouseDB? = null

        fun getInstance(context: Context) : WareHouseDB {
            synchronized(this){
                var instance : WareHouseDB? = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder( context.applicationContext ,
                        WareHouseDB::class.java , "WHMDatabase").fallbackToDestructiveMigration().build()

                }
                return instance
            }
        }
    }
}