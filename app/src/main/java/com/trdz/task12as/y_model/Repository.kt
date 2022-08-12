package com.trdz.task12as.y_model

interface Repository {
	fun next(index: Int): Int
	fun set(array: IntArray)
}