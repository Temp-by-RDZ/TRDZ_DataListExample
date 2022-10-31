package com.trdz.task12as.base_utility.di

import androidx.room.Room
import com.google.gson.GsonBuilder
import com.trdz.task12as.MyApp
import com.trdz.task12as.base_utility.DOMAIN
import com.trdz.task12as.model.data_source_room.database.DataBase
import com.trdz.task12as.model.data_source_room.database.UserDao
import com.trdz.task12as.model.data_source_server.ServerRetrofitApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DIModule(di: DI,val instance: MyApp) {

	private var db: DataBase? = null

	fun getHistoryDao(): UserDao {
		if (db == null) db = Room.databaseBuilder(instance, DataBase::class.java, "test").build()
		return db!!.userDao()
		}

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

	init {
		di.add(UserDao::class,this::getHistoryDao)
		di.add(ServerRetrofitApi::class,this::getRetrofit)
	}
}