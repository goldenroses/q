package com.nyenjes.quotes.repositories

import com.nyenjes.quotes.model.Quote
import org.jetbrains.anko.db.*

class FavoritesRepository(val dbOpenHelper: DbOpenHelper) {

    private val TABLE_NAME = "favorites"

    fun addFavorites(quote: Quote) {

        dbOpenHelper.use {
            insert(
                TABLE_NAME,
                "content" to quote.content
            )
        }
    }

    fun removeFavoriteById(quoteId: Long) {
        dbOpenHelper.use {
            delete(TABLE_NAME, "id = {quoteId}", "quoteId" to quoteId)
        }
    }

    fun isArticleFavorite(quoteId: Long): Boolean {
        var quotes = getAllFavorites()
        return quotes!!.any { quote ->
            quote.id == quoteId
        }

    }

    data class QuoteItem(
        var id: Int,
        var title: String,
        var description: String,
        var imageUrl: String,
        var cardImage: String,
        var content: String
    )

    fun getAllFavorites(): ArrayList<Quote>? {

        var quotes = ArrayList<Quote>()

        dbOpenHelper.use {
            var results = select(TABLE_NAME)
            var data = results.parseList(classParser<Quote>())
            quotes.addAll(data)
        }

        return quotes
    }
}