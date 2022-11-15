package com.cdr.sudoku.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cdr.sudoku.R
import com.cdr.sudoku.contract.HasCustomIcon
import com.cdr.sudoku.contract.HasCustomTitle
import com.cdr.sudoku.databinding.FragmentHistoryBinding

class HistoryFragment : Fragment(), HasCustomTitle, HasCustomIcon {

    private lateinit var binding: FragmentHistoryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun getResTitle(): Int = R.string.titleToolbarHistory
    override fun getResIcon(): Int = R.drawable.ic_history
}