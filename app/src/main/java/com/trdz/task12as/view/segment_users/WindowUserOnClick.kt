package com.trdz.task12as.view.segment_users

import com.trdz.task12as.model.DataUser

interface WindowUserOnClick {
	fun onItemClick(data: DataUser, position: Int)
	fun onItemClickLong(data: DataUser, position: Int)
	fun onItemClickSpecial(data: DataUser, position: Int)
}
