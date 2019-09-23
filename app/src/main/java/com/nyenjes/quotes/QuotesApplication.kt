package com.nyenjes.quotes

import android.app.Application
import com.nyenjes.quotes.managers.DataManager
import com.nyenjes.quotes.repositories.DbOpenHelper
import com.nyenjes.quotes.repositories.FavoritesRepository
import com.nyenjes.quotes.repositories.MainRepository

class QuotesApplication : Application() {

    var dbHelper: DbOpenHelper? = null
    var favoritesRepository: FavoritesRepository? = null
    var mainRepository: MainRepository? = null
    var dataManager: DataManager? = null
        private set


    override fun onCreate() {
        super.onCreate()

        dbHelper = DbOpenHelper(applicationContext)
        favoritesRepository = FavoritesRepository(dbHelper!!)
        mainRepository = MainRepository(dbHelper!!)
        dataManager = DataManager(favoritesRepository!!, mainRepository!!)
    }
}
