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
import com.nyenjes.quotes.fragment.QuotesDisplayFragment

class QuoteCardAdapter(val fragment: QuotesDisplayFragment): RecyclerView.Adapter<QuoteHolder>() {
    var quotes: ArrayList<Quote>? = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.quote_holder, parent, false)
        return QuoteHolder(view)    }

    override fun getItemCount(): Int {
        return quotes!!.size
    }

    override fun onBindViewHolder(holder: QuoteHolder, position: Int) {
        holder.cardTitle.setTypeface(ResourcesCompat.getFont(holder.itemView.context, R.font.traveller))
        var images = ArrayList<Int>()


        Toast.makeText(holder.itemView.context, "Here : ${fragment.category}", Toast.LENGTH_SHORT).show()

        if ((fragment).category == "love") {
            images.add(R.drawable.heart)
            images.add(R.drawable.love_one)
            images.add(R.drawable.love_two)
            images.add(R.drawable.love_three)
            images.add(R.drawable.love_four)
            images.add(R.drawable.love_five)
            images.add(R.drawable.love_six)
            images.add(R.drawable.love_sev)
            images.add(R.drawable.love_ieght)
        }

        else if ((fragment).category == "leadership") {
            images.add(R.drawable.lead_one)
            images.add(R.drawable.lead_two)
            images.add(R.drawable.lead_three)
            images.add(R.drawable.lead_four)
            images.add(R.drawable.lead_five)
            images.add(R.drawable.lead_six)
            images.add(R.drawable.lead_sev)
            images.add(R.drawable.lead_eight)
            images.add(R.drawable.lead_nine)
            images.add(R.drawable.lead_ten)
        }

        else if ((fragment).category == "inspirational") {
            images.add(R.drawable.mot_one)
            images.add(R.drawable.mot_two)
            images.add(R.drawable.mot_three)
            images.add(R.drawable.mot_four)
            images.add(R.drawable.mot_five)
            images.add(R.drawable.mot_six)
            images.add(R.drawable.mot_sev)
            images.add(R.drawable.mot_eight)
            images.add(R.drawable.mot_nine)
            images.add(R.drawable.mot_ten)
        }

        var currentItem = quotes!![position]
        holder.cardTitle.text = currentItem.content
        val picasso = Picasso.Builder(holder.imgFavorite.context).build()
        picasso.load(images.random()).centerCrop().transform(RoundedCornersTransformation(5,5)).resize(90, 90).into(holder.imgFavorite)

        fragment.quote = currentItem.content

        holder.updateCurrentItem(currentItem)    }
}

class QuoteHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    val cardTitle = itemView.findViewById<TextView>(R.id.cardTitle)
    val imgFavorite = itemView.findViewById<ImageView>(R.id.cardImageView)

    init {
        cardTitle.setOnClickListener {
//            val intent = Intent(itemView.context, PlaceDetailActivity::class.java)
//            intent.putExtra("currentPlaceItem", Gson().toJson(currentPlaceItem))
//            itemView.context.startActivity(intent)
        }
    }

    var currentPlaceItem: Quote? = null

    fun updateCurrentItem(currentItem: Quote) {
        currentPlaceItem = currentItem
    }

}
