package com.trdz.task12as.presenter

import android.util.Log
import com.github.terrakok.cicerone.Router
import com.trdz.task12as.MyApp
import com.trdz.task12as.base_utility.*
import com.trdz.task12as.model.RepositoryExecutor
import com.trdz.task12as.view.segment_users.UserView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter

class UserPresenter(
	private val repository: RepositoryExecutor = RepositoryExecutor(),
	private val router: Router = MyApp.instance.router,
	private var id: Int = 0
): MvpPresenter<UserView>() {

	override fun onFirstViewAttach() {
		super.onFirstViewAttach()
		viewState.loadingState(true)
	}

	fun getDetails(name: String) {
		Log.d("@@@", "Prs - Start details loading")
		with(viewState) {
			repository.setInternalSource(IN_STORAGE)
			repository.getUserInfo(name)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(
					{
						Log.d("@@@", "Prs - Internal load complete")
						id = it.dataUserInfo!!.id
						refresh(it.dataRep!!)
						loadingState(false)
					},
					{
						Log.w("@@@", "Prs - Failed internal load start external loading $it")
						startUserLoad(name)
					})
		}

	}
	private fun startUserLoad(name: String) {
		with(viewState) {
			repository.setInternalSource(IN_SERVER)
			repository.getUserInfo(name)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(
					{
						Log.d("@@@", "Prs - External load complete")
						id = it.dataUserInfo!!.id
						startRepoLoad(name)
					},
					{
						Log.e("@@@", "Prs - Loading failed $it")
						viewState.errorCatch()
						loadingState(false)
					})
		}
	}

	private fun startRepoLoad(name: String) {
		Log.d("@@@", "Prs - Start repos loading")
		with(viewState) {
			repository.setInternalSource(IN_SERVER)
			repository.getUserRepository(name)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(
					{
						Log.d("@@@", "Prs - External load complete")
						val result = it.dataRep!!
						repository.updateRepo(result,id)
						refresh(result)
						loadingState(false)
					},
					{
						Log.e("@@@", "Prs - Loading failed $it")
						loadingState(false)
					})
		}
	}


}