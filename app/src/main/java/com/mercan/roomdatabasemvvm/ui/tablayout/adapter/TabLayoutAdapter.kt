package com.mercan.roomdatabasemvvm.ui.tablayout.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.mercan.roomdatabasemvvm.R
import com.mercan.roomdatabasemvvm.ui.taskcompletedlist.TaskCompletedListFragment
import com.mercan.roomdatabasemvvm.ui.taskuncompletedlist.TaskUncompletedListFragment

class TabLayoutAdapter(
    fm: FragmentManager,
    val context: Context
) : FragmentStatePagerAdapter(fm) {
    override fun getCount() = 2

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> TaskUncompletedListFragment()
            1 -> TaskCompletedListFragment()
            else -> throw Exception()
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> context.getString(R.string.uncompleted_tasks)
            1 -> context.getString(R.string.completed_tasks)
            else -> throw Exception()
        }
    }
}