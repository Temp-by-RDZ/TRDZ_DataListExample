package com.trdz.task12as.model.data_source_room.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity(tableName = "repos")
data class UserRepoEntity(
	@PrimaryKey
	@ColumnInfo(name = PRIMARY_KEY)
	val id: Long,
	val name: String,
	val publicity: Boolean,
	@ColumnInfo(name = FOREIGN_USER_KEY)
	val userId: Long
) {
	companion object {
		const val PRIMARY_KEY = "id"
		const val FOREIGN_USER_KEY = "userId"
	}
}
