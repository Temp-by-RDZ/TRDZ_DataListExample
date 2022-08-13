package com.trdz.task12as.model

import com.trdz.task12as.base_utility.IN_BASIS
import com.trdz.task12as.base_utility.TYPE_TITLE

class RepositoryExecutor: Repository {

	private lateinit var dataSource: DataSource
	private lateinit var currentData: MutableList<DataUser>

	override fun setInternalSource(index: Int) {
		when (index) {
			IN_BASIS -> dataSource = DataSourceBasis()
			//IN_SQL ->  dataSourceInternal = DataSourceSQL()
		}
	}

	override fun getInitUsers() : List<DataUser> {
		dataUpdate()
		return currentData
	}

	override fun getUsers() : List<DataUser> {
		return currentData
	}

	override fun addAt() {
		currentData.add(DataUser("Новый Лидер", "Новая группа", TYPE_TITLE,getNewGroup()))
	}

	override fun removeAt(position: Int) {
		currentData.removeAt(position)
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

	private fun dataUpdate() {
		currentData = dataSource.load().toMutableList()
	}

//	override fun connection(serverListener: ServerResponse, prefix: String, date: String?) {
//		Log.d("@@@", "Rep - start connection $prefix on date: $date")
//		lateinit var externalSource: ExternalSource
//		when (prefix) {
//			//PREFIX_POD -> externalSource = ServerRetrofitPOD()
//			//PREFIX_MRP -> externalSource = ServerRetrofitMRP()
//			//PREFIX_EPC -> externalSource = ServerReceiverEPC()
//		}
//		Thread {
//			val result = externalSource.load(date)
//			if (result.code in 200..299 ) serverListener.success(prefix,result)
//			else serverListener.fail(prefix, result.code)
//		}.start()
//	}

}
