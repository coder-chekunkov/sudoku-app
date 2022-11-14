package com.cdr.sudoku.statistic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cdr.sudoku.R
import com.cdr.sudoku.contract.HasCustomTitle
import com.cdr.sudoku.databinding.FragmentStatisticBinding

class StatisticFragment : Fragment(), HasCustomTitle {

    private lateinit var binding: FragmentStatisticBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStatisticBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun getResTitle(): Int = R.string.titleStatisticButton
}