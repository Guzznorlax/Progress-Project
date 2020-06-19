package com.ntuesoeoop.progressproject

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.create_progress.*
import kotlinx.android.synthetic.main.progress_normal_card.view.*

class ProgressListAdapter internal constructor(context: Context) :
    RecyclerView.Adapter<ProgressListAdapter.ProgressViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var progresses = emptyList<Progress>()




    inner class ProgressViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val titleTextView: TextView = itemView.findViewById(R.id.text_view_progress_title)
        val levelTextView: TextView = itemView.findViewById(R.id.text_view_progress_level)
        val streakTextView: TextView = itemView.findViewById(R.id.text_view_progress_streak)
        val countTextView: TextView = itemView.findViewById(R.id.text_view_progress_completed_ratio)
        val iscompleted: CheckBox = itemView.findViewById(R.id.check_box_progress_complete)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgressViewHolder {
        var itemView = inflater.inflate(R.layout.progress_normal_card, parent, false)

        return ProgressViewHolder(itemView)
    }



    override fun onBindViewHolder(holder: ProgressViewHolder, position: Int) {
        val current = progresses[position]
        var progressName = current.getName()
        var progressDescription = current.getDescription()
        var progressPeriod = current.getPeriod().toString()
        var progresstargetcompleted = current.getPeriod().toString()
        var progressusetargetnum = current.getUseTargetNum()
        var progresstargetnum = current.getPeriod().toInt()



        holder.itemView.setOnClickListener {
            val action = FirstFragmentDirections.actionFirstFragmentToProgressView(
                progressName,
                progressDescription,
                progressPeriod,
                progresstargetcompleted,
                progressusetargetnum,
                progresstargetnum
            )

            it.findNavController().navigate(action)
        }

        holder.titleTextView.text = progressName
        holder.levelTextView.text = current.getLevel().toString()
        holder.streakTextView.text = current.getStreak().toString()
        holder.countTextView.text = current.getCompletedRatio()

        holder.iscompleted.setOnClickListener {
            if (holder.iscompleted.isChecked) {
                current.setIsCompleted(true)
            } else {
                current.setIsCompleted(false)
            }
            //println(current.getIsCompleted())
        }
    }


    override fun getItemCount(): Int {
        return progresses.size
    }
    internal fun setProgress(progresses: List<Progress>) {
        this.progresses = progresses
        notifyDataSetChanged()
    }


}