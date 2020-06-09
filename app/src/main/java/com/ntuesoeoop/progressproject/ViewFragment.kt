package com.ntuesoeoop.progressproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

class ViewFragment : Fragment() {
    private val progressArgs: ViewFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.progress_view, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //create the return process for the return button
        view.findViewById<Button>(R.id.return_button).setOnClickListener {
            findNavController().navigate(R.id.action_progress_view_to_FirstFragment)
        }

        //store the information
        view.findViewById<TextView>(R.id.textview_progress_title).text = progressArgs.progressName
        view.findViewById<TextView>(R.id.textview_progress_description).text = progressArgs.progressDescription
        view.findViewById<TextView>(R.id.textview_view_progress_period).text = progressArgs.progressPeriod

    }

}