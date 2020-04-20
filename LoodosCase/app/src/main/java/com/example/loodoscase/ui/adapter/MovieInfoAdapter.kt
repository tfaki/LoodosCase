package com.example.loodoscase.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.loodoscase.R
import com.example.loodoscase.helper.load
import com.example.loodoscase.model.MovieInfoResponse
import com.example.loodoscase.ui.interfaces.ItemClickListener

class MovieInfoAdapter(private val movieInfoList: ArrayList<MovieInfoResponse> = arrayListOf(), private val itemClickListener: ItemClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.rv_movie_info_item, parent, false)
        return HolderModel(view)
    }

    override fun getItemCount(): Int {
        return movieInfoList.size
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val holder: HolderModel = viewHolder as HolderModel

        holder.ivPhoto.load(movieInfoList[position].Poster)
        holder.tvImdbRating.text = "Imdb Rating : ${movieInfoList[position].imdbRating}"
        holder.tvYear.text = "Year : ${movieInfoList[position].Year}"
        holder.tvRunTime.text = "Runtime : ${movieInfoList[position].Runtime}"

        holder.itemView.setOnClickListener { itemClickListener.onItemClick(it, position) }
    }

    class HolderModel(view: View): RecyclerView.ViewHolder(view){
        val tvImdbRating: TextView = view.findViewById(R.id.tvImdbRating)
        val tvYear: TextView = view.findViewById(R.id.tvYear)
        val tvRunTime: TextView = view.findViewById(R.id.tvRunTime)
        val ivPhoto: ImageView = view.findViewById(R.id.ivPhoto)
    }

}