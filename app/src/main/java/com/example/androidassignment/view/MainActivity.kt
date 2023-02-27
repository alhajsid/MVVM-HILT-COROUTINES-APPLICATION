package com.example.androidassignment.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.koltinpoc.utils.DataHandler
import com.example.androidassignment.databinding.ActivityMainBinding
import com.example.androidassignment.view.adaptor.RocketsAdapter
import com.example.androidassignment.viewModel.RocketViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var rocketsAdapter: RocketsAdapter

    val viewModel: RocketViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        init()

        viewModel.topHeadlines.observe(this) { dataHandler ->
            when (dataHandler) {
                is DataHandler.SUCCESS -> {
                    binding.swipeRefresh.isRefreshing = false
                    rocketsAdapter.differ.submitList(dataHandler.data)
                }
                is DataHandler.ERROR -> {
                    binding.swipeRefresh.isRefreshing = false
                }
                is DataHandler.LOADING -> {
                    binding.swipeRefresh.isRefreshing = true

                }
            }

        }
        viewModel.getRockets()
    }

    private fun init() {

        rocketsAdapter.onRocketClicked {
            val bundle = Bundle().apply {
                putParcelable("article_data", it)

            }
            val id = it.id
            val intent = Intent(this, RocketDetailsActivity::class.java)
            intent.putExtra("id", id)
            startActivity(intent)
        }

        binding.recyclerView.apply {
            adapter = rocketsAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getRockets()
        }

    }
}