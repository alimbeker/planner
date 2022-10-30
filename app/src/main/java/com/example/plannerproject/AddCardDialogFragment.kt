package com.example.plannerproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.plannerproject.data.Cardsource

class AddCardDialogFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView:View = inflater.inflate(R.layout.add_item,container,false)


        rootView.findViewById<Button>(R.id.backCard).setOnClickListener {
            dismiss()
        }
        rootView.findViewById<Button>(R.id.saveCard).setOnClickListener {
            val newCard = rootView.findViewById<EditText>(R.id.cardNo)
            val card = newCard.text.toString()

            Cardsource().insert(card,"Something new");

            Toast.makeText(context,"Succesfully added new $card card.",Toast.LENGTH_LONG).show()
            dismiss()
        }



        return  rootView
    }

    companion object{
        @JvmStatic val TAG = AddCardDialogFragment::class.java.simpleName
        @JvmStatic val REQUEST_KEY = "$TAG:defaultRequestKey"
        @JvmStatic val KEY_RESPONSE = "RESPONSE"
    }

}