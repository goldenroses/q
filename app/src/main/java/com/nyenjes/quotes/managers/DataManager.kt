package com.nyenjes.quotes.managers

import com.nyenjes.quotes.model.Quote
import com.nyenjes.quotes.repositories.FavoritesRepository
import com.nyenjes.quotes.repositories.MainRepository

class DataManager (private val loveRepository: FavoritesRepository,
                   private val mainRepository: MainRepository
) {

    private var data: ArrayList<Quote>? = null
    private var loveCache: ArrayList<Quote>? = null

    fun getAll(tableName: String): ArrayList<Quote>? {

        data = mainRepository.getAll(tableName)
        return data
    }

    fun getAllFavorites(): ArrayList<Quote>? {

        if(loveCache == null) {
            loveCache = loveRepository.getAllFavorites()
        }

        return loveCache
    }

    fun addFavorite(quote: Quote) {

        loveCache?.add(quote)
        loveRepository.addFavorites(quote)
    }


    fun removeFavorite(quote: Quote) {
        loveRepository.removeFavoriteById(quote.id!!)
        loveCache!!.remove(quote)
        loveCache!!.filter {
            it.id != quote.id
        } as ArrayList<Quote>
    }

    fun getIsFavorite(quoteId: Long): Boolean {
        return loveRepository.isArticleFavorite(quoteId)
    }

}