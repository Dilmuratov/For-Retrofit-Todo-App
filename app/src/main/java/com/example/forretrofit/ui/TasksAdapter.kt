package com.example.forretrofit.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.forretrofit.data.models.getalltasks.Tasks
import com.example.forretrofit.databinding.ItemTaskBinding

class TasksAdapter :
    ListAdapter<Tasks, TasksAdapter.TasksViewHolder>(object : DiffUtil.ItemCallback<Tasks>() {
        override fun areItemsTheSame(oldItem: Tasks, newItem: Tasks) = oldItem == newItem

        override fun areContentsTheSame(oldItem: Tasks, newItem: Tasks) = oldItem.id == newItem.id
    }) {

    inner class TasksViewHolder(private val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val task = getItem(position)
            binding.apply {
                tvTaskName.text = task.task
                tvDescription.text = task.description
                tvCreatedAt.text = task.category.createdAt
                tvUpdatedAt.text = task.category.updatedAt
                checkbox.isChecked = task.isDone
            }

            binding.root.setOnClickListener {
                onItemClickListener?.invoke(task)
            }
        }
    }

    private var onItemClickListener: ((Tasks) -> (Unit))? = null
    fun setOnItemClickListener(block: ((Tasks) -> (Unit))) {
        onItemClickListener = block
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder =
        TasksViewHolder(ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        holder.bind(position)
    }
}