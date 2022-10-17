package com.trdz.task12as.presenter

import android.util.Log
import com.github.terrakok.cicerone.Router
import com.trdz.task12as.MyApp
import com.trdz.task12as.base_utility.*
import com.trdz.task12as.model.DataUser
import com.trdz.task12as.model.RepositoryExecutor
import com.trdz.task12as.view.segment_users.MainView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import javax.inject.Inject

class MainPresenter(): MvpPresenter<MainView>() {

	@Inject lateinit var repository: RepositoryExecutor
	@Inject	lateinit var router: Router

	override fun onFirstViewAttach() {
		super.onFirstViewAttach()
		Log.d("@@@", "Prs - Start users loading")
		with(viewState) {
			repository.setSource(IN_STORAGE)
			loadingState(true)
			repository.getInitUsers()
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(
					{
						Log.d("@@@", "Prs - Internal load complete")
						val result = it.dataUser!!
						repository.dataUpdate(result.toMutableList())
						refresh(result)
						loadingState(false)
					},
					{
						Log.w("@@@", "Prs - Failed internal load start external loading $it")
						startLoad()
					})
		}

	}
	private fun startLoad() {
		with(viewState) {
			repository.setSource(IN_SERVER)
			repository.getInitUsers()
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(
					{
						Log.d("@@@", "Prs - External load complete")
						val result = it.dataUser!!
						repository.dataUpdate(result.toMutableList())
						repository.update()
						refresh(result)
						loadingState(false)
					},
					{
						Log.e("@@@", "Prs - Loading failed $it")
						viewState.errorCatch()
						loadingState(false)
					})
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