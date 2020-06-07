package com.ntuesoeoop.progressproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.navigation.Navigation


import androidx.navigation.ui.AppBarConfiguration


import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_first.*


class MainActivity : AppCompatActivity() {
//    private lateinit var progressViewModel: ProgressViewModel

    private lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
//        }
//        fab.setOnClickListener {view ->
//            findNavController(view).navigate(R.id.action_FirstFragment_to_createProgressFragment)
//        }

    }

    //make button_view button jump to progress_view
    fun onBtnClick(view: View) {
        //Toast.makeText(this, "hi", Toast.LENGTH_SHORT).show();
        Navigation.findNavController(view).navigate(R.id.action_FirstFragment_to_progress_view)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> {
                Toast.makeText(this, "you entered settings", Toast.LENGTH_SHORT).show();
                true
            }
            R.id.about_me -> {
                Toast.makeText(this, "I am your helper, Demo", Toast.LENGTH_SHORT).show();
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


//    override fun onAttachFragment(fragment: Fragment) {
//        super.onAttachFragment(fragment)
//        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_progress_list)
//        val adapter = ProgressListAdapter(this)
//        recyclerView.adapter = adapter
//        recyclerView.layoutManager = LinearLayoutManager(this)
//
//        // Get a new or existing ViewModel from the ViewModelProvider.
//        progressViewModel = ViewModelProvider(this).get(ProgressViewModel::class.java)
//
//        progressViewModel.allProgresses.observe(this, Observer { progresses ->
//            // Update the cached copy of the words in the adapter.
//            progresses?.let { adapter.setProgress(it) }
//        })
//    }
}
