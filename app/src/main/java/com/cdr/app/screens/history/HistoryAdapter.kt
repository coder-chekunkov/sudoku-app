package com.cdr.app.screens.history

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.cdr.app.model.database.StatisticGameInfoTuple
import com.cdr.sudoku.R
import com.cdr.sudoku.databinding.ItemHistoryGameBinding

class HistoryDiffUtil(
    private val oldList: List<StatisticGameInfoTuple>,
    private val newList: List<StatisticGameInfoTuple>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        return oldItem == newItem
    }
}

interface HistoryItemListener {
    fun getInfoAboutGame(id: Long)
    fun removeInfoAboutGame(id: Long)
}

class HistoryAdapter(private val historyItemListener: HistoryItemListener) :
    RecyclerView.Adapter<HistoryAdapter.HistoryItemViewHolder>(), View.OnClickListener {

    var data: List<StatisticGameInfoTuple> = emptyList()
        set(newValue) {
            val diffUtil = HistoryDiffUtil(field, newValue)
            val diffUtilResult = DiffUtil.calculateDiff(diffUtil)
            field = newValue
            diffUtilResult.dispatchUpdatesTo(this@HistoryAdapter)
        }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHistoryGameBinding.inflate(inflater, parent, false)
        binding.more.setOnClickListener(this)

        return HistoryItemViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: HistoryItemViewHolder, position: Int) {
        val static = data[position]

        with(holder.binding) {
            more.tag = static

            statusTextView.text = static.resultName
            difficultTextView.text = static.difficultyName
            mistakesTextView.text = "${static.mistakes}/3"

            image.setImageResource(if (static.resultName == "Победа") R.drawable.ic_win_history else R.drawable.ic_lost_history)
        }
    }

    override fun onClick(view: View) {
        showPopupMenu(view)
    }

    private fun showPopupMenu(view: View) {
        val popupMenu = PopupMenu(view.context, view)
        val statistic = view.tag as StatisticGameInfoTuple

        popupMenu.menu.add(0, ID_INFORMATION, Menu.NONE, "Information")
        popupMenu.menu.add(0, ID_REMOVE, Menu.NONE, "Remove")

        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                ID_INFORMATION -> historyItemListener.getInfoAboutGame(statistic.id)
                ID_REMOVE -> historyItemListener.removeInfoAboutGame(statistic.id)
            }
            return@setOnMenuItemClickListener true
        }

        popupMenu.show()
    }

    companion object {
        private const val ID_INFORMATION = 1
        private const val ID_REMOVE = 2
    }

    class HistoryItemViewHolder(val binding: ItemHistoryGameBinding) :
        RecyclerView.ViewHolder(binding.root)
}