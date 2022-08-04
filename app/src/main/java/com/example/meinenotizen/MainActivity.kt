package com.example.meinenotizen

import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.meinenotizen.databinding.ActivityMainBinding
import com.example.meinenotizen.home.HomeFragment
import com.google.android.material.tabs.TabLayout

//const val TAG = "Firebase"


class MainActivity : AppCompatActivity() {

    /* -------------------- Klassen Variablen -------------------- */

    private val REQUEST_IMAGE_CAPTURE = 13
    private lateinit var mainActivityViewModel: MainViewModel

    /** Bindet das XML-View mit der Klasse um auf die Elemente zugreifen zu können */
    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController

    lateinit var drawer: DrawerLayout
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    /* -------------------- Lifecycle -------------------- */

    /**
     * Lifecycle Methode, wird aufgerufen wenn Activity erstellt wird
     *
     * @param savedInstanceState      Save state vom view
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val takePhotoIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        setSupportActionBar(binding.toolbar)

        mainActivityViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        replaceFragment(HomeFragment.newInstance(), true)

        setSupportActionBar(binding.toolbar)


        drawer = binding.drawer
        actionBarDrawerToggle =
            ActionBarDrawerToggle(this, drawer, R.string.nav_open, R.string.nav_close)
        drawer.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        // navigation zum ein und ausblenden


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentMain) as NavHostFragment
        navController = navHostFragment.navController
        val bottomNavigationView = binding.bottomNavigationView
        setupWithNavController(bottomNavigationView, navController)

        val navigationView = binding.navigationView
        setupWithNavController(navigationView, navController)

        binding.navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.homeFragment -> {
                    println("Home Fragment clicked")
                    navController.navigate(R.id.homeFragment)
                    true
                }
                R.id.notizenFragment -> {
                    println("Notizen Fragment clicked")
                    navController.navigate(R.id.notizenFragment)
                    true
                }
                R.id.scanFragment -> {
                    println("Scan Fragment clicked")
                    navController.navigate(R.id.scanFragment)
                    true
                }
                R.id.colorFragment -> {
                    println("Color Fragment clicked")
                    navController.navigate(R.id.colorFragment)
                    true
                }


                R.id.scanLibButton -> {
                    println("Scan Fragment clicked")

                    try {
                        startActivityForResult(takePhotoIntent, REQUEST_IMAGE_CAPTURE)
                    } catch (ex: Exception) {
                        Log.e("CAM", ex.toString())
                    }
                    true
                }

                R.id.nav_share -> {
                    var detailText = ""
                    var tv_songTitle = ""
                    println("Teilen Fragment clicked")
                    val intent = Intent.createChooser(
                        Intent()
                            .apply {
                                action = Intent.ACTION_SEND
                                putExtra(
                                    Intent.EXTRA_TEXT,
                                    "Ich möchte die PDf oder Foto  $detailText mit dir Teilen :)"
                                )
                                type = "text/plain"
                            }, null
                    )
                    startActivity(intent)
                    true
                }

            }
            true
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val drawerToggle =
            ActionBarDrawerToggle(this, binding.drawer, R.string.nav_open, R.string.nav_close)
        binding.drawer.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.scanLibButton.setOnClickListener {
            try {
                startActivityForResult(takePhotoIntent, REQUEST_IMAGE_CAPTURE)
            } catch (ex: Exception) {
                Log.e("CAM", ex.toString())
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE) {
            try {
                val imageBitmap = data?.extras?.get("data") as Bitmap
                mainActivityViewModel.setBitmap(imageBitmap)
                navController.navigate(R.id.scanFragment)
            } catch (e: Exception) {
                Toast.makeText(this, "Something went wrong: $e", Toast.LENGTH_LONG).show()
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
    fun replaceFragment(fragment: Fragment, istransition: Boolean) {

        val fragmentTransition = supportFragmentManager.beginTransaction()

        if (istransition) {
            fragmentTransition.setCustomAnimations(
                android.R.anim.slide_out_right,
                android.R.anim.slide_in_left
            )
        }
        fragmentTransition.replace(R.id.homeFragment, fragment)
            .addToBackStack(fragment.javaClass.simpleName)
        fragmentTransition.commit()
    }


    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        return when (item.itemId) {
            android.R.id.home -> {
                drawer.openDrawer(GravityCompat.START)
                println("CLICK:!" + item)
                true
            }
            else -> super.onOptionsItemSelected(item)

        }
    }

}