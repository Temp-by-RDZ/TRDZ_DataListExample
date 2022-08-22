package com.trdz.task12as.model

import com.trdz.task12as.base_utility.TYPE_CARD

data class DataRepository(
	val name: String,
	val id: Int,
	val publicity: Boolean,
	val type:Int  = TYPE_CARD,
	val group: Int = 0,
	var state: Int = 0
)