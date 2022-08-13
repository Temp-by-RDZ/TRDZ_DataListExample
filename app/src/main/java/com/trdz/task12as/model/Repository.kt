package com.trdz.task12as.model

interface Repository {
	fun setInternalSource(index: Int)
	fun getInitUsers() : List<DataUser>
	fun getUsers() : List<DataUser>
	fun addAt()
	fun removeAt(position: Int)
	fun changeStateAt(data:DataUser,position: Int, state: Int) : Int
	//fun connection(serverListener: ServerResponse, prefix: String, date: String?)
}