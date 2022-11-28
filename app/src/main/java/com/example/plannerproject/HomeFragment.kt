package com.example.plannerproject

import SwipeToDelete
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.plannerproject.database.CardDatabase
import com.example.plannerproject.database.CardEntity
import com.example.plannerproject.model.HomeFragmentView
import com.example.plannerproject.model.VmFactory
import com.example.plannerproject.view.ItemAdapter


class  HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home2, container, false)
        val link = view.findViewById<TextView>(R.id.table)

        //implement viewModel
        val application = requireNotNull(this.activity).application
        val dataSource = CardDatabase.getInstance(application)!!.cardDao()
        val vmFactory = VmFactory(dataSource,application)
        val vm = ViewModelProvider(this,vmFactory).get(HomeFragmentView::class.java)

        // show all recycleView cards
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)

        //method for generate cardList Adapter
        fun getList(list: MutableList<CardEntity>): ItemAdapter {
            return ItemAdapter(this.context, list)
        }


        vm.filteredCards.observe(viewLifecycleOwner) {
            recyclerView.adapter = getList(it as MutableList<CardEntity>)
        }

        recyclerView.setHasFixedSize(true)
        val swipeToDelete = object :SwipeToDelete(){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val a = vm.filteredCards.value?.get(position)
                vm.onSwipeDelete(a)
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeToDelete)
        itemTouchHelper.attachToRecyclerView(recyclerView)


       // search tab
        val searchView = view.findViewById<SearchView>(R.id.searchView)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(text: String?): Boolean {
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(text: String?): Boolean {
                vm.onQueryTextChange(text)
                return true
            }


        })

        link.setOnClickListener { findNavController().navigate(R.id.action_homeFragment_to_tableFragment) }
        return view
    }
}
