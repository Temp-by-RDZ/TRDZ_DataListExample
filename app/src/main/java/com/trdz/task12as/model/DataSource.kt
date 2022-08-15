package com.trdz.task12as.model

interface DataSource {
	fun load(): List<DataUser>
}