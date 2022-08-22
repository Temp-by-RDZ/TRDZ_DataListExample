package com.trdz.task12as.model.server_git_user

import com.trdz.task12as.base_utility.PACKAGE_LIST
import com.trdz.task12as.base_utility.PACKAGE_SOLO
import com.trdz.task12as.model.server_git_user.dto.GitUsersDto
import com.trdz.task12as.model.server_git_user.dto.GitUsersDtoItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ServerRetrofitApi {
	@GET(PACKAGE_LIST)
	fun getResponse(): Call<GitUsersDto>

	@GET("$PACKAGE_LIST/{$PACKAGE_SOLO}")
	fun getResponseSolo(@Path(PACKAGE_SOLO) login: String): Call<GitUsersDtoItem>
}


