package com.example.threeinarow.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.threeinarow.data.PieceDataRepository
import com.example.threeinarow.data.local.PieceXmlLocalDataSource
import com.example.threeinarow.databinding.FragmentBoardBinding
import com.example.threeinarow.domain.GetPiecesUseCase
import com.example.threeinarow.domain.SetPieceUseCase
import com.example.threeinarow.presentation.adapter.PieceItemAdapter

class PieceFragment : Fragment() {

    private var _binding: FragmentBoardBinding? = null
    private val binding get() = _binding!!
    private val resourceAdapter = PieceItemAdapter()


    private val viewModel: PieceViewModel by lazy {
        val dataRepo = PieceDataRepository(PieceXmlLocalDataSource(requireContext()))

        PieceViewModel(
            GetPiecesUseCase(
                dataRepo
            ),
            SetPieceUseCase(
                dataRepo
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBoardBinding.inflate(inflater, container, false)
        setupview()
        return binding.root
    }

    private fun setupview() {
        binding.apply {
            pieceList.apply {
                adapter = resourceAdapter
                layoutManager = GridLayoutManager(
                    requireContext(),
                    3,
                    LinearLayoutManager.VERTICAL,
                    false
                )

            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        viewModel.loadPieces()
    }

    private fun setupObservers() {
        val observer = Observer<PieceViewModel.UiState> { uiState ->
            resourceAdapter.submitList(uiState.pieces)
        }
        viewModel.uiState.observe(viewLifecycleOwner, observer)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}