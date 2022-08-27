package com.trdz.task12as.model.data_source_room.database

import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface UserDao {
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	abstract fun insert(user: UserEntity)

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	abstract fun insertRepos(repos: List<UserRepoEntity>)

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	abstract fun insertAll(users: List<UserEntity>)

	@Query("SELECT * FROM users")
	abstract fun getUsers(): List<UserEntity>

	@Transaction
	@Query("SELECT * FROM users WHERE name = :name")
	abstract fun getUserWithRepos(name: String): UserRepoMerge

	@Delete
	abstract fun delete(userDBObject: UserEntity): Completable
}