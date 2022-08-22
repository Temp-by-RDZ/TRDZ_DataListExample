package com.trdz.task12as.model

import com.trdz.task12as.base_utility.TYPE_CARD
import com.trdz.task12as.base_utility.TYPE_TITLE
import io.reactivex.rxjava3.core.Single

class DataSourceBasis: DataSource {

	private val basisData = listOf(
		DataUser(name = "Лидер 1", subName = "Первая группа", id = 0, type = TYPE_TITLE, iconUrl =  "",group = 1),
		DataUser(name ="Alex" ,subName ="Участник 1", id = 0, type = TYPE_CARD,iconUrl = "", group = 2),
		DataUser(name ="Krot", subName ="Участник 2", id = 0, type =  TYPE_CARD, iconUrl = "",group = 2),
		DataUser(name ="Лидер 2", subName ="Вторая группа", id = 0, type =  TYPE_TITLE, iconUrl = "",group = 3),
		DataUser(name ="EnotAndYouAreNot", subName ="Участник 1", id = 0, type =  TYPE_CARD, iconUrl = "",group = 4),
		DataUser(name ="Fox", subName ="Участник 2", id = 0, type =  TYPE_CARD, iconUrl = "",group = 4),
		DataUser(name ="Dropbox", subName ="Участник 3", id = 0, type =  TYPE_CARD, iconUrl = "",group = 4),
		DataUser(name ="Лидер 3", subName ="Третья группа", id = 0, type =  TYPE_TITLE, iconUrl = "",group = 5),
		DataUser(name ="Aleluya", subName ="Участник 1", id = 0, type =  TYPE_CARD, iconUrl = "",group = 6),
	)

	override fun load(): Single<ServersResult> = Single.create{
		it.onSuccess(ServersResult(0,basisData))
	}

}
