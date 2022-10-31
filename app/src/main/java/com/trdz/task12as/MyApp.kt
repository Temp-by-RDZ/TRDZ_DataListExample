package com.trdz.task12as

import android.app.Application
import android.util.Log
import androidx.room.Room
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.google.gson.GsonBuilder
import com.trdz.task12as.base_utility.DOMAIN
import com.trdz.task12as.base_utility.di.*
import com.trdz.task12as.model.data_source_room.database.DataBase
import com.trdz.task12as.model.data_source_room.database.UserDao
import com.trdz.task12as.model.data_source_server.ServerRetrofitApi
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApp: Application() {

	companion object {
		lateinit var  instance:MyApp
		lateinit var  di:DI
	}

	lateinit var appComponent: Component

	override fun onCreate() {
		super.onCreate()
		instance = this
		di = DIImpl().apply {
			DIModule(this, instance)
		}
		RxJavaPlugins.setErrorHandler {/*None*/}
		appComponent = DaggerComponent.builder().build()
	}

}