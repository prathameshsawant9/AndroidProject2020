package com.leo.android.project.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import com.leo.android.project.R
import com.leo.android.project.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.nav_header_main.view.*

class MainActivity : AppCompatActivity() {

    private val drawerLayout by lazy {
        findViewById<DrawerLayout>(R.id.drawerLayout)
    }

    private val toolbar by lazy {
        findViewById<Toolbar>(R.id.appToolbar)
    }

    private val actionBarDrawable by lazy {
        ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.mainNavigationOpen,
            R.string.mainNavigationClose
        )
    }

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        init()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        drawerLayout.removeDrawerListener(actionBarDrawable)
    }

    private fun init(){
        setSupportActionBar(toolbar)
        drawerLayout.addDrawerListener(actionBarDrawable)
        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
        }

        actionBarDrawable.syncState()

        listener()
    }

    private fun listener(){
        binding.navView.imgViewGo.setOnClickListener{
            drawerLayout.closeDrawers()
        }
    }
}
