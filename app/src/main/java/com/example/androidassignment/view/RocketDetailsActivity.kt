package com.example.androidassignment.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.koltinpoc.utils.DataHandler
import com.example.androidassignment.databinding.ActivityRocketDetailsBinding
import com.example.androidassignment.model.RocketModel
import com.example.androidassignment.view.adaptor.FlickerImagesAdapter
import com.example.androidassignment.view.adaptor.RocketsAdapter
import com.example.androidassignment.viewModel.RocketDetailsViewModel
import com.example.androidassignment.viewModel.RocketViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RocketDetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityRocketDetailsBinding


    @Inject
    lateinit var flickerAdapter: FlickerImagesAdapter

    lateinit var id: String

    val viewModel: RocketDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRocketDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        init()

        viewModel.topHeadlines.observe(this) { dataHandler ->
            when (dataHandler) {
                is DataHandler.SUCCESS -> {
                    binding.swipeRefresh.isRefreshing = false
                    dataHandler.data?.let {
                        setData(it)
                    }
                }
                is DataHandler.ERROR -> {
                    binding.swipeRefresh.isRefreshing = false
                }
                is DataHandler.LOADING -> {
                    binding.swipeRefresh.isRefreshing = true

                }
            }

        }
        viewModel.getRockets(id)
    }

    private fun setData(data: RocketModel) {
        flickerAdapter.differ.submitList(data.flickr_images)
        binding.tvActiveStatus.text = data.active.toString()
        binding.tvCostPerLaunch.text = data.cost_per_launch.toString()
        binding.tvSuccessRate.text = data.success_rate_pct.toString()
        binding.tvDescription.text = data.description
        binding.tvWikipedia.text = data.wikipedia
        binding.tvName.text = data.name

        val heightAndDia = "${data.height.feet} ft / ${data.diameter.feet} ft"
        binding.tvHeight.text = heightAndDia
    }

    fun init() {
        id = intent.getStringExtra("id") ?: ""

        binding.recyclerView.apply {
            adapter = flickerAdapter
            layoutManager = LinearLayoutManager(this@RocketDetailsActivity).apply {
                this.orientation = LinearLayoutManager.HORIZONTAL
            }
        }

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getRockets(id)
        }
    }

}