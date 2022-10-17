package com.trdz.task12as.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.trdz.task12as.MyApp
import com.trdz.task12as.R
import com.trdz.task12as.base_utility.KEY_OPTIONS
import com.trdz.task12as.base_utility.KEY_THEME
import moxy.MvpAppCompatActivity
import javax.inject.Inject

class MainActivity: MvpAppCompatActivity(), Leader {

	//region Elements
	private val navigation = Navigation(R.id.container_fragment_base)
	private val executor = Executor()
	private val navigator = AppNavigator(this,R.id.container_fragment_base)
	@Inject lateinit var navigatorHolder: NavigatorHolder


	//endregion

	//region Customization

	override fun onBackPressed() {
		val fragmentList = supportFragmentManager.fragments

		var handled = false
		for (f in fragmentList) {
			if (f is CustomOnBackPressed) {
				handled = f.onBackPressed()
				if (handled) {
					break
				}
			}
		}

		if (!handled) super.onBackPressed()
	}

	//endregion

	//region Navigator
	override fun onResumeFragments() {
		super.onResumeFragments()
		navigatorHolder.setNavigator(navigator)
	}

	override fun onPause() {
		super.onPause()
		navigatorHolder.removeNavigator()
	}
	//endregion

	//region Base realization
	override fun onDestroy() {
		executor.stop()
		super.onDestroy()
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		MyApp.instance.appComponent.inject(this)
		themeSettings()
		setContentView(R.layout.activity_main)
		if (savedInstanceState == null) {
			Log.d("@@@", "Start program")
			navigation.add(supportFragmentManager, WindowStart(), false, R.id.container_fragment_primal)
		}
	}

	private fun themeSettings() {
		when (getSharedPreferences(KEY_OPTIONS, Context.MODE_PRIVATE).getInt(KEY_THEME, 0)) {
			0 -> setTheme(R.style.MyBaseTheme)
			1 -> setTheme(R.style.MyGoldTheme)
			2 -> setTheme(R.style.MyFiolTheme)
		}
	}

	//endregion

	override fun getNavigation() = navigation
	override fun getExecutor() = executor

}