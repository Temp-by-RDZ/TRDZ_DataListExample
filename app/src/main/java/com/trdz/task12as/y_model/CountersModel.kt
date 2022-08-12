package com.trdz.task12as.y_model

class CountersModel(): Repository {

	private val counters = mutableListOf(0, 0, 0)

	private fun getCurrent(index: Int): Int {
		return counters[index]
	}

	fun set(index: Int, value: Int){
		counters[index] = value
	}

	override fun next(index: Int): Int {
		counters[index]++
		return getCurrent(index)
	}

	override fun set(array: IntArray){
		counters.clear()
		counters.addAll(array.toList())
	}
}
