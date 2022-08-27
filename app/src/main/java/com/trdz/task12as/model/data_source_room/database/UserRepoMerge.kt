package com.trdz.task12as.model.data_source_room.database

import androidx.room.Embedded
import androidx.room.Relation

data class UserRepoMerge (
	@Embedded
	val userDBObject: UserEntity,
	@Relation(
		parentColumn = UserEntity.PRIMARY_KEY,
		entityColumn = UserRepoEntity.FOREIGN_USER_KEY
	)
	val repos: List<UserRepoEntity>)

