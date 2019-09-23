package com.nyenjes.quotes.repositories

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class DbOpenHelper(context: Context) : ManagedSQLiteOpenHelper(context, "favorites.db", null, 1) {

    private val FAVE_TABLE_NAME: String = "favorites"
    private val QUOTES_TABLE_NAME: String = "quotes"
    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(
            FAVE_TABLE_NAME, true,
            "id" to INTEGER + PRIMARY_KEY,
            "contents" to TEXT
        )

        db?.createTable(
            QUOTES_TABLE_NAME, true,
            "id" to INTEGER + PRIMARY_KEY,
            "contents" to TEXT
        )    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
