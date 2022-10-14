package com.trdz.task12as.view.segment_users

import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.trdz.task12as.MyApp
import com.trdz.task12as.base_utility.KEY_NAME
import com.trdz.task12as.databinding.FragmentWindowUserBinding
import com.trdz.task12as.model.DataRepository
import com.trdz.task12as.model.DataUser
import com.trdz.task12as.model.RepositoryExecutor
import com.trdz.task12as.presenter.MainPresenter
import com.trdz.task12as.presenter.UserPresenter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class WindowUser: MvpAppCompatFragment(), UserView {

	//region Elements
	private var _binding: FragmentWindowUserBinding? = null
	private val binding get() = _binding!!
	private val adapter = WindowUserRecycle()
	//private val presenter by moxyPresenter { UserPresenter(RepositoryExecutor()) }
	private val presenter by moxyPresenter {
		UserPresenter().apply {
			MyApp.instance.appComponent.inject(this)
		}
	}
	private lateinit var name:String
	//endregion

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		_binding = FragmentWindowUserBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		arguments?.let {
			name = it.getString(KEY_NAME)?: ""
			binding.Title.text =name
		}
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			binding.recyclerView.setOnScrollChangeListener { _, _, _, _, _ ->
				binding.Title.isSelected = binding.recyclerView.canScrollVertically(-1)
			}
		}
		binding.recyclerView.adapter = adapter
		presenter.getDetails(name)
	}

	//region Presenter command realization
	override fun errorCatch() {
		//("Not yet implemented")
	}

	override fun refresh(list: List<DataRepository>) {
		adapter.setList(list)
	}

	override fun loadingState(state: Boolean) {
		binding.loadingLayout.visibility = if (state) View.VISIBLE
		else View.GONE
	}

	//endregion

	companion object {
		@JvmStatic
		fun newInstance(username: String) =
			WindowUser().apply {
				arguments = Bundle().apply {
					putString(KEY_NAME, username)
				}
			}

		fun getInstance() = WindowUser()
	}


}

