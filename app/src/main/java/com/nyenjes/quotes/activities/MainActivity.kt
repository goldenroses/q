package com.nyenjes.quotes.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nyenjes.quotes.QuotesApplication
import com.nyenjes.quotes.R
import com.nyenjes.quotes.fragment.CategoriesFragment
import com.nyenjes.quotes.fragment.FavoritesFragment
import com.nyenjes.quotes.fragment.QuotesDisplayFragment
import com.nyenjes.quotes.managers.DataManager
import com.nyenjes.quotes.model.Quote

class MainActivity : AppCompatActivity() {
    
    private val TAG: String = "MainActivity"

    val quotes : ArrayList<Quote> = ArrayList()

    private var dataManager: DataManager? = null

    private val categoriesFragment: CategoriesFragment
    private val quotesDisplayFragment: QuotesDisplayFragment
    private val favoritesFragment: FavoritesFragment

    init {
        categoriesFragment = CategoriesFragment()
        favoritesFragment = FavoritesFragment()
        quotesDisplayFragment = QuotesDisplayFragment()
    }

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)

        when (item.itemId) {
            R.id.navigation_home -> transaction.replace(R.id.fragment_container, categoriesFragment)
            R.id.navigation_favorites -> transaction.replace(R.id.fragment_container, favoritesFragment)
            R.id.navigation_notifications -> transaction.replace(R.id.fragment_container, favoritesFragment)
        }

        transaction.commit()
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        dataManager = (applicationContext as QuotesApplication).dataManager

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragment_container, categoriesFragment)
        transaction.commit()

        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }

}
