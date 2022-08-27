package com.trdz.task12as.model.data_source_server

import com.trdz.task12as.base_utility.TYPE_CARD
import com.trdz.task12as.base_utility.TYPE_TITLE
import com.trdz.task12as.model.DataRepository
import com.trdz.task12as.model.DataUser
import com.trdz.task12as.model.data_source_server.data_user_rep.dto.RepositoryDTOItem
import com.trdz.task12as.model.data_source_server.data_users.dto.GitUsersDtoItem

object ResponseMapper {

	fun mapToEntity(dto: GitUsersDtoItem, index: Int): DataUser {
		return with(dto) {
			DataUser(
				id = id,
				name = login,
				subName = type,
				iconUrl = avatarUrl,
				type = TYPE_TITLE,
				group = index * 2,
				state = 1
			)
		}
	}

	fun mapToEntity(dto: RepositoryDTOItem): DataRepository {
		return with(dto) {
			DataRepository(
				id = id,
				name = name,
				type = TYPE_CARD,
				publicity = private
			)
		}
	}
}
