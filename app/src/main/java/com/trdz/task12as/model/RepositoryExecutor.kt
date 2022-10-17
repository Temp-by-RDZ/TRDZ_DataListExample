package com.trdz.task12as.model

import android.os.Build
import android.util.Log
import com.trdz.task12as.base_utility.IN_BASIS
import com.trdz.task12as.base_utility.IN_SERVER
import com.trdz.task12as.base_utility.IN_STORAGE
import com.trdz.task12as.base_utility.TYPE_TITLE
import com.trdz.task12as.model.data_source_basis.DataSourceBasis
import com.trdz.task12as.model.data_source_room.InternalStorage
import com.trdz.task12as.model.data_source_server.ServerRetrofit
import io.reactivex.rxjava3.core.Single

class RepositoryExecutor {

	private lateinit var dataSource: ADataSource
	private lateinit var currentData: MutableList<DataUser>
	private val internalStorage: InternalStorage = InternalStorage()

	fun dataUpdate(data: MutableList<DataUser>) {
		currentData = data
	}

	fun setSource(index: Int) {
		when (index) {
			IN_BASIS -> dataSource = DataSourceBasis()
			IN_STORAGE ->  dataSource = InternalStorage()
			IN_SERVER ->  dataSource = ServerRetrofit()
		}
	}

	fun update() {
		Log.d("@@@", "Rep - User Saving...")
		internalStorage.saveUsers(currentData).subscribe({
			Log.d("@@@", "Rep - ...Done")},{
			Log.d("@@@", "Rep - ...Failed $it")})
	}

	fun updateRepo(repos: List<DataRepository>,owner:Int) {
		Log.d("@@@", "Rep - Repo Saving...")
		internalStorage.saveRepos(repos,owner).subscribe({
			Log.d("@@@", "Rep - ...Done")},{
			Log.d("@@@", "Rep - ...Failed $it")})
	}

	fun getUserRepository(name: String) : Single<ServersResultRepository> {
		return dataSource.loadRepository(name)
	}

	fun getInitUsers() : Single<ServersResultUser> {
		return dataSource.loadUsers()
	}

	fun getUserInfo(name: String) : Single<ServersResultRepository> {
		return dataSource.loadDetails(name)
	}

	fun getUsers() : List<DataUser> {
		return currentData
	}

	fun addAt() {
		currentData.add(DataUser("Новый Лидер", "Новая группа", 0,"",TYPE_TITLE,getNewGroup()))
	}

	fun removeAt(position: Int) {
		currentData.removeAt(position)
	}

	fun removeGroup(group: Int): Int {
		var count = 1
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
			currentData.forEach { tek ->
				if (tek.group == group+1) {
					count++
				}
			}
			currentData.removeIf{ element ->
				element.group == group+1}
			Log.d("@@@", currentData.toString())
		}
		else {
			val subData = mutableListOf<DataUser>()
			currentData.forEach { tek ->
				if (tek.group == group+1) {
					count++
				}
				else {
					subData.add(tek)
				}
			}
			currentData = subData
		}

		return count
	}

	fun changeStateAt(data: DataUser, position: Int, state: Int): Int {
		currentData[position].state = state
		return setState(state*2, data.group + 1)
	}

	private fun getNewGroup(): Int {
		return ((currentData[currentData.size-1].group+1)/2+1)*2+1
	}

	private fun setState(state: Int, group: Int): Int {
		var count = 0
		currentData.forEach { tek ->
			if (tek.group == group) {
				tek.state = state
				count++
			}
		}
		return count
	}


}
