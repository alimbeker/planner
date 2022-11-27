package com.example.plannerproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.plannerproject.database.CardDatabase
import com.example.plannerproject.model.HomeFragmentView
import com.example.plannerproject.model.VmFactory

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

        //implement viewModel
        val application = requireNotNull(this.activity).application
        val dataSource = CardDatabase.getInstance(application)!!.cardDao()
        val vmFactory = VmFactory(dataSource,application)
        val vm = ViewModelProvider(this,vmFactory).get(HomeFragmentView::class.java)

        rootView.findViewById<Button>(R.id.saveCard).setOnClickListener {
            val newCardTask = rootView.findViewById<EditText>(R.id.newCardTask).text.toString()
            val newCardDesc = rootView.findViewById<EditText>(R.id.newCardDesc).text.toString()

            vm.onClickInsert(newCardTask,newCardDesc)

            Toast.makeText(context,"Succesfully added new $newCardTask card.",Toast.LENGTH_LONG).show()
            dismiss()
        }

        /*rootView.findViewById<ImageView>(R.id.more).setOnClickListener {
            vm.onClickDelete()
        }*/


        return  rootView
    }
    companion object{
        @JvmStatic val TAG = AddCardDialogFragment::class.java.simpleName
    }

}