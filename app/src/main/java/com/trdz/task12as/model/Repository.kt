package com.trdz.task12as.model

import io.reactivex.rxjava3.core.Single

interface Repository {
	fun setInternalSource(index: Int)
	fun getInitUsers() : Single<ServersResult>
	fun getUsers() : List<DataUser>
	fun addAt()
	fun removeAt(position: Int)
	fun removeGroup(state: Int) : Int
	fun changeStateAt(data:DataUser,position: Int, state: Int) : Int
	fun dataUpdate(data: MutableList<DataUser>)
	//fun connection(serverListener: ServerResponse, prefix: String, date: String?)
}