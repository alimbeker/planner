package com.example.plannerproject

import SwipeToDelete
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.SearchView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plannerproject.database.CardDatabase
import com.example.plannerproject.database.CardEntity
import com.example.plannerproject.databinding.ActivityMainBinding
import com.example.plannerproject.databinding.FragmentHome2Binding
import com.example.plannerproject.databinding.ListItemBinding
import com.example.plannerproject.databinding.NavHeaderBinding
import com.example.plannerproject.model.HomeFragmentView
import com.example.plannerproject.model.VmFactory
import com.example.plannerproject.view.ItemAdapter
import kotlinx.android.synthetic.main.fragment_home2.*


class  HomeFragment : Fragment() {

    lateinit var newAdapter:ItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home2, container, false)
        val link = view.findViewById<TextView>(R.id.table)
        val binding:FragmentHome2Binding = FragmentHome2Binding.bind(view)

        //implement viewModel
        val application = requireNotNull(this.activity).application
        val dataSource = CardDatabase.getInstance(application)!!.cardDao()
        val vmFactory = VmFactory(dataSource,application)
        val vm = ViewModelProvider(this,vmFactory).get(HomeFragmentView::class.java)



        // show all recycleView cards
        val recyclerView = binding.recyclerView

        vm.filteredCards.observe(viewLifecycleOwner) {
            newAdapter= ItemAdapter()
            newAdapter.submitList(it)
            recyclerView.adapter=newAdapter
            recyclerView.layoutManager=LinearLayoutManager(this.context)
            newAdapter.onItemClick = {
                val intent = Intent(this.context, TableActivity::class.java)
                intent.putExtra("cards", it)
                startActivity(intent)
            }
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


        val searchView = binding.searchView
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
