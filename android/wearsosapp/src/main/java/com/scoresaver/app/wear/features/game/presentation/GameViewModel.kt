package com.scoresaver.app.wear.features.game.presentation

import android.app.ActivityManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.ServiceConnection
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scoresaver.app.util.db.entity.GAME_POINT
import com.scoresaver.app.util.db.entity.GENDER
import com.scoresaver.app.util.db.entity.ResultData
import com.scoresaver.app.util.db.entity.UserEntity
import com.scoresaver.app.wear.features.game.model.Team
import com.scoresaver.app.wear.features.game.timer.TimerBroadcastReceiver
import com.scoresaver.app.wear.features.game.timer.TimerService
import com.scoresaver.app.wear.features.game.use_cases.GameInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.io.path.name

@HiltViewModel
internal class GameViewModel @Inject constructor(
    private val gameInteractor: GameInteractor,
    @ApplicationContext private val context: Context,
) : ViewModel() {

    private val timerBroadcastReceiver = TimerBroadcastReceiver()

    private val _isTimerRunning = mutableStateOf(false)
    val isTimerRunning by _isTimerRunning

    private val _formattedSeconds = mutableStateOf("00:00:00")
    val formattedSeconds by _formattedSeconds

    private val _hearthRate = mutableFloatStateOf(0f)
    val hearthRate by _hearthRate

    private val _calories = mutableStateOf("0")
    val calories by _calories

    private val _scoreTeam1 = mutableStateOf("0")
    val scoreTeam1 by _scoreTeam1

    private val _scoreTeam2 = mutableStateOf("0")
    val scoreTeam2 by _scoreTeam2

    private val _isKillerPointActive = mutableStateOf(false)
    val isKillerPointActive by _isKillerPointActive

    private val _gameTeam1 = mutableStateOf("0")
    val gameTeam1 by _gameTeam1

    private val _gameTeam2 = mutableStateOf("0")
    val gameTeam2 by _gameTeam2

    private val _setTeam1 = mutableIntStateOf(0)
    val setTeam1 by _setTeam1

    private val _setTeam2 = mutableIntStateOf(0)
    val setTeam2 by _setTeam2

    private val _userData = mutableStateOf<UserEntity?>(null)
    val userData = _userData

    private val _showSnackbar = mutableStateOf(false)
    val showSnackbar by _showSnackbar

    private val _actionCloseGame = mutableStateOf(false)
    val actionCloseGame by _actionCloseGame

    private val _historyMatches = MutableStateFlow<List<ResultData>>(listOf())
    val historyMatches: MutableStateFlow<List<ResultData>>
        get() = _historyMatches


    fun getDataUsers() {
        viewModelScope.launch(Dispatchers.IO)
        {
            val userData = gameInteractor.getUserData()

            userData?.let {
                withContext(Dispatchers.Main) {
                    _userData.value = userData
                }
            }
        }
    }

    fun getSettingsData() {
        viewModelScope.launch(Dispatchers.IO) {
            val gameSettings = gameInteractor.getGameSettings()

            gameSettings?.let {
                withContext(Dispatchers.Main) {
                    _isKillerPointActive.value = gameSettings.gamePoint == GAME_POINT.KILLER
                }
            }
        }
    }

    private fun updateIsTimerRunning(isRunning: Boolean) {
        if (isRunning != _isTimerRunning.value) {
            _isTimerRunning.value = isRunning
        }
    }

    private fun updateHeartRateValue(value: Float) {
        if (value != _hearthRate.floatValue) {
            _hearthRate.floatValue = value
        }
    }

    fun startTimer() {
        _isTimerRunning.value = true
        val intent = Intent(context, TimerService::class.java).apply {
            action = "START_TIMER"
        }
        context.startService(intent)
    }

    fun stopTimer() {
        _isTimerRunning.value = false
        val intent = Intent(context, TimerService::class.java).apply {
            action = "STOP_TIMER"
        }
        context.startService(intent)
    }

    fun checkCounter() {
        timerBroadcastReceiver.onTimerTick = { seconds ->
            viewModelScope.launch {
                _formattedSeconds.value = formatSeconds(seconds)
                getCalories(seconds / 60)
            }
        }
        val intentFilter = IntentFilter("TIMER_TICK")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.registerReceiver(
                timerBroadcastReceiver,
                intentFilter,
                Context.RECEIVER_NOT_EXPORTED
            )
        }
    }

    fun startHeartRateListener() {
        gameInteractor.startHeartRateListener {
            updateHeartRateValue(it)
        }
    }

    fun stopHeartRateListener() {
        gameInteractor.stopHeartRateListener()
    }

    private fun getCalories(seconds: Int) {
        userData.value?.gender
        _calories.value =
            gameInteractor.getCalories(
                age = userData?.value?.age ?: 18,
                userData.value?.gender ?: GENDER.MALE,
                userData.value?.weight ?: 180,
                userData?.value?.height ?: 60,
                _hearthRate.floatValue, seconds
            )
    }

    fun addPointsTeam1() {
        gameInteractor.addPoint(team = Team.TEAM_1, isKillerPointActive = isKillerPointActive)
        _scoreTeam1.value = gameInteractor.getPointScoreTeam1()
        _scoreTeam2.value = gameInteractor.getPointScoreTeam2()
        _gameTeam1.value = gameInteractor.getGameScoreTeam1()
        _gameTeam2.value = gameInteractor.getGameScoreTeam2()
        _setTeam1.intValue = gameInteractor.getSetScoreTeam1()
        _setTeam2.intValue = gameInteractor.getSetScoreTeam2()
    }

    fun addPointsTeam2() {
        gameInteractor.addPoint(team = Team.TEAM_2, isKillerPointActive = isKillerPointActive)
        _scoreTeam1.value = gameInteractor.getPointScoreTeam1()
        _scoreTeam2.value = gameInteractor.getPointScoreTeam2()
        _gameTeam1.value = gameInteractor.getGameScoreTeam1()
        _gameTeam2.value = gameInteractor.getGameScoreTeam2()
        _setTeam1.intValue = gameInteractor.getSetScoreTeam1()
        _setTeam2.intValue = gameInteractor.getSetScoreTeam2()
    }

    fun removePoint() {
        gameInteractor.removeLastPoint()
        _scoreTeam1.value = gameInteractor.getPointScoreTeam1()
        _scoreTeam2.value = gameInteractor.getPointScoreTeam2()
    }

    fun updateKillerPoint() {
        _isKillerPointActive.value = !isKillerPointActive
        _showSnackbar.value = true
    }

    fun hideSnackBar() {
        _showSnackbar.value = false
    }

    fun onClickCloseGame() {
        _actionCloseGame.value = true
    }

    fun saveResult() {
        gameInteractor.saveResult()
    }

    fun setOnClickCloseGame(value: Boolean) {
        _actionCloseGame.value = value
    }

    fun loadHistoryMatches() {
        viewModelScope.launch {
            gameInteractor.loadHistoryMatches().collect {
                _historyMatches.value = it
            }
        }
    }

    fun resetData() {
        _scoreTeam1.value = "0"
        _scoreTeam2.value = "0"
        _gameTeam1.value = "0"
        _gameTeam2.value = "0"
        _setTeam1.intValue = 0
        _setTeam2.intValue = 0
        _actionCloseGame.value = false
        _isTimerRunning.value = false
        stopHeartRateListener()
        stopTimer()
        stopTimer()
        _hearthRate.floatValue = 0f
        viewModelScope.launch {
            gameInteractor.deleteSettingsData()
        }
    }

    private fun formatSeconds(seconds: Int): String {
        val hours = seconds / 3600
        val minutes = (seconds % 3600) / 60
        val remainingSeconds = seconds % 60
        return String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds)
    }

    override fun onCleared() {
        super.onCleared()
        stopTimer()
    }

}