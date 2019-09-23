package com.nyenjes.quotes.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nyenjes.quotes.QuotesApplication
import com.nyenjes.quotes.R
import com.nyenjes.quotes.adapters.QuoteCardAdapter
import com.nyenjes.quotes.managers.DataManager
import com.nyenjes.quotes.model.Quote

class FavoritesFragment : Fragment() {

    private var dataManager: DataManager? = null
    private var recycler: RecyclerView? = null
    private var cardAdapter: QuoteCardAdapter? = null
    private var quote: Quote? = null
    private var imageView: ImageView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favorites, container, false)
        dataManager = (activity!!.applicationContext as QuotesApplication).dataManager


        recycler = view.findViewById(R.id.fave_recycler)

        recycler!!.layoutManager = LinearLayoutManager(view.context)

        cardAdapter!!.quotes = dataManager!!.getAllFavorites()

        imageView = view.findViewById(R.id.cardImageView)

        recycler!!.adapter = cardAdapter
        return view
    }

    fun loadFave() {
        try {
            if(dataManager!!.getIsFavorite(quote!!.id!!)) {
                imageView!!.setImageResource(R.drawable.ic_fave)
            }
            else {
                imageView!!.setImageResource(R.drawable.ic_fave_border)
            }
        } catch (e: Exception) {
        }
    }

    fun favoritePlace(view: View) {
        Log.d("Here", "Clicked" +quote)

        try {
            if(dataManager!!.getIsFavorite(quote!!.id!!)) {
                dataManager!!.removeFavorite(quote!!)
                imageView!!.setImageResource(R.drawable.ic_fave_border)
            }
            else {
                dataManager!!.addFavorite(quote!!)
                imageView!!.setImageResource(R.drawable.ic_fave)

            }
        } catch (e: Exception) {
        }

    }
}
