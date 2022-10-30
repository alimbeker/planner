package com.example.plannerproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.FragmentResultListener
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.plannerproject.data.Cardsource
import com.example.plannerproject.model.CardData
import com.example.plannerproject.view.ItemAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {


    private lateinit var addsBtn:FloatingActionButton
    private lateinit var recv:RecyclerView
    private lateinit var cardList:ArrayList<CardData>
    private lateinit var itemAdapter:ItemAdapter



    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragment) as NavHostFragment
        navController = navHostFragment.navController


        // dialog
        var addCardBtn = findViewById<FloatingActionButton>(R.id.addingBtn)
        addCardBtn.setOnClickListener {
            showAddCardFragment( );
        }


    }

    // dialog method
    private fun showAddCardFragment(){
        val dialogFragment = AddCardDialogFragment()
        dialogFragment.show(supportFragmentManager,AddCardDialogFragment.TAG)
    }


}