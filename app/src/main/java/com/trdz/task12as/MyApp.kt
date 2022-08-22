package com.trdz.task12as

import android.app.Application
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.google.gson.GsonBuilder
import com.trdz.task12as.base_utility.DOMAIN
import com.trdz.task12as.model.data_source_server.ServerRetrofitApi
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApp: Application() {

	companion object {
		lateinit var instance : MyApp

		private var retrofitGitUsers: ServerRetrofitApi? = null

		private fun createRetrofit() {
			retrofitGitUsers = Retrofit.Builder().apply {
				baseUrl(DOMAIN)
				addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
			}.build().create(ServerRetrofitApi::class.java)
		}

		fun getRetrofit(): ServerRetrofitApi {
			if (retrofitGitUsers == null) createRetrofit()
			return retrofitGitUsers!!
		}

	}

	private val cicerone : Cicerone<Router> by lazy { Cicerone.create() }

	val navigationHandler = cicerone.getNavigatorHolder()
	val router = cicerone.router

	override fun onCreate() {
		super.onCreate()
		instance = this
		RxJavaPlugins.setErrorHandler {
			//None
		}
	}

}