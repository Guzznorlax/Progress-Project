package com.ntuesoeoop.progressproject

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import org.koin.experimental.builder.getArguments
import java.util.*


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
        var progresstargetcompleted = current.getTargetCompleted().toString()
        var progressusetargetnum = current.getUseTargetNum()
        var progresstargetnum = current.getPeriod()
        var progressPassedPeriod = current.getPassedPeriod().toString()
        var progressCurrentCompleted = current.getCurrentCompleted().toString()
        var progressCount = current.getCount().toString()
        var progressTargetCount = current.getTargetCount().toString()
        var progressTargetNumber = current.getTargetNum().toString()
        var progressCurrentNumber = current.getCurrentNum().toString()
        var progressStrike = current.getStreak().toString()
        var progressMaxStrike = current.getMaxStreak().toString()



        holder.itemView.setOnClickListener {
            val action = FirstFragmentDirections.actionFirstFragmentToProgressView(
                progressName,
                progressDescription,
                progressPeriod,
                progresstargetcompleted,
                progressusetargetnum,
                progresstargetnum,
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

        holder.iscompleted.setOnClickListener {
            if (holder.iscompleted.isChecked) {
                current.setIsCompleted(true)
            } else {
                current.setIsCompleted(false)
            }
            setProgress(progresses)
            //println(current.getIsCompleted())
        }



        // wrong
        //        current.evaluate()
        //        val calender = Calendar.getInstance()
        //        val hour: String = calender.get(Calendar.HOUR_OF_DAY).toString()
        //        val minute: String = calender.get(Calendar.MINUTE).toString()
        //        val second: String = calender.get(Calendar.SECOND).toString()
        //        if(hour == "1"&&minute == "54"&&second=="0"){
        //            current.evaluate()
        //            println("good")
        //        }
        //        println("hour" +hour+minute+second)



        // wrong
        //val alarmManager =
        //            context.getSystemService(Context.ALARM_SERVICE) as? AlarmManager
        //
        //        PendingIntent
        //        alarmMgr?.setInexactRepeating(
        //            AlarmManager.ELAPSED_REALTIME,
        //            SystemClock.elapsedRealtime() + AlarmManager.INTERVAL_HALF_HOUR,
        //            AlarmManager.INTERVAL_HALF_HOUR,
        //            alarmIntent
        //        )


        // wrong
        //var alarmMgr : AlarmManager ? = null
        //        lateinit var alarmIntent : PendingIntent
        //
        //        // Set the alarm to start at approximately 2:00 pm
        //        val calendar : Calendar = Calendar . getInstance (). apply {
        //            timeInMillis = System . currentTimeMillis ()
        //            set ( Calendar.HOUR_OF_DAY , 14 )
        //        }
        //
        //        alarmMgr ?. setInexactRepeating (
        //            AlarmManager . RTC,
        //            calendar . timeInMillis,
        //            AlarmManager.INTERVAL_FIFTEEN_MINUTES ,
        //            alarmIntent
        //        )

        // wrong
        //val cal = Calendar.getInstance()
        //        val currentDay = cal.get(Calendar.DAY_OF_MONTH)
        //
        //        val sharedPreferences: SharedPreferences = getSharedPreferences("appInfo", 0)
        //        val lastDay = sharedPreferences.getInt("day", 0)
        //
        //        if (lastDay != currentDay) {
        //            val editor = sharedPreferences.edit()
        //            editor.putInt("weekOfYear", currentDay)
        //            editor.commit()
        //            // Your once a day code here
        //            current.evaluate()
        //        }



    }


    override fun getItemCount(): Int {
        return progresses.size
    }
    internal fun setProgress(progresses: List<Progress>) {
        this.progresses = progresses
        notifyDataSetChanged()
    }


}