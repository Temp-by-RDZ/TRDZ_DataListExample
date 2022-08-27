package com.trdz.task12as.model.data_source_room

import com.trdz.task12as.base_utility.TYPE_CARD
import com.trdz.task12as.base_utility.TYPE_TITLE
import com.trdz.task12as.model.DataRepository
import com.trdz.task12as.model.DataUser
import com.trdz.task12as.model.DataUserInfo
import com.trdz.task12as.model.data_source_room.database.UserEntity
import com.trdz.task12as.model.data_source_room.database.UserRepoEntity
import com.trdz.task12as.model.data_source_server.data_user_rep.dto.RepositoryDTOItem

object ResponseMapper {

	fun toStorage(data: DataUser): UserEntity {
		return with(data) {
			UserEntity(
				id = id.toLong(),
				name = name,
				subName = subName,
				iconUrl = iconUrl,
				group = group,
			)
		}
	}

	fun toStorage(data: DataRepository, userId: Int): UserRepoEntity {
		return with(data) {
			UserRepoEntity(
			id = id.toLong(),
			name = name,
			publicity = publicity,
			userId = userId.toLong()
		)
		}
	}

	fun fromStorage(entity: UserEntity): DataUser {
		return with(entity) {
			DataUser(
				id = id.toInt(),
				name = name,
				subName = subName,
				iconUrl = iconUrl,
				type = TYPE_TITLE,
				group = group,
				state = 1
			)
		}
	}

	fun fromStorageInfo(entity: UserEntity): DataUserInfo {
		return with(entity) {
			DataUserInfo(
				id = id.toInt(),
				name = name,
				subName = subName,
				iconUrl = iconUrl,
			)
		}
	}

	fun fromStorage(entity: UserRepoEntity): DataRepository {
		return with(entity) {DataRepository(
			id = id.toInt(),
			name = name,
			type = TYPE_CARD,
			publicity = publicity
		)}
	}

}
