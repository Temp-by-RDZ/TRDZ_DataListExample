package com.trdz.task12as.model

import io.reactivex.rxjava3.core.Single

interface ADataSource {
	fun loadUser(): Single<ServersResultUser>
	fun loadRepository(name: String): Single<ServersResultRepository>
}