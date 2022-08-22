package com.trdz.task12as.model.server_git_user

import com.trdz.task12as.base_utility.TYPE_TITLE
import com.trdz.task12as.model.DataUser
import com.trdz.task12as.model.server_git_user.dto.GitUsersDto
import com.trdz.task12as.model.server_git_user.dto.GitUsersDtoItem

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
}
