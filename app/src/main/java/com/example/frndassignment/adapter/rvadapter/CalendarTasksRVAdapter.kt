package com.example.frndassignment.adapter.rvadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.frndassignment.data.models.repository.response.TaskDataApiResponseModel
import com.example.frndassignment.data.models.repository.response.TaskApiResponseModel
import com.example.frndassignment.databinding.LayoutCalendarTaskItemBinding
import timber.log.Timber
import java.lang.Exception

class CalendarTasksRVAdapter(private val onDeleteClick: (Int, Int) -> Unit): RecyclerView.Adapter<CalendarTasksRVAdapter.CalendarTasksViewHolder>() {

    private val dataList = mutableListOf<TaskDataApiResponseModel>()

    fun updateData(dataList: List<TaskDataApiResponseModel>) {
        this.dataList.clear()
        this.dataList.addAll(dataList)
        notifyDataSetChanged()
    }

    fun removeItem(position: Int) {
        Timber.i("Removed Position $position")
        try {
            // Crash Prone
            dataList.removeAt(position)
            notifyItemRemoved(position)
        } catch (e: Exception) {
            Timber.i(e)
        }

    }

    inner class CalendarTasksViewHolder(private val binding: LayoutCalendarTaskItemBinding):
        RecyclerView.ViewHolder(binding.root) {
            fun bind(data: TaskDataApiResponseModel, position: Int) {
                binding.tvTaskTitle.text = data.taskModel.title
                binding.tvTaskDescription.text = data.taskModel.description
                binding.ivTaskDelete.setOnClickListener {
                    onDeleteClick(data.taskId, position)
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarTasksViewHolder {
        return CalendarTasksViewHolder(LayoutCalendarTaskItemBinding.inflate(LayoutInflater
            .from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: CalendarTasksViewHolder, position: Int) {
        holder.bind(dataList[position], position)
    }
}