package com.ntuesoeoop.progressproject

import android.content.Context
import android.database.Observable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_first.*
import org.koin.experimental.property.inject

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

class FirstFragment : Fragment() {
    private lateinit var progressViewModel: ProgressViewModel

    var progressList: MutableList<Progress> = mutableListOf()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        addProgress()


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
            // Update the cached copy of the words in the adapter.
            progresses?.let {
                adapter?.setProgress(it)
            }
        })
    }

    private fun addProgress() {
        var progress = Progress("Test Progress")
        for (i in 1..2) {
            progressViewModel.insert(progress)
        }
    }
}