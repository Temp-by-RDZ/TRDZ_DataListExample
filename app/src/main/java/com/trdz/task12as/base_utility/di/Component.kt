package com.trdz.task12as.base_utility.di

import com.trdz.task12as.presenter.MainPresenter
import com.trdz.task12as.presenter.UserPresenter

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
	modules = [
		ModuleServer::class,
	]
)
interface Component {

	fun inject(mainPresenter: MainPresenter)
	fun inject(usersPresenter: UserPresenter)

}
