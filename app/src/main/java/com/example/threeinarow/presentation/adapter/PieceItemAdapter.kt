package com.example.threeinarow.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.threeinarow.R
import com.example.threeinarow.domain.Piece
import com.example.threeinarow.presentation.PieceDiffUtil

class PieceItemAdapter : ListAdapter<Piece, PieceItemViewHolder>(PieceDiffUtil()) {
    lateinit var onClick: (Piece) -> Unit

    fun setEvent(onClick: (Piece) -> Unit) {
        this.onClick = onClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PieceItemViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_piece_item, parent, false)
        return PieceItemViewHolder(view)
    }

    override fun getItemCount(): Int = currentList.size
    override fun onBindViewHolder(holder: PieceItemViewHolder, position: Int) {
        holder.bind(currentList[position], onClick)
    }
}