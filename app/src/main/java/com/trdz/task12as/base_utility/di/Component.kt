package com.trdz.task12as.base_utility.di

import com.trdz.task12as.presenter.MainPresenter
import com.trdz.task12as.presenter.UserPresenter
import com.trdz.task12as.view.MainActivity

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
	modules = [
		ModuleNavigation::class,
		ModuleRepo::class,
	]
)
interface Component {

	fun inject(mainActivity: MainActivity)
	fun inject(mainPresenter: MainPresenter)
	fun inject(usersPresenter: UserPresenter)

}
