package com.trdz.task12as.x_presenter

import com.trdz.task12as.w_view.MainView
import com.trdz.task12as.y_model.CountersModel
import com.trdz.task12as.y_model.Repository
import moxy.MvpPresenter

class MainPresenter(
	private val repository: Repository = CountersModel(),
): MvpPresenter<MainView>() {

	fun counterClickFirst() {
		viewState.setFirst(repository.next(0))
	}
	fun counterClickSecond(){
		viewState.setSecond(repository.next(1))
	}
	fun counterClickThird() {
		viewState.setThird(repository.next(2))
	}

}