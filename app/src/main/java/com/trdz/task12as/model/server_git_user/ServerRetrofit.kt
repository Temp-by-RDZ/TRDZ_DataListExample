package com.trdz.task12as.model.server_git_user

import android.util.Log
import com.trdz.task12as.MyApp
import com.trdz.task12as.model.DataSource
import com.trdz.task12as.model.DataUser
import com.trdz.task12as.model.ServersResult
import com.trdz.task12as.model.server_git_user.dto.GitUsersDto
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import java.lang.Exception

class ServerRetrofit: DataSource {

	override fun load(): Single<ServersResult> = Single.create{
		val retrofit = MyApp.getRetrofit()
		try {
			val response = retrofit.getResponse().execute()
			it.onSuccess(responseFormation(response))
		}
		catch (error : Exception) {
			it.onError(responseFail(error))
		}
	}

	private fun responseFormation(response: Response<GitUsersDto>) : ServersResult {
		return if (response.isSuccessful) response.body()!!.run {
			val userList: MutableList<DataUser> = emptyList<DataUser>().toMutableList()
			forEachIndexed { index, user ->
				userList.add(ResponseMapper.mapToEntity(user,index)
				) }
			ServersResult(response.code(), dataUser = userList)
		}
		else ServersResult(response.code())
	}

	private fun responseFail(error : Exception) = Throwable("Error code: -1, Internet connection lost or current data available:\n$error")
}
