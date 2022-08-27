package com.trdz.task12as.model.data_source_room.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
	@PrimaryKey
	@ColumnInfo(name = PRIMARY_KEY)
	val id: Long,
	val name: String,
	val subName: String,
	val group: Int,
	val iconUrl: String
) {

	companion object {
		const val PRIMARY_KEY = "id"
	}
}
