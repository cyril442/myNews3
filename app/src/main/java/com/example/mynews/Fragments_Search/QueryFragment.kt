package com.example.mynews.Fragments_Search


import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText

import com.example.mynews.R
import kotlinx.android.synthetic.*



class QueryFragment : Fragment() {

    companion object {
        fun newInstance() = QueryFragment()
    }

    lateinit var query : EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val view = inflater.inflate(R.layout.fragment_query, container, false)

        var resultEditText = view.findViewById<EditText>(R.id.edit_query)
        return view
    }


}
