package com.trdz.task12as.presenter

import com.github.terrakok.cicerone.Router
import com.trdz.task12as.MyApp
import com.trdz.task12as.base_utility.IN_BASIS
import com.trdz.task12as.base_utility.ScreenUsers
import com.trdz.task12as.model.DataUser
import com.trdz.task12as.model.Repository
import com.trdz.task12as.model.RepositoryExecutor
import com.trdz.task12as.view.segment_users.MainView
import com.trdz.task12as.view.segment_users.WindowUser
import moxy.MvpPresenter

class MainPresenter(
	private val repository: Repository = RepositoryExecutor(),
	private val router: Router = MyApp.instance.router
): MvpPresenter<MainView>() {

	override fun onFirstViewAttach() {
		super.onFirstViewAttach()
		repository.setInternalSource(IN_BASIS)
		viewState.refresh(repository.getInitUsers())
	}

	fun openDetails(data: DataUser) {
		router.navigateTo(ScreenUsers(data))
	}

	fun dataAdditional() {
		repository.addAt()
		viewState.changeMore(repository.getUsers())
	}

	fun dataRemoval(position: Int) {
		repository.removeAt(position)
		viewState.changeLess(repository.getUsers(),position)
	}

	fun visualChange(data: DataUser, position: Int) {
		val count = if (data.state == 1) {
			repository.changeStateAt(data,position,0)
		}
		else {
			repository.changeStateAt(data,position,1)
		}
		viewState.changeState(repository.getUsers(),position+1,count)
	}


}