package com.cdr.app.screens.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cdr.app.model.database.StatisticGameInfoTuple
import com.cdr.app.model.statistic.StatisticRepository
import com.cdr.app.utils.DbUnknownException
import com.cdr.app.utils.EmptyStatisticListException
import com.cdr.app.utils.StatisticGameInfoNotExist
import com.cdr.core.uiactions.UiActions
import com.cdr.core.views.BaseViewModel
import com.cdr.sudoku.R
import kotlinx.coroutines.launch

class HistoryViewModel(
    private val uiActions: UiActions,
    private val statisticRepository: StatisticRepository
) : BaseViewModel() {

    private val _statistic = MutableLiveData<List<StatisticGameInfoTuple>>()
    val statistic: LiveData<List<StatisticGameInfoTuple>> = _statistic

    init {
        getAllStatisticInfo()
    }

    val historyItemListener = object : HistoryItemListener {
        override fun getInfoAboutGame(id: Long) {
            val statisticId = _statistic.value?.indexOfFirst { it.id == id }
            if (statisticId == -1) return

            val statisticInfo = _statistic.value?.get(statisticId!!)
            uiActions.showToast(statisticInfo.toString())
        }

        override fun removeInfoAboutGame(id: Long) {
            viewModelScope.launch {
                try {
                    statisticRepository.removeGameStatisticInfoById(id)
                    getAllStatisticInfo()
                } catch (e: StatisticGameInfoNotExist) {
                    uiActions.showToast(uiActions.getString(R.string.errorMessage))
                }
            }
        }
    }

    fun removeAllStatisticGameInfo() {
        viewModelScope.launch {
            try {
                statisticRepository.removeAllGamesStatisticInfo()
                _statistic.value = emptyList()
            } catch (e: DbUnknownException) {
                uiActions.showToast(uiActions.getString(R.string.errorMessage))
            }
        }
    }

    fun getAllStatisticInfo() {
        viewModelScope.launch {
            try {
                _statistic.value = statisticRepository.getAllGamesStatisticInfo().reversed()
            } catch (e: EmptyStatisticListException) {
                _statistic.value = emptyList()
            } catch (e: DbUnknownException) {
                uiActions.showToast(uiActions.getString(R.string.errorMessage))
            }
        }
    }
}