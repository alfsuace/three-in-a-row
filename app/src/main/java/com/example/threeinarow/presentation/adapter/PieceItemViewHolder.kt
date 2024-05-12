package com.example.threeinarow.presentation.adapter

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.threeinarow.databinding.ViewPieceItemBinding
import com.example.threeinarow.domain.Piece

class PieceItemViewHolder(val view: View): RecyclerView.ViewHolder(view) {
    lateinit var binding: ViewPieceItemBinding

    fun bind(piece: Piece) {
        binding = ViewPieceItemBinding.bind(view)
        binding.apply {
            root.visibility = if (piece.colour.isNotEmpty()) View.VISIBLE else View.INVISIBLE
            val color = when (piece.colour) {
                "white" -> Color.WHITE
                "black" -> Color.BLACK
                else -> Color.TRANSPARENT
            }

        }
    }

}