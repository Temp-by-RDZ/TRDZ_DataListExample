package com.trdz.task12as.model.data_source_room

import com.trdz.task12as.MyApp
import com.trdz.task12as.model.*
import com.trdz.task12as.model.data_source_room.database.UserDao
import com.trdz.task12as.model.data_source_room.database.UserEntity
import com.trdz.task12as.model.data_source_room.database.UserRepoEntity
import com.trdz.task12as.model.data_source_room.database.UserRepoMerge
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import java.lang.Exception
import kotlin.concurrent.thread

class InternalStorage: ADataSource, InternalData {

	private fun getData(): UserDao {
		return MyApp.getHistoryDao()
	}

	override fun saveUsers(users: List<DataUser>): Completable = Completable.create {
		thread {
			try {
				val userList: MutableList<UserEntity> = emptyList<UserEntity>().toMutableList()
				users.forEach { user ->
					userList.add(ResponseMapper.toStorage(user))
				}
				getData().insertAll(userList.toList())
				it.onComplete()
			}
			catch (e: Exception) {
				it.onError(Throwable(e.message))
			}
		}.start()

	}

	override fun loadUsers(): Single<ServersResultUser> = Single.create {
		try {
			val response = getData().getUsers()
			if (response.isEmpty()) it.onError(responseEmpty())
			else it.onSuccess(responseFormation(response))
		}
		catch (error: Exception) {
			it.onError(responseFail(error))
		}
	}

	private fun responseFormation(response: List<UserEntity>): ServersResultUser {
		val userList: MutableList<DataUser> = emptyList<DataUser>().toMutableList()
		response.forEach { entity ->
			userList.add(ResponseMapper.fromStorage(entity)
			)
		}
		return ServersResultUser(200, dataUser = userList)
	}


	override fun saveRepos(repos: List<DataRepository>, owner: Int): Completable = Completable.create {
		thread {
			try {
				val reposList: MutableList<UserRepoEntity> = emptyList<UserRepoEntity>().toMutableList()
				repos.forEach { repo ->
					reposList.add(ResponseMapper.toStorage(repo, owner))
				}

				getData().insertRepos(reposList.toList())
				it.onComplete()
			}
			catch (e: Exception) {
				it.onError(Throwable(e.message))
			}
		}
	}

	override fun loadDetails(name: String): Single<ServersResultRepository> = loadRepository(name)
	override fun loadRepository(name: String): Single<ServersResultRepository> = Single.create {
		try {
			val response = getData().getUserWithRepos(name)
			if (response.repos.isEmpty()) it.onError(responseEmpty())
			else it.onSuccess(responseFormation(response))
		}
		catch (error: Exception) {
			it.onError(responseFail(error))
		}
	}

	private fun responseFormation(response: UserRepoMerge): ServersResultRepository {
		val repoList: MutableList<DataRepository> = emptyList<DataRepository>().toMutableList()
		response.repos.forEach { repo ->
			repoList.add(ResponseMapper.fromStorage(repo)
			)
		}
		return ServersResultRepository(200, repoList,ResponseMapper.fromStorageInfo(response.user))
	}

	private fun responseEmpty() = Throwable("Error code: 404, Data lost\n")
	private fun responseFail(error: Exception) = Throwable("Error code: -1, Internal storage unavailable:\n$error")
}