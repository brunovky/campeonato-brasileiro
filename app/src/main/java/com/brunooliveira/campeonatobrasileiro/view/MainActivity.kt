package com.brunooliveira.campeonatobrasileiro.view

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.brunooliveira.campeonatobrasileiro.R
import com.brunooliveira.campeonatobrasileiro.databinding.ActivityMainBinding
import com.brunooliveira.campeonatobrasileiro.view.adapter.MatchAdapter
import com.brunooliveira.campeonatobrasileiro.view.animator.LoadingAnimator
import com.brunooliveira.campeonatobrasileiro.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Calendar

class MainActivity: AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding
    private lateinit var autoCompleteTextView: AutoCompleteTextView
    private lateinit var adapter: MatchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        title = "${getString(R.string.app_name)} ${Calendar.getInstance().get(Calendar.YEAR)}"
        setContentView(binding.root)
        initComponents()
        initObservers()
        initListeners()
    }

    private fun initComponents() {
        autoCompleteTextView = binding.tilRounds.editText as AutoCompleteTextView
        adapter = MatchAdapter(listOf())
        binding.listMatches.layoutManager = LinearLayoutManager(this)
        binding.listMatches.adapter = adapter
    }

    private fun initObservers() {
        viewModel.rounds.observe(this, {
            autoCompleteTextView.setAdapter(ArrayAdapter(this,
                    android.R.layout.simple_list_item_1, it))
        })
        viewModel.matches.observe(this, {
            adapter.setMatches(it)
        })
        viewModel.loading.observe(this, {
            LoadingAnimator.animateLoading(it, binding.listMatches, binding.progressLoading)
        })
    }

    private fun initListeners() {
        autoCompleteTextView.setOnItemClickListener { _, _, position, _ ->
            viewModel.loadMatches(position)
        }
    }

}