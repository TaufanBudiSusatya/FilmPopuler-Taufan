package com.example.filmpopuler_taufan.ui.home.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.filmpopuler_taufan.data.remote.model.movie.Movie
import com.example.filmpopuler_taufan.databinding.MoviesListItemBinding

class MoviesAdapter(private val interaction: Interaction? = null) :
    PagingDataAdapter<Movie, MoviesAdapter.MoviesViewHolder>(TrendPagingDiffUtil()) {


    // Buat tampilan baru (dipanggil oleh layout manager)
    // membuat recycleview
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {

        return MoviesViewHolder(
            // Buat tampilan baru, yang mendefinisikan UI item list
            MoviesListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            interaction
        )
    }

    //mengganti konten tampilan (dipanggil oleh layout manager)
    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }


    class MoviesViewHolder
    constructor(
        private val binding: MoviesListItemBinding,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Movie) = with(itemView) {
            binding.movie = item
//            binding.configuration=ConfigurationStore.getConfigurationData()
            //
            itemView.setOnClickListener {
                val extras = FragmentNavigatorExtras(
                        binding.ivImage to "header_image")
                interaction?.onItemSelected(bindingAdapterPosition, item,extras)
            }


        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: Movie,extra:FragmentNavigator.Extras)
    }
}

class TrendPagingDiffUtil : DiffUtil.ItemCallback<Movie>() {

    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}