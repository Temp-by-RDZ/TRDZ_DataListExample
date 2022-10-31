package com.trdz.task12as.model.data_source_server

import com.trdz.task12as.MyApp
import com.trdz.task12as.model.*
import com.trdz.task12as.model.data_source_server.data_user_rep.dto.RepositoryDTO
import com.trdz.task12as.model.data_source_server.data_users.dto.GitUsersDto
import com.trdz.task12as.model.data_source_server.data_users.dto.GitUsersDtoItem
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class ServerRetrofit: ADataSource {

	override fun loadUsers(): Single<ServersResultUser> = Single.create{
		val retrofit = MyApp.di.get(ServerRetrofitApi::class)
		try {
			val response = retrofit.getResponse().execute()
			it.onSuccess(responseFormation(response))
		}
		catch (error : Exception) {
			it.onError(responseFail(error))
		}
	}

	private fun responseFormation(response: Response<GitUsersDto>) : ServersResultUser {
		return if (response.isSuccessful) response.body()!!.run {
			val userList: MutableList<DataUser> = emptyList<DataUser>().toMutableList()
			forEachIndexed { index, user ->
				userList.add(ResponseMapper.mapToEntity(user, index)
				) }
			ServersResultUser(response.code(), dataUser = userList)
		}
		else ServersResultUser(response.code())
	}

	override fun loadDetails(name: String): Single<ServersResultRepository> = Single.create{
		val retrofit = MyApp.di.get(ServerRetrofitApi::class)
		try {
			val response = retrofit.getResponseSolo(name).execute()
			it.onSuccess(responseFormationDetails(response))
		}
		catch (error : Exception) {
			it.onError(responseFail(error))
		}
	}

	private fun responseFormationDetails(response: Response<GitUsersDtoItem>) : ServersResultRepository {
		return if (response.isSuccessful) response.body()!!.run {
			ServersResultRepository(response.code(), dataUserInfo = ResponseMapper.mapToEntity(this))
		}
		else ServersResultRepository(response.code())
	}

	override fun loadRepository(name: String): Single<ServersResultRepository> = Single.create{
		val retrofit = MyApp.di.get(ServerRetrofitApi::class)
		try {
			val response = retrofit.getResponseSoloRepo(name).execute()
			it.onSuccess(responseFormation(response))
		}
		catch (error : Exception) {
			it.onError(responseFail(error))
		}
	}

	private fun responseFormation(response: Response<RepositoryDTO>) : ServersResultRepository {
		return if (response.isSuccessful) response.body()!!.run {
			val repoList: MutableList<DataRepository> = emptyList<DataRepository>().toMutableList()
			forEach { repo ->
				repoList.add(ResponseMapper.mapToEntity(repo)
				) }
			ServersResultRepository(response.code(), repoList)
		}
		else ServersResultRepository(response.code())
	}

	private fun responseFail(error : Exception) = Throwable("Error code: -1, Internet connection lost or current data available:\n$error")
}
