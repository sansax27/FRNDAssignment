package com.example.frndassignment.ui.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.frndassignment.R
import com.example.frndassignment.adapter.vpadapter.CalendarDatesVPAdapter
import com.example.frndassignment.databinding.AlertDialogSelectDateBinding
import com.example.frndassignment.databinding.FragmentCalendarBinding
import java.util.Calendar


class CalendarFragment : Fragment() {

    private val binding: FragmentCalendarBinding by lazy {
        FragmentCalendarBinding.inflate(layoutInflater)
    }
    private val vpAdapter: CalendarDatesVPAdapter by lazy {
        CalendarDatesVPAdapter(Calendar.getInstance().get(Calendar.YEAR),lifecycle, childFragmentManager) {
            findNavController().
            navigate(CalendarFragmentDirections.actionCalendarFragmentToCalendarTasksFragment())
        }
    }

    private val monthsArray: Array<String> by lazy {
        resources.getStringArray(R.array.array_month_names)
    }

    private val selectDateAlertDialog:AlertDialog by lazy {
        val alertDialog = AlertDialog.Builder(requireActivity()).create()
        val selectDateBinding = AlertDialogSelectDateBinding.inflate(layoutInflater, binding.root, false)
        var monthValue = 0
        selectDateBinding.spSelectDateMonth.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                monthValue = p2
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }
        selectDateBinding.btSetDate.setOnClickListener{
            if (selectDateBinding.etSelectDateYear.text.toString().toIntOrNull() !in 1900..2100) {
                Toast.makeText(context, getString(R.string.proper_year), Toast.LENGTH_LONG).show()
            } else {
                vpAdapter.changeTime(selectDateBinding.etSelectDateYear.text.toString().toInt())
                binding.vpCalendarDateValues.currentItem = monthValue
                alertDialog.dismiss()
            }
        }
        alertDialog.setView(selectDateBinding.root)
        alertDialog
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
        binding.ivDateBack.setOnClickListener {
            if (binding.vpCalendarDateValues.currentItem>0) {
                binding.vpCalendarDateValues.setCurrentItem(binding.vpCalendarDateValues.currentItem-1, true)
            }
        }
        binding.ivDateNext.setOnClickListener {
            if (binding.vpCalendarDateValues.currentItem<11) {
                binding.vpCalendarDateValues.setCurrentItem(binding.vpCalendarDateValues.currentItem+1, true)
            }
        }
        binding.vpCalendarDateValues.adapter = vpAdapter
        binding.vpCalendarDateValues.setCurrentItem(Calendar.getInstance().get(Calendar.MONTH), false)
        binding.tvSelectedDate.text = getString(R.string.select_date_text).format(monthsArray[Calendar.getInstance().get(Calendar.MONTH)], Calendar.getInstance().get(Calendar.YEAR))
        binding.vpCalendarDateValues.isSaveEnabled = false
        binding.vpCalendarDateValues.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tvSelectedDate.text = getString(R.string.select_date_text).format(monthsArray[position], vpAdapter.getCurrentYear())
            }
        })
        binding.tvSelectedDate.setOnClickListener {
            selectDateAlertDialog.show()
        }
    }

}