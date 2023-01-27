package com.cdr.app.screens.history

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.cdr.app.model.database.StatisticGameInfoTuple
import com.cdr.core.views.*
import com.cdr.sudoku.R
import com.cdr.sudoku.databinding.FragmentHistoryBinding

class HistoryFragment : BaseFragment(R.layout.fragment_history), HasCustomTitle, HasCustomAction {

    override val viewModel: HistoryViewModel by screenViewModel()
    private lateinit var binding: FragmentHistoryBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHistoryBinding.bind(view)

        viewModel.statistic.observe(viewLifecycleOwner) { renderUi(it) }
        binding.updateButton.setOnClickListener { viewModel.getAllStatisticInfo() }
    }

    private fun renderUi(data: List<StatisticGameInfoTuple>) {
        if (data.isEmpty()) {
            binding.recyclerView.visibility = View.GONE
            binding.emptyListContainer.visibility = View.VISIBLE
        } else {
            binding.recyclerView.visibility = View.VISIBLE
            binding.emptyListContainer.visibility = View.GONE

            val adapter = HistoryAdapter(viewModel.historyItemListener)
            adapter.data = data
            binding.recyclerView.adapter = adapter
            binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun getScreenTitle(): String = getString(R.string.titleToolbarHistory)
    override fun getCustomAction(): CustomAction = CustomAction(
        iconRes = R.drawable.ic_remove,
        textAction = getString(R.string.textActionRemove),
        onCustomAction = { viewModel.removeAllStatisticGameInfo() }
    )
}