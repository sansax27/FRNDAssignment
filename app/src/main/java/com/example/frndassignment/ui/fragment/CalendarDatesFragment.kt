package com.example.frndassignment.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.frndassignment.R
import com.example.frndassignment.adapter.rvadapter.CalendarDatesValuesRVAdapter
import com.example.frndassignment.databinding.FragmentCalendarDatesBinding
import com.example.frndassignment.ui.activity.MainActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "calendarYear"
private const val ARG_PARAM2 = "calendarMonth"

/**
 * A simple [Fragment] subclass.
 * Use the [CalendarDatesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CalendarDatesFragment(private val onDateClick:() -> Unit): Fragment() {
    // TODO: Rename and change types of parameters
    private var year: Int? = null
    private var month: Int? = null

    private val binding: FragmentCalendarDatesBinding by lazy {
        FragmentCalendarDatesBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            year = it.getInt(ARG_PARAM1)
            month = it.getInt(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            binding.rvCalendarDateValues.adapter = CalendarDatesValuesRVAdapter(year ?: 2024,month ?: 8,
            requireContext().resources.getStringArray(R.array.array_weekday_names).toList()) {
            onDateClick()
        }
        binding.rvCalendarDateValues.layoutManager = GridLayoutManager(context, 7)

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CalendarDates.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(year: Int, month: Int, onDateClick: () -> Unit) =
            CalendarDatesFragment(onDateClick).apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, year)
                    putInt(ARG_PARAM2, month)
                }
            }
    }
}