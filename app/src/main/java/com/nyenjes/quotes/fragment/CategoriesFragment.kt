package com.nyenjes.quotes.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nyenjes.quotes.R
import com.nyenjes.quotes.adapters.CategoriesCardAdapter
import com.nyenjes.quotes.model.Quote

class CategoriesFragment : Fragment() {

    private val TAG: String = "MainActivity"

    var adapter: CategoriesCardAdapter? = CategoriesCardAdapter()
    val quotes : ArrayList<Quote> = ArrayList()
    var recyclerView : RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_categories, container, false)

        recyclerView = view.findViewById(R.id.categories_recycler)
//        recyclerView!!.layoutManager = GridLayoutManager(this, 2)
        recyclerView!!.layoutManager = LinearLayoutManager(view.context)
        val quotes_1 = Quote(null, "Love")
        val quotes_2 = Quote(null, "Leadership")
        val quotes_3 = Quote(null, "Inspirational")
        val quotes_4 = Quote(null, "Motivation")
        quotes.add(quotes_1)
        quotes.add(quotes_2)
        quotes.add(quotes_3)
        quotes.add(quotes_4)
        adapter!!.quotes = quotes
        recyclerView!!.adapter = adapter

        return view
    }


}
