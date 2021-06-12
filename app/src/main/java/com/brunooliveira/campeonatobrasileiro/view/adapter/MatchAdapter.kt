package com.brunooliveira.campeonatobrasileiro.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.brunooliveira.campeonatobrasileiro.databinding.RowMatchBinding
import com.brunooliveira.campeonatobrasileiro.model.Match

class MatchAdapter(private var matches: List<Match>?):
    RecyclerView.Adapter<MatchAdapter.MatchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val binding = RowMatchBinding.inflate(LayoutInflater.from(parent.context), parent,
            false)
        return MatchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        with(holder) {
            with(matches!![position]) {
                binding.textHomeTeam.text = homeTeam.name
                binding.textAwayTeam.text = awayTeam.name
                binding.textHomeScore.text =
                    if (status == "FINISHED") score.fullTime.homeTeam.toString() else ""
                binding.textAwayScore.text =
                    if (status == "FINISHED") score.fullTime.awayTeam.toString() else ""
            }
        }
    }

    override fun getItemCount(): Int = matches!!.size

    fun setMatches(newMatches: List<Match>?) {
        matches = newMatches
        notifyDataSetChanged()
    }

    inner class MatchViewHolder(val binding: RowMatchBinding): RecyclerView.ViewHolder(binding.root)

}