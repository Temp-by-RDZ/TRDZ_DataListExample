package com.trdz.task12as.view.segment_users

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.trdz.task12as.base_utility.TYPE_CARD
import com.trdz.task12as.base_utility.TYPE_NONE
import com.trdz.task12as.base_utility.TYPE_TITLE
import com.trdz.task12as.databinding.ElementUserCardBinding
import com.trdz.task12as.databinding.ElementUserHiderBinding
import com.trdz.task12as.databinding.ElementUserLiderBinding
import com.trdz.task12as.model.DataUser
import kotlin.math.min

class WindowUserRecycle(private val clickExecutor: WindowUserOnClick): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

	private var list: List<DataUser> = emptyList()
	private var opened = -1

	fun stackControl(newList: List<DataUser>, first: Int, count: Int) {
		this.list = newList
		notifyItemRangeChanged(first, count)
	}
	fun subClose(position: Int) {
		notifyItemChanged(position, true)
	}

	@SuppressLint("NotifyDataSetChanged")
	fun setList(newList: List<DataUser>) {
		this.list = newList
		notifyDataSetChanged()
	}

	fun addToList(newList: List<DataUser>, position: Int) {
		this.list = newList
		notifyItemChanged(position)
	}

	fun removeFromList(newList: List<DataUser>, position: Int) {
		this.list = newList
		notifyItemRemoved(position)
	}
	fun removeFromListMany(newList: List<DataUser>, first: Int, count: Int) {
		this.list = newList
		notifyItemRangeRemoved(first, count)
	}

	override fun getItemViewType(position: Int): Int {
		return when (getItemViewState(position)) {
			2 -> TYPE_NONE
			else -> list[position].type
		}
	}

	private fun getItemViewState(position: Int): Int {
		return list[position].state
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
		return when (viewType) {
			TYPE_CARD -> {
				val view = ElementUserCardBinding.inflate(LayoutInflater.from(parent.context))
				ElementUser(view.root)
			}
			TYPE_TITLE -> {
				val view = ElementUserLiderBinding.inflate(LayoutInflater.from(parent.context))
				ElementLider(view.root)
			}
			else -> {
				val view = ElementUserHiderBinding.inflate(LayoutInflater.from(parent.context))
				ElementNone(view.root)
			}
		}

	}

	override fun onBindViewHolder(
		holder: RecyclerView.ViewHolder,
		position: Int,
		payloads: MutableList<Any>,
	) {
		if (payloads.isEmpty()) {
			super.onBindViewHolder(holder, position, payloads)
		}
		else {
			(holder as ElementUser).subClose()
		}
	}

	override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
		(holder as ListElement).myBind(list[position])
	}

	override fun getItemCount(): Int {
		return list.size
	}

	abstract inner class ListElement(view: View): RecyclerView.ViewHolder(view) {
		abstract fun myBind(data: DataUser)
	}

	inner class ElementUser(view: View): ListElement(view) {
		fun subClose() {
			(ElementUserCardBinding.bind(itemView)).apply {
				marsDescriptionTextView.visibility = View.GONE
			}
		}

		override fun myBind(data: DataUser) {
			(ElementUserCardBinding.bind(itemView)).apply {
				root.setOnClickListener {clickExecutor.onItemClick(data, layoutPosition)}
				root.setOnLongClickListener { clickExecutor.onItemClickLong(data, layoutPosition); true }
				elemntImage.setOnClickListener {
					if (opened > -1) subClose(opened)
					if (opened != layoutPosition) {
						opened = layoutPosition
						marsDescriptionTextView.visibility = View.VISIBLE
					}
					else opened = -1
				}
				title.text = data.name
			}
		}
	}

	inner class ElementLider(view: View): ListElement(view) {
		override fun myBind(data: DataUser) {
			(ElementUserLiderBinding.bind(itemView)).apply {
				if (data.state == 1) ObjectAnimator.ofFloat(blockImage, View.ROTATION, -90f, 0f).setDuration(250).start()
				title.text = data.name
				descriptionTextView.text = data.subName
				clickZone.setOnClickListener {
					if (data.state == 1) ObjectAnimator.ofFloat(blockImage, View.ROTATION, 0f, -90f).setDuration(500).start()
					else ObjectAnimator.ofFloat(blockImage, View.ROTATION, -90f, 0f).setDuration(500).start()
					clickExecutor.onItemClickSpecial(data, layoutPosition)
				}
				root.setOnClickListener { clickExecutor.onItemClick(data, layoutPosition) }
				root.setOnLongClickListener { clickExecutor.onItemClickLong(data, layoutPosition); true }
			}
		}
	}

	inner class ElementNone(view: View): ListElement(view) {
		override fun myBind(data: DataUser) {
		}
	}
}