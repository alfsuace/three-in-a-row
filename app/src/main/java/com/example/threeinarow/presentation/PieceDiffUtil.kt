package com.example.threeinarow.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.threeinarow.domain.Piece

class PieceDiffUtil : DiffUtil.ItemCallback<Piece>() {
    override fun areItemsTheSame(oldItem: Piece, newItem: Piece): Boolean {
        return oldItem.vertical == newItem.vertical && oldItem.horizontal == newItem.horizontal
    }

    override fun areContentsTheSame(oldItem: Piece, newItem: Piece): Boolean {
        return oldItem == newItem
    }
}