package com.trdz.task12as.w_view

import android.os.Bundle
import com.trdz.task12as.databinding.ActivityMainBinding
import com.trdz.task12as.x_presenter.MainPresenter
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity: MvpAppCompatActivity(), MainView {

	private var binding: ActivityMainBinding? = null
	private val presenter by moxyPresenter { MainPresenter() }

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding?.root)
		buttonBinds()
	}

	private fun buttonBinds() {
		binding?.run {
			btnCounter1.setOnClickListener { presenter.counterClickFirst() }
			btnCounter2.setOnClickListener { presenter.counterClickSecond()  }
			btnCounter3.setOnClickListener { presenter.counterClickThird()  }
		}
	}

	override fun setFirst(number: Int) = with(binding!!) {
		btnCounter1.text = number.toString()
	}

	override fun setSecond(number: Int) = with(binding!!) {
		btnCounter2.text = number.toString()
	}

	override fun setThird(number: Int) = with(binding!!) {
		btnCounter3.text = number.toString()
	}

}