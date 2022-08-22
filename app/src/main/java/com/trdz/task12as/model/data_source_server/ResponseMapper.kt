package com.trdz.task12as.model.data_source_server

import com.trdz.task12as.base_utility.TYPE_CARD
import com.trdz.task12as.base_utility.TYPE_TITLE
import com.trdz.task12as.model.DataRepository
import com.trdz.task12as.model.DataUser
import com.trdz.task12as.model.data_source_server.data_user_rep.dto.RepositoryDTOItem
import com.trdz.task12as.model.data_source_server.data_users.dto.GitUsersDtoItem

object ResponseMapper {

	fun mapToEntity(dto: GitUsersDtoItem,index:Int): DataUser {
		return DataUser(
			id = dto.id,
			name = dto.login,
			subName = dto.type,
			iconUrl = dto.avatarUrl,
			type = TYPE_TITLE,
			group = index*2,
			state = 1
		)
	}
	fun mapToEntity(dto: RepositoryDTOItem): DataRepository {
		return DataRepository(
			id = dto.id,
			name = dto.name,
			type = TYPE_CARD,
			publicity = dto.private
		)
	}
}
