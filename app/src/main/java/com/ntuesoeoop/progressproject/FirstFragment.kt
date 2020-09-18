package com.ntuesoeoop.progressproject


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

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

class FirstFragment : Fragment() {
    private lateinit var progressViewModel: ProgressViewModel
    private val createProgressArgs: FirstFragmentArgs by navArgs()


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

        // Get a new or existing ViewModel from the ViewModelProvider.
        progressViewModel = ViewModelProvider(this).get(ProgressViewModel::class.java)

        val adapter = context?.let {
            ProgressListAdapter(it, object : ProgressListAdapter.ProgressStatusUpdateListener {
                override fun onProgressStatusUpdated(progress: Progress) {
                    progressViewModel.update(progress)
                }
            })
        }

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_progress_list)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Update the cached copy of the progresses in the adapter.
        progressViewModel.allProgresses.observe(viewLifecycleOwner, Observer { progresses ->
            progresses?.let {
                adapter?.setProgress(it)
            }
        })

        // create the progress
        if (createProgressArgs.progressName != " " && createProgressArgs.progressName != "") {
            var newProgress = Progress(
                createProgressArgs.progressName,
                description = createProgressArgs.progressDescription,
                period = createProgressArgs.progressPeriod,
                targetCompleted = createProgressArgs.progressTargetCompleted,
                useTargetNum = createProgressArgs.progressUsetargenum,
                targetNum = createProgressArgs.progressTargetNum,
                createdAt = ISODate().getFormattedCurrentTime()
            )

            println(
                createProgressArgs.progressName + createProgressArgs.progressDescription + createProgressArgs.progressPeriod + createProgressArgs.progressTargetCompleted +
                        createProgressArgs.progressUsetargenum + createProgressArgs.progressTargetNum
            )
            progressViewModel.insert(newProgress)
        }

    }

}