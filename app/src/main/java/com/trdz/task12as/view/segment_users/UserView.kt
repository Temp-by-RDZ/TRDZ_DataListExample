package com.trdz.task12as.view.segment_users

import com.trdz.task12as.model.DataRepository
import com.trdz.task12as.model.DataUser
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface UserView: MvpView {
	fun errorCatch()
	fun refresh(list: List<DataRepository>)
	fun loadingState(state: Boolean)
}