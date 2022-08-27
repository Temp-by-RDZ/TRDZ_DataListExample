package com.trdz.task12as.model

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface InternalData {
	fun saveUsers(users: List<DataUser>): Completable
	fun saveRepos(repos: List<DataRepository>, owner: Int): Completable
}