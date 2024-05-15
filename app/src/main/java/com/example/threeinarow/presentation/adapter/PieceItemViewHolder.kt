package com.example.threeinarow.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.threeinarow.R
import com.example.threeinarow.databinding.ViewPieceItemBinding
import com.example.threeinarow.domain.Piece

class PieceItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    lateinit var binding: ViewPieceItemBinding

    fun bind(piece: Piece, onClick: (Piece) -> Unit) {
        binding = ViewPieceItemBinding.bind(view)
        binding.apply {
            if (piece.colour == "white") {
                viewPiece.setImageResource(R.drawable.ic_circle_shape_white)
            } else if (piece.colour == "black") {
                viewPiece.setImageResource(R.drawable.ic_circle_shape_black)
            } else {
                viewPiece.setImageResource(R.drawable.ic_square)
            }
            view.setOnClickListener {
                onClick(piece)
            }
        }
    }
}