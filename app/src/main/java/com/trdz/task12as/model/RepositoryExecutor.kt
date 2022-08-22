package com.trdz.task12as.model

import android.os.Build
import android.util.Log
import com.trdz.task12as.base_utility.IN_BASIS
import com.trdz.task12as.base_utility.IN_SERVER
import com.trdz.task12as.base_utility.IN_SQL
import com.trdz.task12as.base_utility.TYPE_TITLE
import com.trdz.task12as.model.server_git_user.ServerRetrofit
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlin.math.log

class RepositoryExecutor: Repository {

	private lateinit var dataSource: DataSource
	private lateinit var currentData: MutableList<DataUser>

	override fun dataUpdate(data: MutableList<DataUser>) {
		currentData = data
	}

	override fun setInternalSource(index: Int) {
		when (index) {
			IN_BASIS -> dataSource = DataSourceBasis()
			IN_SQL ->  dataSource = DataSourceBasis()//DataSourceSQL()
			IN_SERVER ->  dataSource = ServerRetrofit()
		}
	}

	override fun getInitUsers() : Single<ServersResult> {
		return dataSource.load()
	}

	override fun getUsers() : List<DataUser> {
		Log.d("@@@", "БАБАБАБАББАБААБАБАБАБАБААББААББААББАБА")
		return currentData
	}

	override fun addAt() {
		currentData.add(DataUser("Новый Лидер", "Новая группа", 0,"",TYPE_TITLE,getNewGroup()))
	}

	override fun removeAt(position: Int) {
		currentData.removeAt(position)
	}

	override fun removeGroup(group: Int): Int {
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

	override fun changeStateAt(data: DataUser, position: Int, state: Int): Int {
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
