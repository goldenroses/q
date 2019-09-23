package com.nyenjes.quotes.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.nyenjes.quotes.R
import com.nyenjes.quotes.model.Quote
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import androidx.appcompat.app.AppCompatActivity
import com.nyenjes.quotes.fragment.QuotesDisplayFragment
import android.os.Bundle

class CategoriesCardAdapter: RecyclerView.Adapter<CategoriesHolder>() {
    var quotes: ArrayList<Quote> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.categories_holder, parent, false)
        return CategoriesHolder(view)    }

    override fun getItemCount(): Int {
        return quotes.size
    }

    override fun onBindViewHolder(holder: CategoriesHolder, position: Int) {
        holder.cardTitle.setTypeface(ResourcesCompat.getFont(holder.itemView.context, R.font.traveller))
        holder.cardDecription.setTypeface(ResourcesCompat.getFont(holder.itemView.context, R.font.cute))

        val picasso = Picasso.Builder(holder.img.context).build()
        var image = R.drawable.ic_arrow_up

        var currentItem = quotes[position]

        if (currentItem.content == "Love"){ image = R.drawable.heart }

        if (currentItem.content == "Leadership") { image = R.drawable.leader }

        if (currentItem.content == "Inspirational") { image = R.drawable.inspire }

        if (currentItem.content == "Motivational") { image = R.drawable.motivate }

        picasso.load(image).centerCrop().transform(RoundedCornersTransformation(5,5)).resize(90, 90).into(holder.img)

        holder.cardTitle.text = currentItem.content
        holder.updateCurrentItem(currentItem)    }

}

class CategoriesHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    val cardTitle = itemView.findViewById<TextView>(R.id.cardTitle)
    val cardDecription = itemView.findViewById<TextView>(R.id.cardDescription)
    val img = itemView.findViewById<ImageView>(R.id.cardImageView)

    init {
        cardTitle.setOnClickListener {
            val context = itemView.context

            Toast.makeText(context, "Selected : ${currentPlaceItem!!.content}", Toast.LENGTH_SHORT).show()

            val activity = itemView.context as AppCompatActivity
            val myFragment = QuotesDisplayFragment()
            val arguments = Bundle()
            arguments.putString("category", currentPlaceItem!!.content)
            myFragment.setArguments(arguments)

            val transaction = activity.supportFragmentManager.beginTransaction()
            transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)

            transaction.replace(R.id.fragment_container, myFragment).commit()

        }
    }

    var currentPlaceItem: Quote? = null

    fun updateCurrentItem(currentItem: Quote) {
        currentPlaceItem = currentItem
    }

}