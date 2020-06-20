package com.ntuesoeoop.progressproject

import android.app.AlarmManager
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.progress_normal_card.*
import java.util.*


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

class FirstFragment : Fragment() {
    private lateinit var progressViewModel: ProgressViewModel
    private val createProgressArgs: FirstFragmentArgs by navArgs()

    // unused
    companion object {
        fun getInstance(progressTitle: String, progressDescription: String?): FirstFragment {
            val fragment = FirstFragment()
            fragment.createProgress(progressTitle, progressDescription)
            return fragment
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<FloatingActionButton>(R.id.floating_btn_create_progress)
            .setOnClickListener {
                findNavController().navigate(R.id.action_FirstFragment_to_createProgressFragment)
            }

        val adapter = context?.let { ProgressListAdapter(it) }
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_progress_list)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        // Get a new or existing ViewModel from the ViewModelProvider.
        progressViewModel = ViewModelProvider(this).get(ProgressViewModel::class.java)

        progressViewModel.allProgresses.observe(viewLifecycleOwner, Observer { progresses ->
            // Update the cached copy of the progresses in the adapter.
            progresses?.let {
                adapter?.setProgress(it)
            }
        })

//        if(createProgressArgs.progressUsetargenum){
//            view.findViewById<EditText>(R.id.edittext_progress_currentnumber).visibility = View.VISIBLE
//            view.findViewById<CheckBox>(R.id.check_box_progress_complete).visibility = View.INVISIBLE
//            println("hillo")
//        }else{
//            if(view.findViewById<EditText>(R.id.edittext_progress_currentnumber)!= null){
//                view.findViewById<EditText>(R.id.edittext_progress_currentnumber).visibility = View.INVISIBLE
//                view.findViewById<CheckBox>(R.id.check_box_progress_complete).visibility = View.VISIBLE
//                println("goodmorning")
//            }
//        }


        if (createProgressArgs.progressName != " " && createProgressArgs.progressName != "") {
            var newProgress = Progress(
                createProgressArgs.progressName,
                description = createProgressArgs.progressDescription,
                period = createProgressArgs.progressPeriod,
                targetCompleted = createProgressArgs.progressTargetCompleted,
                useTargetNum = createProgressArgs.progressUsetargenum,
                targetNum = createProgressArgs.progressTargetNum
            )

            println(createProgressArgs.progressName + createProgressArgs.progressDescription + createProgressArgs.progressPeriod + createProgressArgs.progressTargetCompleted +
                    createProgressArgs.progressUsetargenum + createProgressArgs.progressTargetNum)
            progressViewModel.insert(newProgress)
        }


        val cal = Calendar.getInstance()
        val currentDay = cal.get(Calendar.DAY_OF_MONTH)
        val sharedPreferences: SharedPreferences? = activity?.getSharedPreferences("appInfo", 0)
        val lastDay = sharedPreferences?.getInt("day", 0)
        if (lastDay != currentDay) {
            val editor = sharedPreferences?.edit()
            editor?.putInt("day", currentDay)
            editor?.commit()
            // put the once a day code here
            println("hihi" + currentDay)

        }


        //  測試用(minute)
        //        val cal = Calendar.getInstance()
        //        val currentminute = cal.get(Calendar.MINUTE)
        //        val sharedPreferences: SharedPreferences? = activity?.getSharedPreferences("appInfo", 0)
        //        val lastminute = sharedPreferences?.getInt("minute", 0)
        //        if (lastminute != currentminute) {
        //            val editor = sharedPreferences?.edit()
        //            editor?.putInt("minute", currentminute)
        //            editor?.commit()
        //            // put the once a minute code here
        //            println("hihi" + currentminute)
        //
        //        }

    }

    fun createProgress(title: String, description: String?) {
        var newProgress = Progress(title)
        progressViewModel.insert(newProgress)
    }

}