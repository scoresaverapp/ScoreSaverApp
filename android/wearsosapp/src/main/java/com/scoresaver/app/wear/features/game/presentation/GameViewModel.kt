package com.scoresaver.app.wear.features.game.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scoresaver.app.wear.features.game.model.Team
import com.scoresaver.app.wear.features.game.use_cases.GameInteractor
import com.scoresaver.core.data.db.schema.GAME_POINT
import com.scoresaver.core.data.db.schema.GAME_TYPE
import com.scoresaver.app.util.db.entity.GENDER
import com.scoresaver.app.util.db.entity.UserEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
internal class GameViewModel @Inject constructor(private val gameInteractor: GameInteractor) :
    ViewModel() {
    private val _formattedSeconds = mutableStateOf("00:00:00")
    val formattedSeconds by _formattedSeconds

    private val _isTimerRunning = mutableStateOf(false)
    val isTimerRunning by _isTimerRunning

    private val _hearthRate = mutableFloatStateOf(0f)
    val hearthRate by _hearthRate

    private val _calories = mutableStateOf("0")
    val calories by _calories

    private fun updateTimerValue(value: Int) {
        _formattedSeconds.value = gameInteractor.formatSeconds(value)
    }

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

    private val _isSingleMatch = mutableStateOf(false)
    val isSingleMatch by _isSingleMatch

    private val _userData = mutableStateOf<UserEntity?>(null)
    val userData = _userData

    private val _showSnackbar = mutableStateOf(false)
    val showSnackbar by _showSnackbar

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val gameSettings = gameInteractor.getGameSettings()

            gameSettings?.let {
                withContext(Dispatchers.Main) {
                    _isSingleMatch.value = gameSettings.gameType == GAME_TYPE.SINGLE
                    _isKillerPointActive.value = gameSettings.gamePoint == GAME_POINT.KILLER
                }

            }
        }

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
        gameInteractor.startTimer(
            scope = viewModelScope,
            onTimerChangeCallback = { value, isRunning ->
                updateTimerValue(value)
                updateIsTimerRunning(isRunning)
                getCalories(value / 60)
            })
    }

    fun stopTimer() {
        gameInteractor.stopTimer()
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

    override fun onCleared() {
        super.onCleared()
        gameInteractor.clearTimer()
    }

}