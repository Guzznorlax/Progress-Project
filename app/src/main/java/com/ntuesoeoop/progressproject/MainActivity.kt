package com.ntuesoeoop.progressproject

import android.content.SharedPreferences
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
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


        val cal = Calendar.getInstance()
        val currentDay = cal.get(Calendar.MINUTE)
        val sharedPreferences: SharedPreferences = getSharedPreferences("appInfo", 0)
        val lastDay = sharedPreferences.getInt("minute", 0)
        if (lastDay != currentDay) {
            val editor = sharedPreferences.edit()
            editor.putInt("minute", currentDay)
            editor.commit()
            // Your once a day code here
            Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show()
        }





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




}
