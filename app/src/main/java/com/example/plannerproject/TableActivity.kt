package com.example.plannerproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.plannerproject.database.CardEntity

class TableActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table)

        val cards = intent.getParcelableExtra<CardEntity>("cards")
        if(cards != null) {
            val task: TextView = findViewById(R.id.list)
            val cardText: TextView = findViewById(R.id.mText)
            task.text = cards.task
            cardText.text = cards.description
        }
        /*val view = inflater.inflate(R.layout.fragment_table, container, false)
        val link = view.findViewById<TextView>(R.id.cross)
        link.setOnClickListener{findNavController().navigate(R.id.action_tableFragment_to_homeFragment)}
*/
        val btn = findViewById<TextView>(R.id.cross)
        btn.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
    }
}