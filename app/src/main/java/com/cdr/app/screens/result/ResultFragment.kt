package com.cdr.app.screens.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.fragment.navArgs
import com.cdr.app.model.statistic.Statistic
import com.cdr.app.model.statistic.Statistic.Companion.WIN_RESULT_STATISTIC
import com.cdr.app.screens.home.HomeViewModel.Companion.DIFFICULTY_EASY
import com.cdr.app.screens.home.HomeViewModel.Companion.DIFFICULTY_EXPERT
import com.cdr.app.screens.home.HomeViewModel.Companion.DIFFICULTY_HARD
import com.cdr.app.screens.home.HomeViewModel.Companion.DIFFICULTY_MIDDLE
import com.cdr.core.views.BaseFragment
import com.cdr.core.views.HasCustomTitle
import com.cdr.core.views.screenViewModel
import com.cdr.sudoku.R
import com.cdr.sudoku.databinding.FragmentResultBinding

class ResultFragment : BaseFragment(R.layout.fragment_result), HasCustomTitle {

    override val viewModel by screenViewModel<ResultViewModel>()
    private lateinit var binding: FragmentResultBinding
    private val args by navArgs<ResultFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_result, container, false).apply {
            findViewById<ComposeView>(R.id.composeView).setContent {
                CreateResultUi()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentResultBinding.bind(view)
        startEmojiRain()
    }

    @Composable
    private fun CreateResultUi() {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 50.dp, top = 100.dp, end = 50.dp, bottom = 100.dp)
            ) {
                CreateResultTitle(args.statisticArg.resultId)
                CreateResultData(args.statisticArg)
                CreateAcceptButton()
            }
        }
    }

    @Composable
    private fun CreateResultTitle(result: Long) {
        val textResult =
            if (result == WIN_RESULT_STATISTIC) getString(R.string.titleWinResult) else getString(R.string.titleLostResult)
        val imageRes =
            if (result == WIN_RESULT_STATISTIC) R.drawable.ic_win else R.drawable.ic_lost

        val textColor =
            if (isSystemInDarkTheme()) colorResource(id = R.color.white) else colorResource(id = R.color.black)
        Column(
            modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = textResult,
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold,
                color = textColor
            )
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                colorFilter = ColorFilter.tint(colorResource(id = R.color.darkGrey)),
                modifier = Modifier.padding(10.dp)
            )
        }
    }

    @Composable
    private fun CreateResultData(statistic: Statistic) {
        val textColor =
            if (isSystemInDarkTheme()) colorResource(id = R.color.white) else colorResource(id = R.color.black)
        val difficultText = when (statistic.difficultId) {
            DIFFICULTY_EASY.toLong() -> getString(R.string.difficultEasy)
            DIFFICULTY_MIDDLE.toLong() -> getString(R.string.difficultMiddle)
            DIFFICULTY_HARD.toLong() -> getString(R.string.difficultHard)
            DIFFICULTY_EXPERT.toLong() -> getString(R.string.difficultExpert)
            else -> getString(R.string.error)
        }

        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = getString(R.string.titleDifficult),
                    style = MaterialTheme.typography.subtitle2,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Start,
                    color = textColor
                )
                Text(
                    text = difficultText,
                    style = MaterialTheme.typography.subtitle2,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.End,
                    color = textColor
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp)
            ) {
                Text(
                    text = getString(R.string.titleMistakes),
                    style = MaterialTheme.typography.subtitle2,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Start,
                    color = textColor
                )
                Text(
                    text = statistic.mistakes.toString(),
                    style = MaterialTheme.typography.subtitle2,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.End,
                    color = textColor
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp)
            ) {
                Text(
                    text = getString(R.string.titlePoints),
                    style = MaterialTheme.typography.subtitle2,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Start,
                    color = textColor
                )
                Text(
                    text = statistic.points.toString(),
                    style = MaterialTheme.typography.subtitle2,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.End,
                    color = textColor
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp)
            ) {
                Text(
                    text = getString(R.string.titleTime),
                    style = MaterialTheme.typography.subtitle2,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Start,
                    color = textColor
                )
                Text(
                    text = statistic.elapsedTime,
                    style = MaterialTheme.typography.subtitle2,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.End,
                    color = textColor
                )
            }
        }
    }

    @Composable
    private fun CreateAcceptButton() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 25.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            Button(
                onClick = { viewModel.launchHomeScreen(this@ResultFragment) },
                modifier = Modifier.width(200.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.blue)),
                shape = CircleShape
            ) {
                Text(text = getString(R.string.buttonOk), color = colorResource(id = R.color.white))
            }
        }
    }

    private fun startEmojiRain() {
        with(binding.container) {
            if (args.statisticArg.resultId == WIN_RESULT_STATISTIC) {
                addEmoji(R.drawable.emoji_win_1)
                addEmoji(R.drawable.emoji_win_2)
                addEmoji(R.drawable.emoji_win_3)
            } else {
                addEmoji(R.drawable.emoji_lost_1)
                addEmoji(R.drawable.emoji_lost_2)
                addEmoji(R.drawable.emoji_lost_3)
            }

            setPer(5)
            setDuration(6000)
            setDropDuration(3400)
            setDropFrequency(500)

            startDropping()
        }
    }

    override fun getScreenTitle(): String = getString(R.string.titleToolbarGameOver)
}
