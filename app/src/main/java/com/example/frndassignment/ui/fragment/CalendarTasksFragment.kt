package com.example.frndassignment.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.frndassignment.R
import com.example.frndassignment.adapter.rvadapter.CalendarTasksRVAdapter
import com.example.frndassignment.data.UIStatus
import com.example.frndassignment.databinding.FragmentCalendarTasksBinding
import com.example.frndassignment.viewmodel.CalendarTasksViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class CalendarTasksFragment : Fragment() {


    private val binding: FragmentCalendarTasksBinding by lazy {
        FragmentCalendarTasksBinding.inflate(layoutInflater)
    }

    private val viewModel: CalendarTasksViewModel by viewModels()


    private val adapter: CalendarTasksRVAdapter by lazy {
        CalendarTasksRVAdapter{ taskId, position ->
            viewModel.deleteCalendarTask(1234, taskId)
            deleteTaskPosition = position
        }
    }



    private var deleteTaskPosition = -1


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding.btAddCalendarTask.setOnClickListener {
            requireActivity().findNavController(R.id.nav_host_fragment).navigate(CalendarTasksFragmentDirections.actionCalendarTasksFragmentToCalendarAddTaskFragment())
        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.getCalendarTasks(1234)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.deleteCalendarTaskStatus.collect {
                when (it) {
                    is UIStatus.Loading -> {
                        binding.pbCalendarTask.visibility = View.VISIBLE
                        binding.clCalendarTaskUi.visibility = View.GONE
                    }

                    is UIStatus.Success -> {
                        if (deleteTaskPosition!=-1) {
                            adapter.removeItem(deleteTaskPosition)
                            deleteTaskPosition = -1
                            binding.clCalendarTaskUi.visibility = View.VISIBLE
                            binding.pbCalendarTask.visibility = View.GONE
                            if (adapter.itemCount==0) {
                                binding.tvCalendarTaskThatsAll.visibility = View.VISIBLE
                                binding.rvCalendarTasks.visibility = View.GONE
                            } else {
                                binding.tvCalendarTaskThatsAll.visibility = View.GONE
                                binding.rvCalendarTasks.visibility = View.VISIBLE
                            }
                        }
                    }

                    is UIStatus.Error -> {
                        binding.pbCalendarTask.visibility = View.GONE
                        Toast.makeText(context, getString(R.string.error_string), Toast.LENGTH_LONG)
                            .show()
                    }

                    else -> {

                    }
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getCalendarTasksStatus.collect{
                when(it) {
                    is UIStatus.Loading -> {
                        binding.clCalendarTaskUi.visibility = View.GONE
                        binding.pbCalendarTask.visibility = View.VISIBLE
                    }
                    is UIStatus.Error -> {
                        binding.pbCalendarTask.visibility = View.GONE
                        Toast.makeText(context, getString(R.string.error_string), Toast.LENGTH_LONG).show()
                    }
                    is UIStatus.Success -> {
                        binding.pbCalendarTask.visibility = View.GONE
                        if (it.data.tasks.isNotEmpty()) {
                            binding.rvCalendarTasks.adapter = adapter
                            binding.rvCalendarTasks.layoutManager = LinearLayoutManager(activity).apply {
                                orientation = LinearLayoutManager.VERTICAL
                            }
                            adapter.updateData(it.data.tasks)
                            binding.tvCalendarTaskThatsAll.visibility = View.GONE
                            binding.rvCalendarTasks.visibility = View.VISIBLE
                        } else {
                            binding.tvCalendarTaskThatsAll.visibility = View.VISIBLE
                            binding.rvCalendarTasks.visibility = View.GONE
                        }
                        binding.clCalendarTaskUi.visibility = View.VISIBLE
                    }
                    else -> {

                    }
                }
            }

        }

    }

}