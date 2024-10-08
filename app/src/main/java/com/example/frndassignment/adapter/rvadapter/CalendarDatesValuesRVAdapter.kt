package com.example.frndassignment.adapter.rvadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.frndassignment.DateTimeUtils
import com.example.frndassignment.R
import com.example.frndassignment.data.models.util.CalendarValueType
import com.example.frndassignment.databinding.LayoutMonthDayBinding
import com.example.frndassignment.databinding.LayoutWeekdayNameBinding
import timber.log.Timber


class CalendarDatesValuesRVAdapter(year: Int, month: Int, private val weekDayNameList: List<String>,
    private val onDateClick:(Int)->Unit):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val calendarMonthInfo = DateTimeUtils.getCalendarMonthInfo(year, month).also {
        Timber.i(it.firstCalendarRow.toString())
        Timber.i(it.lastCalendarRow.toString())
        Timber.i(it.toString())
        Timber.i(it.totalCalendarVisibleDays.toString())
    }


    inner class CalendarWeekDayNameViewHolder(private val binding: LayoutWeekdayNameBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(weekDayNumber:Int) {
            binding.tvWeekdayName.text = weekDayNameList[weekDayNumber]
        }
    }

    inner class CalendarMonthDayNumberViewHolder(private val binding: LayoutMonthDayBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(monthDayNumber: Int, isThisMonthDay: Boolean) {
            binding.tvDateNumber.text = monthDayNumber.toString()
            if (isThisMonthDay) {
                binding.root.setOnClickListener {
                    onDateClick(monthDayNumber)
                }
            } else {
                binding.tvDateNumber.isEnabled = false
                binding.tvDateNumber.setTextColor(binding.root.context.getColor(R.color.disabled_date_text_color))
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position<7) {
            CalendarValueType.WEEKDAY.ordinal
        } else {
            CalendarValueType.MONTH_DAY.ordinal
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType==CalendarValueType.WEEKDAY.ordinal) {
            CalendarWeekDayNameViewHolder(LayoutWeekdayNameBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        } else {
            CalendarMonthDayNumberViewHolder(LayoutMonthDayBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }

    override fun getItemCount(): Int {
        return 7+calendarMonthInfo.totalCalendarVisibleDays

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CalendarWeekDayNameViewHolder) {
            holder.bind(position)
        } else {
            holder as CalendarMonthDayNumberViewHolder

            if (position<=13) {
                if (calendarMonthInfo.firstCalendarRow[position-7]<=7) {
                    holder.bind(calendarMonthInfo.firstCalendarRow[position-7], true)
                } else {
                    holder.bind(calendarMonthInfo.firstCalendarRow[position-7], false)
                }

            } else {
                val monthDayNumber = position-6-calendarMonthInfo.firstDayWeekDayNumber
                Timber.i("Current Month Day is $monthDayNumber")
                if (monthDayNumber>calendarMonthInfo.numberOfDaysInMonth) {
                    holder.bind(calendarMonthInfo.lastCalendarRow[
                        calendarMonthInfo.lastDayWeekDayNumber+monthDayNumber-calendarMonthInfo.numberOfDaysInMonth], false)
                } else {
                    holder.bind(monthDayNumber, true)
                }
            }
        }
    }


}