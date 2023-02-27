package com.example.androidassignment.view.adaptor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.androidassignment.databinding.RocketItemBinding
import com.example.androidassignment.model.RocketsModel
import com.example.androidassignment.utils.loadImageFromGlide
import javax.inject.Inject

class RocketsAdapter @Inject constructor() : RecyclerView.Adapter<RocketsAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: RocketItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffUtil = object : DiffUtil.ItemCallback<RocketsModel.RocketsModelItem>() {
        override fun areItemsTheSame(oldItem: RocketsModel.RocketsModelItem, newItem: RocketsModel.RocketsModelItem): Boolean {

            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: RocketsModel.RocketsModelItem, newItem: RocketsModel.RocketsModelItem): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, diffUtil)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            RocketItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val rocket = differ.currentList[position]
        holder.binding.apply {
            ivFlickerImage.loadImageFromGlide(rocket.flickr_images?.first()?:"")
            tvName.text = rocket.name
            tvCountry.text = rocket.country
            tvEngineCount.text = rocket.engines.number.toString()

        }

        holder.itemView.setOnClickListener {
            setRocketClickListener?.let {
                it(rocket)
            }
        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var setRocketClickListener : ((rocket: RocketsModel.RocketsModelItem)->Unit)? =null

    fun onRocketClicked(listener:(RocketsModel.RocketsModelItem)->Unit){
        setRocketClickListener = listener
    }
}