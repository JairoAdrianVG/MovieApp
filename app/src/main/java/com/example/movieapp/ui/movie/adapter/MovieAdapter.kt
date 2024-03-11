package com.example.movieapp.ui.movie.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.core.BaseViewHolder
import com.example.movieapp.data.model.Movie
import com.example.movieapp.databinding.MovieItemBinding

class MovieAdapter(private val movieList:List<Movie>,
    private val itemClickListener: onMovieClickListener):RecyclerView.Adapter<BaseViewHolder<*>>() {

    //Un eventListener
    interface onMovieClickListener{
        fun onMovieClick(movie:Movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        //itemBinding -> Inflamos la vista
        val itemBinding = MovieItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        val holder = MoviesViewHolder(itemBinding,parent.context)

        //Click a cada elemento de la lista
        itemBinding.root.setOnClickListener{

            //Obtengo la posicion de la pelicula en la lista
            val position = holder.bindingAdapterPosition.takeIf{it != DiffUtil.DiffResult.NO_POSITION}
                ?: return@setOnClickListener
            itemClickListener.onMovieClick(movieList[position])
        }
        return holder
    }

    override fun getItemCount(): Int = movieList.size


    //Cada elemento lo dibujo en pantalla
    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {

        //Bindeo cada elemento de la lista a el BindViewHolder para que le ponga su imagen con el bind
        when(holder){
            is MoviesViewHolder -> holder.bind(movieList[position])
        }
    }

    //Le pongo la imagen a cada uno de los elementos
    private inner class MoviesViewHolder(val binding: MovieItemBinding, val context:Context):BaseViewHolder<Movie>(binding.root){
        override fun bind(item: Movie) {
            Glide.with(context).load("https://image.tmdb.org/t/p/w500/${item.poster_path}").centerCrop().into(binding.imgMovie)
        }
    }

}