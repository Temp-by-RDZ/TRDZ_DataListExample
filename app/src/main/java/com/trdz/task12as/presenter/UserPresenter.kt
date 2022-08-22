package com.trdz.task12as.presenter

import android.util.Log
import com.github.terrakok.cicerone.Router
import com.trdz.task12as.MyApp
import com.trdz.task12as.base_utility.*
import com.trdz.task12as.model.RepositoryExecutor
import com.trdz.task12as.view.segment_users.MainView
import com.trdz.task12as.view.segment_users.UserView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter

class UserPresenter(
	private val repository: RepositoryExecutor = RepositoryExecutor(),
	private val router: Router = MyApp.instance.router,
	private var name: String = ""
): MvpPresenter<UserView>() {

	override fun onFirstViewAttach() {
		super.onFirstViewAttach()
		viewState.loadingState(true)
	}

	fun getList(name: String) {
		this.name = name
		with(viewState) {
			repository.setInternalSource(IN_SERVER)
			loadingState(true)
			repository.getUserRepository(name)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(
					{
						Log.e("@@@@@@@@@@@@@@@@@@@@",it.dataRep!!.toString())
						refresh(it.dataRep!!)
					},
					{
						Log.e("@@@@@@@@@@@@@@@@@@@@",it.message!!)
					})
			loadingState(false)
		}

	}


}