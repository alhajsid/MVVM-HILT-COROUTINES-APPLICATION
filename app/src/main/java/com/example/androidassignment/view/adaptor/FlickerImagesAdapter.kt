package com.example.androidassignment.view.adaptor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.androidassignment.databinding.FlickerImageItemBinding
import com.example.androidassignment.databinding.RocketItemBinding
import com.example.androidassignment.model.RocketModel
import com.example.androidassignment.model.RocketsModel
import com.example.androidassignment.utils.loadImageFromGlide
import javax.inject.Inject

class FlickerImagesAdapter @Inject constructor() : RecyclerView.Adapter<FlickerImagesAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: FlickerImageItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffUtil = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {

            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, diffUtil)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            FlickerImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val image = differ.currentList[position]
        holder.binding.apply {
            ivFlickerImage.loadImageFromGlide(image)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}