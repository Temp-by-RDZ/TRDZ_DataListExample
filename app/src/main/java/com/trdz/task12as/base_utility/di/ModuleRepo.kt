package com.trdz.task12as.base_utility.di

import com.trdz.task12as.model.RepositoryExecutor
import dagger.Module
import dagger.Provides
import java.util.*
import javax.inject.Named
import javax.inject.Singleton

@Module
object ModuleRepo {

	@Provides
	@Singleton
	fun provideRepository(): RepositoryExecutor {
		return RepositoryExecutor()
	}

}


