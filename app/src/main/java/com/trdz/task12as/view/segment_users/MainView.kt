package com.trdz.task12as.view.segment_users

import com.trdz.task12as.model.DataUser
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface MainView: MvpView {
	fun refresh(list: List<DataUser>)
	fun changeMore(list:List<DataUser>)
	fun changeLess(list:List<DataUser>, position:Int)
	fun changeState(list: List<DataUser>, position: Int, count: Int)
}