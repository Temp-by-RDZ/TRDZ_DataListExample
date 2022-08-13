package com.trdz.task12as.view.segment_users

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.trdz.task12as.base_utility.KEY_NAME
import com.trdz.task12as.databinding.FragmentWindowUserBinding

class WindowUser: Fragment() {

	//region Elements
	private var _binding: FragmentWindowUserBinding? = null
	private val binding get() = _binding!!
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
			binding.Title.text = it.getString(KEY_NAME)
		}

	}

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

