package com.trdz.task12as.model

import com.trdz.task12as.base_utility.TYPE_CARD
import com.trdz.task12as.base_utility.TYPE_TITLE

class DataSourceBasis: DataSource {

	private val basisData = listOf(
		DataUser("Лидер 1", "Первая группа", TYPE_TITLE, 1),
		DataUser("Участник 1", "Alex", TYPE_CARD, 2),
		DataUser("Участник 2", "Krot", TYPE_CARD, 2),
		DataUser("Лидер 2", "Вторая группа", TYPE_TITLE, 3),
		DataUser("Участник 1", "EnotAndYouAreNot", TYPE_CARD, 4),
		DataUser("Участник 2", "Fox", TYPE_CARD, 4),
		DataUser("Участник 3", "Dropbox", TYPE_CARD, 4),
		DataUser("Лидер 3", "Третья группа", TYPE_TITLE, 5),
		DataUser("Участник 1", "Aleluya", TYPE_CARD, 6),
	)

	override fun load():List<DataUser> {
		return basisData
	}

}
