package com.trdz.task12as.base_utility

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.trdz.task12as.model.DataUser
import com.trdz.task12as.view.segment_users.WindowUser
import java.util.prefs.PreferencesFactory

data class ScreenUsers(val user: DataUser): FragmentScreen {
	override fun createFragment(factory: FragmentFactory): Fragment{
		return WindowUser.newInstance(user.name)
	}
}