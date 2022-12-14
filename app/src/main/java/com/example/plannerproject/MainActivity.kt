package com.example.plannerproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.plannerproject.database.CardDatabase
import com.example.plannerproject.databinding.ActivityMainBinding
import com.example.plannerproject.databinding.ActivitySignInBinding
import com.example.plannerproject.model.HomeFragmentView
import com.example.plannerproject.model.VmFactory
import com.example.plannerproject.view.ItemAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.nav_header.*

class MainActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    //FirebaseAuth
    private lateinit var firebaseAuth: FirebaseAuth
    //ActionBar
    private lateinit var actionBar: ActionBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //configure actionbar
        actionBar=supportActionBar!!
        actionBar.title="Planner"

        //init FirebaseAuth
        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

//        val binding:ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

        val drawerLayout:DrawerLayout = findViewById(R.id.activityMain)
        val navView: NavigationView= findViewById(R.id.nav_view)
        //ViewModel
        val application = requireNotNull(this).application
        val dataSource = CardDatabase.getInstance(application)!!.cardDao()
        val vmFactory = VmFactory(dataSource,application)
        val vm = ViewModelProvider(this,vmFactory).get(HomeFragmentView::class.java)


        toggle= ActionBarDrawerToggle(this,drawerLayout,R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {

            when(it.itemId){
                R.id.nav_home -> Toast.makeText(applicationContext,"Clicked Home",Toast.LENGTH_SHORT).show()
                R.id.nav_logout -> {
                    firebaseAuth.signOut()
                    checkUser()
                }
                R.id.nav_settings -> Toast.makeText(applicationContext,"Clicked Settings",Toast.LENGTH_SHORT).show()
                R.id.nav_login -> Toast.makeText(applicationContext,"Clicked Login",Toast.LENGTH_SHORT).show()
                R.id.nav_share -> Toast.makeText(applicationContext,"Clicked Share",Toast.LENGTH_SHORT).show()
            }
            true
        }

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragment) as NavHostFragment
        navController = navHostFragment.navController


        // dialog
        var addCardBtn = findViewById<FloatingActionButton>(R.id.addingBtn)
        addCardBtn.setOnClickListener {
            showAddCardFragment( );
        }

        val deleteAll = findViewById<FloatingActionButton>(R.id.deleteAll)
        deleteAll.setOnClickListener {
            vm.onClear()
        }


    }

    private fun checkUser() {
        //check user is logged in or not
        val firebaseUser= firebaseAuth.currentUser
        if(firebaseUser !=null){
            //user not null,user is logged in, get user info
            val email = firebaseUser.email
            //set text view
            val navigationView : NavigationView  = findViewById(R.id.nav_view)
            val headerView : View = navigationView.getHeaderView(0)
            val loggedtxt : TextView = headerView.findViewById(R.id.loggedtext)

            loggedtxt.text=email
        }
        else{
            //user is null,user is not logged in,go to login activity
            startActivity(Intent(this,SignInActivity::class.java))
            finish()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){

            return true
        }
        return super.onOptionsItemSelected(item)
    }

    // dialog method
    private fun showAddCardFragment(){
        val dialogFragment = AddCardDialogFragment()
        dialogFragment.show(supportFragmentManager,AddCardDialogFragment.TAG)
    }


}