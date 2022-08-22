package com.trdz.task12as.view.segment_users

import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import com.trdz.task12as.R
import com.trdz.task12as.databinding.FragmentWindowUserListBinding
import com.trdz.task12as.model.DataUser
import com.trdz.task12as.model.RepositoryExecutor
import com.trdz.task12as.presenter.MainPresenter
import com.trdz.task12as.view.Leader
import com.trdz.task12as.view.MainActivity
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class WindowUserList: MvpAppCompatFragment(), WindowUserOnClick, MainView {

	//region Elements
	private var _binding: FragmentWindowUserListBinding? = null
	private val binding get() = _binding!!
	private var _executors: Leader? = null
	private val executors get() = _executors!!
	private val adapter = WindowUserListRecycle(this)
	private val presenter by moxyPresenter { MainPresenter(RepositoryExecutor()) }

	//endregion

	//region Base realization
	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
		_executors = null
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		_binding = FragmentWindowUserListBinding.inflate(inflater, container, false)
		_executors = (requireActivity() as MainActivity)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			binding.recyclerView.setOnScrollChangeListener { _, _, _, _, _ ->
				binding.naming.isSelected = binding.recyclerView.canScrollVertically(-1)
			}
		}
		binding.recyclerView.adapter = adapter
	}

	//endregion

	private fun openDetails(data: DataUser) {
		presenter.openDetails(data)
	}
	//region Adapter realization
	override fun onItemClick(data: DataUser, position: Int) {
		openDetails(data)
	}

	override fun onItemClickLong(data: DataUser, position: Int) {
		activity?.let {
			AlertDialog.Builder(it)
				.setTitle(getString(R.string.long_clik_dio_title))
				.setMessage(" ${data.name}")
				.setPositiveButton(getString(R.string.long_clik_dio_chose1)) { _, _ ->
					presenter.dataRemoval(data,position)
				}
				.setNegativeButton(getString(R.string.long_clik_dio_chose2)) { _, _ ->
					presenter.dataAdditional()
				}
				.setNeutralButton(getString(R.string.long_clik_dio_chose3)) { dialog, _ ->
					openDetails(data)
					dialog.dismiss()
				}
				.create()
				.show()
		}
	}

	override fun onItemClickSpecial(data: DataUser, position: Int) {
		presenter.visualChange(data, position)
	}


	//endregion

	//region Presenter command realization
	override fun errorCatch() {
		//("Not yet implemented")
	}
	override fun refresh(list: List<DataUser>) {
		adapter.setList(list)
	}

	override fun changeMore(list: List<DataUser>) {
		adapter.addToList(list, list.size)
	}

	override fun changeLess(list:List<DataUser>, position:Int) {
		adapter.removeFromList(list, position)
	}
	override fun changeLessMore(list: List<DataUser>, position: Int, count: Int) {
		adapter.removeFromListMany(list, position, count)
	}
	override fun changeState(list: List<DataUser>, position: Int, count: Int) {
		adapter.stackControl(list, position, count)
	}

	override fun loadingState(state: Boolean) {
		binding.loadingLayout.visibility = if (state)  View.VISIBLE
		else View.GONE
	}
	//endregion

	companion object {
		@JvmStatic
		fun newInstance() = WindowUserList()
	}

}
