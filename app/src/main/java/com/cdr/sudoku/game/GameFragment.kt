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
import com.cdr.sudoku.databinding.FragmentGameBinding
import kotlin.properties.Delegates

class GameFragment : Fragment(), HasCustomTitle, HasCustomIcon, IsGameButtonClickable {

    private lateinit var binding: FragmentGameBinding
    private var difficult by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        difficult = arguments?.getInt(KEY_ARG_DIFFICULT) ?: LaunchGameFragment.DIFFICULTY_EASY
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameBinding.inflate(inflater, container, false)

        binding.textView.text = difficult.toString()

        return binding.root
    }

    override fun getResTitle(): Int = R.string.titleToolbarGame
    override fun getResIcon(): Int = R.drawable.ic_game
    override fun isGameButtonClickable(): Boolean = false

    companion object {
        @JvmStatic
        private val KEY_ARG_DIFFICULT = "ARG_DIFFICULT"

        @JvmStatic
        fun newInstance(newDifficult: Int): Fragment {
            val bundle = Bundle()
            bundle.putInt(KEY_ARG_DIFFICULT, newDifficult)

            val fragment = GameFragment()
            fragment.arguments = bundle
            return fragment
        }
    }


}