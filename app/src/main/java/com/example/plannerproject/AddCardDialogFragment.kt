package com.example.plannerproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.plannerproject.database.CardDatabase
import com.example.plannerproject.databinding.AddItemBinding
import com.example.plannerproject.model.HomeFragmentView
import com.example.plannerproject.model.VmFactory
import java.util.concurrent.TimeUnit

class AddCardDialogFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView:View = inflater.inflate(R.layout.add_item,container,false)
        var binding:AddItemBinding = AddItemBinding.bind(rootView)

        binding.backCard.setOnClickListener {
            dismiss()
        }

        //implement viewModel
        val application = requireNotNull(this.activity).application
        val dataSource = CardDatabase.getInstance(application)!!.cardDao()
        val vmFactory = VmFactory(dataSource,application)
        val vm = ViewModelProvider(this,vmFactory).get(HomeFragmentView::class.java)

        binding.saveCard.setOnClickListener {
            val newCardTask = rootView.findViewById<EditText>(R.id.newCardTask).text.toString()
            val newCardDesc = rootView.findViewById<EditText>(R.id.newCardDesc).text.toString()

            vm.onClickInsert(newCardTask,newCardDesc)

            Toast.makeText(context,"Succesfully added new $newCardTask card.",Toast.LENGTH_LONG).show()

            val myWorkRequest = OneTimeWorkRequestBuilder<TodoWorker>()
                .setInitialDelay(10, TimeUnit.SECONDS)
                .setInputData(
                    workDataOf(
                    "title" to "Todo Created",
                    "message" to "A new todo has been created!")
                )
                .build()
            WorkManager.getInstance(requireContext()).enqueue(myWorkRequest)


            dismiss()

        }
        return  rootView
    }
    companion object{
        @JvmStatic val TAG = AddCardDialogFragment::class.java.simpleName
    }

}