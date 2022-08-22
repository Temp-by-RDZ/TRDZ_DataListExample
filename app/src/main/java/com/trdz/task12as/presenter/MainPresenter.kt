package com.trdz.task12as.presenter

import android.util.Log
import com.github.terrakok.cicerone.Router
import com.trdz.task12as.MyApp
import com.trdz.task12as.base_utility.*
import com.trdz.task12as.model.DataUser
import com.trdz.task12as.model.Repository
import com.trdz.task12as.model.RepositoryExecutor
import com.trdz.task12as.view.segment_users.MainView
import com.trdz.task12as.view.segment_users.WindowUser
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import java.util.concurrent.TimeUnit

class MainPresenter(
	private val repository: Repository = RepositoryExecutor(),
	private val router: Router = MyApp.instance.router,
): MvpPresenter<MainView>() {
	override fun onFirstViewAttach() {
		super.onFirstViewAttach()
		with(viewState) {
			repository.setInternalSource(IN_SERVER)
			loadingState(true)
			repository.getInitUsers()
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(
					{
						val result = it.dataUser!!
						Log.d("@@@@@@@@@@@@@@@@@@@@", result.toString())
						repository.dataUpdate(result.toMutableList())
						refresh(result)
					},
					{

					})
			loadingState(false)
		}

	}

	fun openDetails(data: DataUser) {
		router.navigateTo(ScreenUsers(data))
	}

	fun dataAdditional() {
		repository.addAt()
		viewState.changeMore(repository.getUsers())
	}

	fun dataRemoval(data: DataUser, position: Int) {
		repository.removeAt(position)
		if (data.group%2==1) {
			val count = repository.removeGroup(data.group)
			viewState.changeLessMore(repository.getUsers(), position, count)
		}
		else viewState.changeLess(repository.getUsers(), position)
	}

	fun visualChange(data: DataUser, position: Int) {
		val count = if (data.state == 1) {
			repository.changeStateAt(data, position, 0)
		}
		else {
			repository.changeStateAt(data, position, 1)
		}
		viewState.changeState(repository.getUsers(), position + 1, count)
	}


}