package com.trdz.task12as.model.data_source_server

import com.trdz.task12as.base_utility.PACKAGE_LIST
import com.trdz.task12as.base_utility.PACKAGE_REPO
import com.trdz.task12as.base_utility.PACKAGE_SOLO
import com.trdz.task12as.model.data_source_server.data_user_rep.dto.RepositoryDTO
import com.trdz.task12as.model.data_source_server.data_users.dto.GitUsersDto
import com.trdz.task12as.model.data_source_server.data_users.dto.GitUsersDtoItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ServerRetrofitApi {
	@GET(PACKAGE_LIST)
	fun getResponse(): Call<GitUsersDto>

	@GET("$PACKAGE_LIST/{$PACKAGE_SOLO}")
	fun getResponseSolo(@Path(PACKAGE_SOLO) login: String): Call<GitUsersDtoItem>

	@GET("$PACKAGE_LIST/{$PACKAGE_SOLO}/$PACKAGE_REPO")
	fun getResponseSoloRepo(@Path(PACKAGE_SOLO) login: String): Call<RepositoryDTO>
}


