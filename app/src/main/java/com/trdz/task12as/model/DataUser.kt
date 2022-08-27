package com.trdz.task12as.model

import com.trdz.task12as.base_utility.TYPE_TITLE

data class DataUser(
	val name: String = "Name",
	val subName: String = "Subname",
	val id: Int,
	val iconUrl: String,
	val type:Int  = TYPE_TITLE,
	val group: Int = 0,
	var state: Int = 0
)