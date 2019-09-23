package com.nyenjes.quotes.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.nyenjes.quotes.R
import com.nyenjes.quotes.adapters.QuoteCardAdapter
import com.nyenjes.quotes.model.Quote
import com.nyenjes.quotes.services.QuoteService
import com.nyenjes.quotes.services.ServiceBuilder
import retrofit2.Callback
import retrofit2.Response

class QuotesDisplayFragment : Fragment() {

    private val TAG: String = "QuotesDisplayFragment"

    var adapter: QuoteCardAdapter? = QuoteCardAdapter(this)
    val quotes : ArrayList<Quote> = ArrayList()
    var category : String? = null
    var quote : String? = null

    var recyclerView : RecyclerView? = null
    private var progressBar: ProgressBar? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_quote, container, false)

        val arguments = arguments
        category = arguments!!.getString("category")!!.toLowerCase()

        progressBar = view.findViewById(R.id.exploreProgressBar)
        showDialog()

        val quoteService = ServiceBuilder.buildService(QuoteService::class.java)

        val call = quoteService.getQuotes(category!!)
        call.enqueue(object: Callback<List<Quote>> {
            override fun onFailure(call: retrofit2.Call<List<Quote>>, t: Throwable) {
                Log.d(TAG, "quoteService.getQuotes() failed")
            }

            override fun onResponse(call: retrofit2.Call<List<Quote>>, response: Response<List<Quote>>) {
                Log.d(TAG,"---------------- response: ------- :${response.body()}")

                val jsonPlacesString = Gson().toJson(response.body())
                if(response.body() == null) {
                    Log.d(TAG, "quoteService.getQuotes() failed")
                    return
                }
                val jsonQuotes = Gson().fromJson(jsonPlacesString, Array<Quote>::class.java).toList()

                if (adapter!!.quotes!!.isEmpty()) {

                }

                adapter!!.quotes!!.clear()
                quotes.addAll(jsonQuotes)

                adapter!!.quotes = quotes
                adapter!!.notifyDataSetChanged()
                hideDialog()

                recyclerView = view.findViewById(R.id.quotes_recycler)
                recyclerView!!.layoutManager = LinearLayoutManager(context)
                recyclerView!!.adapter = adapter
            }
        })

        return view
    }

    fun shareApp() {
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(Intent.EXTRA_TEXT, quote)
        sendIntent.type = "text/plain"
        startActivity(sendIntent)
    }

    private fun showDialog() {
        progressBar!!.setVisibility(View.VISIBLE);

    }

    private fun hideDialog() {
        if (progressBar!!.getVisibility() == View.VISIBLE) {
            progressBar!!.setVisibility(View.INVISIBLE);
        }
    }
}
