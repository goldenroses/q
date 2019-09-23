package com.nyenjes.quotes.repositories

import com.nyenjes.quotes.model.Quote
import org.jetbrains.anko.db.*

class MainRepository(val dbOpenHelper: DbOpenHelper) {

    fun isArticleFavorite(quoteId: Long, tableName: String): Boolean {
        var quotes = getAll(tableName)
        return quotes!!.any { quote ->
            quote.id == quoteId
        }

    }

    fun getAll(tableName: String): ArrayList<Quote>? {

        var quotes = ArrayList<Quote>()

        dbOpenHelper.use {
            var results = select(tableName)
            var data = results.parseList(classParser<Quote>())
            quotes.addAll(data)
        }

        return quotes
    }

//    fun insertData(tableName: String): Boolean? {
//        val qry = open('create_table_user.sql', 'r').read()
//
//        dbOpenHelper.use {
//            var results = select(tableName)
//            execSQL(qry)
//        }
//
//        return true
//    }
}