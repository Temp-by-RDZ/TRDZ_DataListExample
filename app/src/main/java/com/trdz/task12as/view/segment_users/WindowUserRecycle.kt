package com.trdz.task12as.view.segment_users

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.trdz.task12as.databinding.ElementUserRepoBinding
import com.trdz.task12as.model.DataRepository

class WindowUserRecycle(private var list: List<DataRepository> = emptyList()): RecyclerView.Adapter<WindowUserRecycle.Line?>() {

	@SuppressLint("NotifyDataSetChanged")
	fun setList(newList: List<DataRepository>) {
		this.list = newList
		notifyDataSetChanged()
	}

	override fun getItemCount(): Int {
		return list.size
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Line {
		val view = ElementUserRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		return Line(view.root)
	}

	override fun onBindViewHolder(holder: Line, position: Int) {
		holder.bind(list[position])
	}

	inner class Line(view: View): RecyclerView.ViewHolder(view) {
		fun bind(data: DataRepository) {
			(ElementUserRepoBinding.bind(itemView)).apply {
				title.text = data.name
			}
		}
	}

}