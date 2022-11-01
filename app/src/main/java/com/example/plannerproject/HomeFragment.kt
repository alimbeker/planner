package com.example.plannerproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.plannerproject.data.Cardsource
import com.example.plannerproject.view.ItemAdapter


class  HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home2, container, false)
        val link = view.findViewById<TextView>(R.id.table)


        // show all recycleView cards
        val cards = Cardsource().loadCard()
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.adapter = ItemAdapter(this.context!!, cards)

        recyclerView.setHasFixedSize(true)
        //

        link.setOnClickListener{findNavController().navigate(R.id.action_homeFragment_to_tableFragment)}
        return view
    }


}