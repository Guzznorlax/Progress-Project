package com.ntuesoeoop.progressproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_first.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
    var progressList: MutableList<Progress> = mutableListOf()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        addProgress(progressList)

        recycler_view_progress_list.layoutManager = LinearLayoutManager(context)
        recycler_view_progress_list.adapter =
            context?.let { ProgressListAdapter(it, progressList = progressList) }
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

//        view.findViewById<Button>(R.id.button_first).setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//        }
        view.findViewById<FloatingActionButton>(R.id.floating_btn_create_progress).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_createProgressFragment)
        }
    }

    private fun addProgress(progressList: MutableList<Progress>) {
        var progress = Progress("Test Progress")
        for (i in 1..10) {
            progressList.add(progress)
        }
    }


}
