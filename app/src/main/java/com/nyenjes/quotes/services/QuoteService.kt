package com.nyenjes.quotes.services

import com.nyenjes.quotes.model.Quote
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface QuoteService {

    @GET("/quote/{category}")
    fun  getQuotes(@Path("category") category: String): Call<List<Quote>>

    @GET("/quote/{id}")
    fun getPlaceById(@Path("id") id: Long): Call<Quote>
}
