package com.ntuesoeoop.progressproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProgressListAdapter : RecyclerView.Adapter<ProgressListAdapter.ViewHolder> {
    private var context: Context
    private var progressList: MutableList<Progress>

    constructor(context: Context, progressList: MutableList<Progress>) : super() {
        this.context = context
        this.progressList = progressList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var progressCard =
            LayoutInflater.from(context).inflate(R.layout.progress_normal_card, parent, false)
        var viewHolder = ViewHolder(progressCard)

        viewHolder.titleTextView = progressCard.findViewById(R.id.text_view_progress_title)
        viewHolder.levelTextView = progressCard.findViewById(R.id.text_view_progress_level)
        viewHolder.streakTextView = progressCard.findViewById(R.id.text_view_progress_streak)
        viewHolder.countTextView =
            progressCard.findViewById(R.id.text_view_progress_completed_ratio)

        return viewHolder
    }

    override fun getItemCount(): Int {
        return progressList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val progress = progressList[position]

        holder.titleTextView.text = progress.getName()
        holder.levelTextView.text = progress.getLevel().toString()
        holder.streakTextView.text = progress.getStreak().toString()
        holder.countTextView.text = progress.getCompletedRatio()
    }

    class ViewHolder : RecyclerView.ViewHolder {
        lateinit var titleTextView: TextView
        lateinit var levelTextView: TextView
        lateinit var streakTextView: TextView
        lateinit var countTextView: TextView

        constructor(itemView: View) : super(itemView)
    }

}