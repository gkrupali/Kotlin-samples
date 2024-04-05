package com.example.countrydetailslist.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.countrydetailslist.databinding.ActivityMainBinding
import com.example.countrydetailslist.model.CountryRepository
import com.example.countrydetailslist.model.UiState
import com.example.countrydetailslist.model.remote.RetrofitHelper
import com.example.countrydetailslist.vm.CountryViewModel
import com.example.countrydetailslist.vm.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val adapter = CountryListAdapter()
    private val viewModel: CountryViewModel by viewModels {
        ViewModelFactory(CountryRepository(RetrofitHelper.getRetrofitInstance()))
    }


	// TODO later 
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.setHasFixedSize(true)

        viewModel.countryListAsLiveData.observe(this, Observer { uiState ->
            if (uiState != null) {
                render(uiState)
            }
        })
        viewModel.errorStatus.observe(this,Observer{ errorStatus->
            if(errorStatus != null){
                render(errorStatus)
            }
        })
    }

    private fun render(uiState: UiState) {
        when (uiState) {
            is UiState.Loading -> {
                binding.progressBar.visibility = View.VISIBLE
                binding.recyclerView.visibility = View.INVISIBLE
            }
            is UiState.Success -> {
                binding.recyclerView.visibility = View.VISIBLE
                binding.progressBar.visibility = View.INVISIBLE
                adapter.submitList(uiState.countryList.toList())
            }
            is UiState.Error -> {
                Toast.makeText(applicationContext,""+uiState.message,Toast.LENGTH_LONG).show()
                binding.progressBar.visibility = View.INVISIBLE
            }
        }
    }
}