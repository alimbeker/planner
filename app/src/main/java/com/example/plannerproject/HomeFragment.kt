package com.example.plannerproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.plannerproject.model.Cardsource
import com.example.plannerproject.data.CardData
import com.example.plannerproject.model.HomeFragmentView
import com.example.plannerproject.view.ItemAdapter



class  HomeFragment : Fragment() {
    private val viewModel: HomeFragmentView by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home2, container, false)
        val link = view.findViewById<TextView>(R.id.table)


        // show all recycleView cards

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)

        //method for generate cardList Adapter
        fun getList(list: List<CardData>): ItemAdapter {
            return ItemAdapter(this.context, list)
        }

        viewModel.cardsLiveData.observe(viewLifecycleOwner) {
            recyclerView.adapter = getList(it as List<CardData>)
        }

        recyclerView.setHasFixedSize(true)

//       // search tab
//        val searchView = view.findViewById<SearchView>(R.id.searchView)
//
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            val tempList: MutableList<CardData> = cards as MutableList<CardData>
//
//            override fun onQueryTextSubmit(p0: String?): Boolean {
//                searchView.clearFocus()
//                cards = tempList
//                cards = cards.filter { el ->
//                    el.task.toString().lowercase() == p0.toString().lowercase()
//                }
//                recyclerView.adapter = getList(cards as MutableList<CardData>)
//                return true
//            }
//
//            override fun onQueryTextChange(p0: String?): Boolean {
//                cards = tempList
//                cards = cards.filter { el ->
//                    el.task.toString().lowercase()
//                        .contains(p0.toString().lowercase(), ignoreCase = true)
//                }
//                recyclerView.adapter = getList(cards as MutableList<CardData>)
//                return true
//            }
//
//
//        })

        link.setOnClickListener { findNavController().navigate(R.id.action_homeFragment_to_tableFragment) }
        return view
    }


}