package com.trdz.task12as.model

data class ServersResultUser(val code: Int, val dataUser: List<DataUser>? =null, val dataUserInfo: DataUserInfo? =null)
data class ServersResultRepository(val code: Int, val dataRep: List<DataRepository>? =null)
