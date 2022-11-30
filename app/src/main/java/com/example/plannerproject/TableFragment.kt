package com.example.plannerproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.plannerproject.databinding.FragmentTableBinding


class TableFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_table, container, false)
        val link = view.findViewById<TextView>(R.id.cross)
        val binding:FragmentTableBinding = FragmentTableBinding.bind(view)

        link.setOnClickListener{findNavController().navigate(R.id.action_tableFragment_to_homeFragment)}

        return view
    }


}