package com.trdz.task12as.model

data class DataUserInfo(
	val name: String = "Name",
	val subName: String? = "Subname",
	val id: Int,
	val iconUrl: String,
	val type:Int,
	val group: Int = 0,
	var state: Int = 0
)