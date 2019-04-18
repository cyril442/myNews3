package com.example.mynews.Fragments_Notification


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.Toast
import android.widget.ViewSwitcher

import com.example.mynews.R
import java.lang.ClassCastException


private lateinit var listener :JobSchedulerFragment.onJobSchedulerFragmentSelected

class JobSchedulerFragment : Fragment() {

  //  internal lateinit var btnSwitch : Switch

    companion object {
        fun newInstance() = JobSchedulerFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_job_scheduler, container, false)
        return view

    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//
//        btnSwitch = view.findViewById(R.id.btnSwitch)
//
//        btnSwitch.setOnClickListener{
//
//            if (btnSwitch.isChecked) {
//                Toast.makeText(activity, "Switch Button is checked", Toast.LENGTH_SHORT).show()
//            } else {
//                Toast.makeText(activity, "Switch Button is Unchecked", Toast.LENGTH_SHORT).show()
//            }
//
//        }
//
//        super.onViewCreated(view, savedInstanceState)
//    }

    interface onJobSchedulerFragmentSelected {
        fun onJobSchedulerFragmentSelected (jobSchedulerFragment: JobSchedulerFragment)
    }

    override fun onAttach(context: Context?) {

        if(context is onJobSchedulerFragmentSelected ){
            listener = context
        } else {
            throw ClassCastException(context.toString() + " must implement onJobSchedulerFragmentSelected.")
        }

        super.onAttach(context)
    }

}
