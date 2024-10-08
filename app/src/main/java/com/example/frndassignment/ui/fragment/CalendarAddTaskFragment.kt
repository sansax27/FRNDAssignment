package com.example.frndassignment.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.frndassignment.R
import com.example.frndassignment.data.UIStatus
import com.example.frndassignment.databinding.FragmentCalendarAddTaskBinding
import com.example.frndassignment.databinding.FragmentCalendarBinding
import com.example.frndassignment.viewmodel.CalendarAddTasksViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch



@AndroidEntryPoint
class CalendarAddTaskFragment : Fragment() {


    private val binding: FragmentCalendarAddTaskBinding by lazy {
        FragmentCalendarAddTaskBinding.inflate(layoutInflater)
    }

    private val viewModel: CalendarAddTasksViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        binding.btAddAddTask.setOnClickListener {
            if (binding.etAddTaskTitle.text.isNullOrEmpty() || binding.etAddTaskDescription.text.isNullOrEmpty()) {
                Toast.makeText(context, getString(R.string.fill_all_values), Toast.LENGTH_LONG).show()
            } else {
                viewModel.storeCalendarTask(1234, binding.etAddTaskTitle.text.toString(),
                    binding.etAddTaskDescription.text.toString())
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.storeCalendarTaskStatus.collect{
                when(it) {
                    is UIStatus.Loading -> {
                        binding.clAddTaskUi.visibility = View.GONE
                        binding.pbAddCalendarTask.visibility = View.VISIBLE
                    }
                    is UIStatus.Success -> {
                        binding.clAddTaskUi.visibility = View.VISIBLE
                        binding.pbAddCalendarTask.visibility = View.GONE
                        binding.etAddTaskDescription.setText("")
                        binding.etAddTaskTitle.setText("")
                        Toast.makeText(context, getString(R.string.success_add_task), Toast.LENGTH_LONG).show()
                    }
                    is UIStatus.Error -> {
                        Toast.makeText(context, getString(R.string.error_string), Toast.LENGTH_LONG).show()
                        binding.clAddTaskUi.visibility = View.VISIBLE
                        binding.pbAddCalendarTask.visibility = View.GONE
                    }
                    else -> {

                    }
                }
            }
        }
    }


}