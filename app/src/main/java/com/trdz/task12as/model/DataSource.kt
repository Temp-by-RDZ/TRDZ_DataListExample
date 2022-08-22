package com.trdz.task12as.model

import io.reactivex.rxjava3.core.Single

interface DataSource {
	fun load(): Single<ServersResult>
}