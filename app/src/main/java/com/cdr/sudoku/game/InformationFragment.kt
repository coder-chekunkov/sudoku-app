package com.cdr.sudoku.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cdr.sudoku.R
import com.cdr.sudoku.contract.HasCustomIcon
import com.cdr.sudoku.contract.HasCustomTitle
import com.cdr.sudoku.contract.IsGameButtonClickable
import com.cdr.sudoku.contract.navigator
import com.cdr.sudoku.databinding.FragmentInformationBinding

class InformationFragment : Fragment(), HasCustomTitle, HasCustomIcon, IsGameButtonClickable {

    private lateinit var binding: FragmentInformationBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInformationBinding.inflate(inflater, container, false)
        binding.okButton.setOnClickListener { clickOkButton() } // Возвращение к запуску игры

        return binding.root
    }

    private fun clickOkButton() = navigator().showLaunchGameFragment() // Запуск игры

    override fun getResTitle(): Int = R.string.titleToolbarInformation
    override fun isGameButtonClickable(): Boolean = false
    override fun getResIcon(): Int = R.drawable.ic_info
}