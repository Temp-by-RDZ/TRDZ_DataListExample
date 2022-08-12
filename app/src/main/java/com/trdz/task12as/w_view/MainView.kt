package com.trdz.task12as.w_view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface MainView: MvpView {
	fun setFirst(number: Int)
	fun setSecond(number: Int)
	fun setThird(number: Int)
}