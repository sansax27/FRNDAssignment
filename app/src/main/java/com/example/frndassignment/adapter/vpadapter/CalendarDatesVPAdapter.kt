package com.example.frndassignment.adapter.vpadapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.frndassignment.ui.fragment.CalendarDatesFragment
import com.example.frndassignment.ui.fragment.CalendarFragment
import timber.log.Timber

class CalendarDatesVPAdapter(private var year: Int, lifecycle: Lifecycle, fragmentManager: FragmentManager, private val onDateClick: () -> Unit): FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 12
    }

    fun changeTime(year: Int) {
        this.year = year
        notifyDataSetChanged()
    }

    fun getCurrentYear(): Int {
        return year
    }
    override fun createFragment(position: Int): Fragment {
        Timber.i("position $position")
        return CalendarDatesFragment.newInstance(year, position+1) {
            onDateClick()
        }
    }
}