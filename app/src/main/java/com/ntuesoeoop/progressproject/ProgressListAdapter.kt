package com.ntuesoeoop.progressproject

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import org.koin.experimental.builder.getArguments
import java.util.*


class ProgressListAdapter internal constructor(
    context: Context,
    progressStatusUpdateListener: ProgressStatusUpdateListener
) :
    RecyclerView.Adapter<ProgressListAdapter.ProgressViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var progresses = emptyList<Progress>()
    private val progressStatusUpdateListener: ProgressStatusUpdateListener =
        progressStatusUpdateListener

    interface ProgressStatusUpdateListener {
        fun onProgressStatusUpdated(progress: Progress)
    }

    inner class ProgressViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val titleTextView: TextView = itemView.findViewById(R.id.text_view_progress_title)
        val levelTextView: TextView = itemView.findViewById(R.id.text_view_progress_level)
        val streakTextView: TextView = itemView.findViewById(R.id.text_view_progress_streak)
        val countTextView: TextView = itemView.findViewById(R.id.text_view_progress_completed_ratio)

        val isCompleted: CheckBox = itemView.findViewById(R.id.check_box_progress_complete)
        val currentNumber: EditText = itemView.findViewById(R.id.edittext_progress_currentnumber)
        val updateNumberBtn: Button = itemView.findViewById(R.id.button_update_currentnum)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgressViewHolder {
        var itemView = inflater.inflate(R.layout.progress_normal_card, parent, false)

        return ProgressViewHolder(itemView)
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ProgressViewHolder, position: Int) {
        val current = progresses[position]

        if (current.evaluate()){
            progressStatusUpdateListener.onProgressStatusUpdated(current)
        }

        val progressName = current.getName()
        val progressDescription = current.getDescription()
        val progressPeriod = current.getPeriod().toString()
        val progressTargetCompleted = current.getPeriod().toString()
        val progressUseTargetNum = current.getUseTargetNum()
        val progressTargetNum = current.getPeriod()
        val progressPassedPeriod = current.getPassedPeriod().toString()
        val progressCurrentCompleted = current.getCurrentCompleted().toString()
        val progressCount = current.getCount().toString()
        val progressTargetCount = current.getTargetCount().toString()
        val progressTargetNumber = current.getTargetNum().toString()
        val progressCurrentNumber = current.getCurrentNum().toString()
        val progressStrike = current.getStreak().toString()
        val progressMaxStrike = current.getMaxStreak().toString()


        //make the text be on the card
        holder.titleTextView.text = progressName
        holder.levelTextView.text = current.getLevel().toString()
        holder.streakTextView.text = current.getStreak().toString()
        holder.countTextView.text = current.getCompletedRatio()

        //make the card change to number mode
        if (current.getUseTargetNum()) {
            holder.currentNumber.visibility = View.VISIBLE
            holder.isCompleted.visibility = View.INVISIBLE
            holder.updateNumberBtn.visibility = View.VISIBLE
        }

        //set the value of currentNum
        holder.updateNumberBtn.setOnClickListener {
            if (holder.currentNumber.visibility == View.VISIBLE) {
                val currentnum: String = holder.currentNumber.text.toString()
                var currentNum = 0F

                if (currentnum != "") {
                    currentNum = currentnum.toFloat()
                }

                current.setCurrentNum(currentNum)

            }
            progressStatusUpdateListener.onProgressStatusUpdated(current)
        }

        holder.itemView.setOnClickListener {
            val action = FirstFragmentDirections.actionFirstFragmentToProgressView(
                progressName,
                progressDescription,
                progressPeriod,
                progressTargetCompleted,
                progressUseTargetNum,
                progressTargetNum,
                progressPassedPeriod,
                progressCurrentCompleted,
                progressCount,
                progressTargetCount,
                progressTargetNumber,
                progressCurrentNumber,
                progressStrike,
                progressMaxStrike
            )

            it.findNavController().navigate(action)
        }

        holder.titleTextView.text = progressName
        holder.levelTextView.text = current.getLevel().toString()
        holder.streakTextView.text = current.getStreak().toString()
        holder.countTextView.text = current.getCompletedRatio()
        holder.isCompleted.isChecked = current.getIsCompleted()
        holder.currentNumber.setText(current.getCurrentNum().toString())
        // println("${current.getName()} ${current.getDescription()} ${current.getUpdatedAt()}")


        //set whether it is completed
        holder.isCompleted.setOnClickListener {

            if (holder.isCompleted.isChecked) {
                current.setIsCompleted(true)
            } else {
                current.setIsCompleted(false)
            }

            setProgress(progresses)
            progressStatusUpdateListener.onProgressStatusUpdated(current)
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




