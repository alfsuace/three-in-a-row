package com.example.threeinarow.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.threeinarow.data.BoardDataRepository
import com.example.threeinarow.data.local.BoardXmlLocalDataSource
import com.example.threeinarow.databinding.FragmentBoardBinding
import com.example.threeinarow.domain.ChangeTurnUseCase
import com.example.threeinarow.domain.GetPiecesUseCase
import com.example.threeinarow.domain.GetTurnUseCase
import com.example.threeinarow.domain.GetWinnerUseCase
import com.example.threeinarow.domain.SetPieceUseCase
import com.example.threeinarow.domain.WipeBoardUseCase
import com.example.threeinarow.presentation.adapter.PieceItemAdapter

class PieceFragment : Fragment() {

    private var _binding: FragmentBoardBinding? = null
    private val binding get() = _binding!!
    private val pieceAdapter = PieceItemAdapter()


    private val viewModel: PieceViewModel by lazy {
        val dataRepo = BoardDataRepository(BoardXmlLocalDataSource(requireContext()))

        PieceViewModel(
            GetPiecesUseCase(
                dataRepo
            ),
            SetPieceUseCase(
                dataRepo
            ),
            GetTurnUseCase(
                dataRepo
            ),
            ChangeTurnUseCase(
                dataRepo
            ),
            WipeBoardUseCase(
                dataRepo
            ),
            GetWinnerUseCase(
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
                layoutManager = GridLayoutManager(
                    requireContext(),
                    3,
                    GridLayoutManager.VERTICAL,
                    false
                )
                pieceAdapter.setEvent {
                    if (it.colour == "empty") {
                        viewModel.putPiece(it)
                    }
                }
                adapter = pieceAdapter
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        viewModel.wipeBoard()
        viewModel.loadPieces()
        viewModel.loadTurn()

    }

    private fun setupObservers() {
        val observer = Observer<PieceViewModel.UiState> { uiState ->
            pieceAdapter.submitList(uiState.pieces)
            if (uiState.winner == "" || uiState.winner == "empty") {
                binding.winnerText.visibility = View.GONE
            } else {
                binding.winnerText.visibility = View.VISIBLE
                binding.winnerText.text = uiState.winner
            }
        }
        viewModel.uiState.observe(viewLifecycleOwner, observer)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}