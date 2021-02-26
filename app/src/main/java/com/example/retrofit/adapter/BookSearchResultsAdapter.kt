package com.example.retrofit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofit.R
import com.example.retrofit.model.Volume


class BookSearchResultsAdapter: RecyclerView.Adapter<BookSearchResultsAdapter.BookSearchResultHolder>() {

    private val results: List<Volume> = ArrayList()

    class BookSearchResultHolder(view: View): RecyclerView.ViewHolder(view){
        var titleTextView: TextView? = null
        var authorsTextView: TextView? = null
        var publishedDateTextView: TextView? = null
        var smallThumbnailImageView: ImageView? = null

        init{
            titleTextView = itemView.findViewById(R.id.book_item_title)
            authorsTextView = itemView.findViewById(R.id.book_item_authors)
            publishedDateTextView = itemView.findViewById(R.id.book_item_publishedDate)
            smallThumbnailImageView = itemView.findViewById(R.id.book_item_smallThumbnail)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookSearchResultHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.book_item, parent, false)
        return BookSearchResultHolder(view)
    }

    override fun onBindViewHolder(holder: BookSearchResultHolder, position: Int) {
        val volume = results.get(position)

        holder.titleTextView!!.text = volume.getVolumeInfo()!!.getTitle()
        holder.publishedDateTextView!!.text = volume.getVolumeInfo()!!.getPublishedDate()

        if (volume.getVolumeInfo()!!.getImageLinks() != null){
            val imageurl: String? = volume.getVolumeInfo()!!
                .getImageLinks()?.getSmallThumbnail()?.replace("http://", "https://")

            Glide.with(holder.itemView).load(imageurl).into(holder.smallThumbnailImageView!!)
        }

            if (volume.getVolumeInfo()!!.getAuthors() != null) {
                val authors: List<String>? = volume.getVolumeInfo()!!.getAuthors()
                holder.authorsTextView!!.text = authors.toString()
            }
    }

    override fun getItemCount(): Int {
       return results.size
    }

    fun setResults(results: List<Volume?>?) {
        notifyDataSetChanged()
    }
}