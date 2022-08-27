package com.trdz.task12as.model.data_source_room.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class], version = 1)
abstract class DataBase: RoomDatabase() {
	abstract fun userDao(): UserDao
}