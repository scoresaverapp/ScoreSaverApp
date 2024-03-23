package com.scoresaver.app.wear.features.game.use_cases

import android.text.format.DateUtils
import androidx.compose.runtime.mutableIntStateOf
import com.scoresaver.app.util.db.entity.GAME_TYPE
import com.scoresaver.app.util.db.entity.GENDER
import com.scoresaver.app.util.db.entity.GameSettingsEntity
import com.scoresaver.app.util.db.entity.ResultData
import com.scoresaver.app.util.db.entity.UserEntity
import com.scoresaver.app.util.util.getCurrentDate
import com.scoresaver.app.wear.features.game.health.Health
import com.scoresaver.app.wear.features.game.model.GameScore
import com.scoresaver.app.wear.features.game.model.GameType
import com.scoresaver.app.wear.features.game.model.PointScore
import com.scoresaver.app.wear.features.game.model.PointType
import com.scoresaver.app.wear.features.game.model.SetType
import com.scoresaver.app.wear.features.game.model.Team
import com.scoresaver.app.wear.features.game.model.mapIntToGameScore
import com.scoresaver.app.wear.features.game.model.mapIntToPointScore
import com.scoresaver.app.wear.features.game.repository.GameRepository
import com.scoresaver.app.wear.features.game.timer.TimerHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class GameInteractorImpl @Inject constructor(
    private val timerHandler: TimerHandler,
    private val health: Health,
    private val gameRepository: GameRepository,
) : GameInteractor {

    private val listPoints = mutableListOf<PointType>()
    private val listGame = mutableListOf<GameType>()
    private val previousListGameA = mutableListOf<Int>()
    private val previousListGameB = mutableListOf<Int>()
    private val listSet = mutableListOf<SetType>()
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    private var isSingleGame: Boolean = false

    init {
        coroutineScope.launch {
            isSingleGame = gameRepository.getGameSettings()?.gameType == GAME_TYPE.SINGLE
        }

    }
    override fun startTimer(
        scope: CoroutineScope,
        onTimerChangeCallback: (Int, Boolean) -> Unit
    ) {
        timerHandler.setScope(scope)
        timerHandler.addTimerListener(onTimerChangeCallback)
        timerHandler.starTimer()
    }

    override fun stopTimer() =
        timerHandler.stopTimer()


    override fun clearTimer() =
        timerHandler.clearTimer()


    override fun startHeartRateListener(onHeartRateChangeCallback: (Float) -> Unit) =
        health.startHeartRateListener(onHeartRateChangeCallback)


    override fun stopHeartRateListener() = health.stopHeartRateListener()

    override fun getCalories(
        age: Int,
        gender: GENDER,
        weight: Int,
        height: Int,
        heartRate: Float,
        seconds: Int
    ): String {
        val minutes = seconds * 60
        return health.getCalories(
            age = age,
            gender = gender, weight = weight, height = height, heartRate = heartRate,
            minutes = minutes
        )
    }

    override fun formatSeconds(value: Int) =
        timerHandler.formatSeconds(value)

    override fun addPoint(team: Team, isKillerPointActive: Boolean) {
        val otherTeam = if (team == Team.TEAM_1) Team.TEAM_2 else Team.TEAM_1

        val scoreSelectedTeam = calculatePointScore(team)
        val scoreOtherTeam = calculatePointScore(otherTeam)

        when {
            isTiebreak() && scoreSelectedTeam.score.toInt() > 4 && scoreSelectedTeam.score.toInt() == scoreOtherTeam.score.toInt() -> {
                listPoints.add(PointType.TieBreakAdvantages(team))
            }

            isTieBreakAdvantages(team) -> {
                listPoints.clear()
                addGame(team)
            }

            isTiebreak() && scoreSelectedTeam.score.toInt() == 6 && !isTieBreakAdvantages(otherTeam) -> {
                listPoints.clear()
                addGame(team)
            }

            isTiebreak() -> {
                listPoints.add(PointType.TieBreakPoint(team))
            }

            scoreSelectedTeam == PointScore.Zero() || scoreSelectedTeam == PointScore.Fifteen() -> listPoints.add(
                PointType.Point15(team)
            )

            scoreSelectedTeam == PointScore.Thirty() -> {
                listPoints.add(PointType.Point10(team))
            }

            scoreSelectedTeam == PointScore.Forty() && scoreOtherTeam == PointScore.Adv() -> {
                listPoints.add(PointType.Deuce(team))
            }

            scoreSelectedTeam == PointScore.Adv() -> {
                listPoints.clear()
                addGame(team)
            }

            scoreSelectedTeam == PointScore.Forty() && scoreOtherTeam == PointScore.Forty() -> {
                if (isKillerPointActive) {
                    listPoints.clear()
                    addGame(team)
                } else {
                    listPoints.add(PointType.Advantages(team))
                }
            }

            scoreSelectedTeam == PointScore.Forty() -> {
                listPoints.clear()
                addGame(team)
            }
        }
    }

    override fun removeLastPoint() {
        if (listPoints.isNotEmpty()) {
            listPoints.removeLast()
        }
    }

    override fun getPointScoreTeam1(): String {
        return calculatePointScore(Team.TEAM_1).score
    }

    override fun getPointScoreTeam2(): String {
        return calculatePointScore(Team.TEAM_2).score
    }

    override fun checkWinGame(): Boolean {
        val scoreTeam1 = calculatePointScore(Team.TEAM_1)
        val scoreTeam2 = calculatePointScore(Team.TEAM_2)

        return scoreTeam1 == PointScore.Sixty() || scoreTeam2 == PointScore.Sixty()
    }

    private fun calculatePointScore(team: Team): PointScore {
        val otherTeam = if (team == Team.TEAM_1) Team.TEAM_2 else Team.TEAM_1

        val teamPoints = listPoints.filter {
            it.team == team
        }

        return if (teamPoints.isEmpty()) {
            PointScore.Zero()
        } else if ((isAdvantages(team) && isDeuce(otherTeam)) || isDeuce(team)) {
            PointScore.Forty()
        } else if (isAdvantages(team)) {
            PointScore.Adv()
        } else {
            val result = teamPoints.map {
                it.value
            }

            val point = result.reduce(operation = { acc, value ->
                acc + value
            })

            if (isTieBreakAdvantages(team)) {
                PointScore.TiebreakAdv(point.toString())
            } else {
                mapIntToPointScore(point)
            }
        }
    }

    private fun calculateGameScore(team: Team): GameScore {
        val teamGame = listGame.filter {
            it.team == team
        }

        return if (teamGame.isEmpty()) {
            GameScore.ZERO
        } else {
            val result = teamGame.map {
                it.value
            }
            val gameScore = result.reduce(operation = { acc, value ->
                acc + value
            })
            mapIntToGameScore(gameScore)
        }
    }

    private fun calculateSetScore(team: Team): Int {
        val teamSet = listSet.filter {
            it.team == team
        }

        return if (teamSet.isEmpty()) {
            0
        } else {
            val result = teamSet.map {
                it.value
            }
            result.reduce(operation = { acc, value ->
                acc + value
            })
        }
    }

    private fun addGame(team: Team) {
        val otherTeam = if (team == Team.TEAM_1) Team.TEAM_2 else Team.TEAM_1
        val scoreSelectedTeam = calculateGameScore(team)
        val scoreOtherTeam = calculateGameScore(otherTeam)

        when {
            scoreSelectedTeam == GameScore.FIVE && scoreOtherTeam == GameScore.FIVE -> {
                listGame.add(GameType.Advantages(team))
            }

            scoreSelectedTeam == GameScore.FIVE && scoreOtherTeam == GameScore.SIX -> {
                listGame.add(GameType.TieBreak(team))
            }

            scoreSelectedTeam == GameScore.FIVE || scoreSelectedTeam == GameScore.SIX -> {
                listSet.add(SetType.Base(team))
                previousListGameA.add(
                    if (calculateGameScore(Team.TEAM_1).score.toInt() == 0)
                        calculateGameScore(Team.TEAM_2).score.toInt()
                    else
                        calculateGameScore(Team.TEAM_1).score.toInt() + 1
                )

                previousListGameB.add(
                    if (calculateGameScore(Team.TEAM_2).score.toInt() == 0)
                        calculateGameScore(Team.TEAM_2).score.toInt()
                    else
                        calculateGameScore(Team.TEAM_2).score.toInt() + 1
                )
                listGame.clear()

            }

            else -> {
                listGame.add(GameType.Base(team))
            }
        }
    }

    override fun saveResult() {
        val gameA = calculateGameScore(Team.TEAM_1).score
        val gameB = calculateGameScore(Team.TEAM_2).score
        val setA = calculateSetScore(Team.TEAM_1)
        val setB = calculateSetScore(Team.TEAM_2)

        val resultData = ResultData(
            listGameTeam1 = if (listSet.isNotEmpty()) {
                previousListGameA.add(gameA.toInt())
                previousListGameA
            } else {
                mutableListOf(gameA.toInt())
            },
            listGameTeam2 = if (listSet.isNotEmpty()) {
                previousListGameB.add(gameA.toInt())
                previousListGameB
            } else {
                mutableListOf(gameB.toInt())
            },
            winner = if (listSet.isEmpty()) {
                if (gameA.toInt() > gameB.toInt()) 1 else if (gameA.toInt() == gameB
                        .toInt()
                ) 3 else 2
            } else {
                if (setA > setB) 1 else if (setA == setB) 3 else 2
            },
            data = getCurrentDate(),
            single = isSingleGame
        )

        coroutineScope.launch {
            gameRepository.insetResultMatch(resultData)
            previousListGameA.clear()
            previousListGameB.clear()
            listSet.clear()
            listGame.clear()
            listPoints.clear()
        }
    }

    override suspend fun loadHistoryMatches(): Flow<List<ResultData>> {
        return gameRepository.getHistoryMatches()
    }


    private fun isAdvantages(team: Team): Boolean {
        val lastPoints = listPoints.last()
        return lastPoints.team == team && lastPoints is PointType.Advantages
    }

    private fun isTieBreakAdvantages(team: Team): Boolean {
        if (listPoints.isNotEmpty()) {
            val lastPoints = listPoints.last()
            return lastPoints.team == team && lastPoints is PointType.TieBreakAdvantages
        }

        return false
    }

    private fun isDeuce(team: Team): Boolean {
        val lastPoints = listPoints.last()
        return lastPoints.team == team && lastPoints is PointType.Deuce
    }

    private fun isTiebreak(): Boolean {
        return if (listGame.isEmpty()) {
            false
        } else {
            listGame.last() is GameType.TieBreak
        }

    }


    override fun getGameScoreTeam1(): String {
        return calculateGameScore(Team.TEAM_1).score
    }

    override fun getGameScoreTeam2(): String {
        return calculateGameScore(Team.TEAM_2).score
    }

    override fun getSetScoreTeam1(): Int {
        return calculateSetScore(Team.TEAM_1)
    }

    override fun getSetScoreTeam2(): Int {
        return calculateSetScore(Team.TEAM_2)
    }

    override suspend fun getUserData(): UserEntity? {
        return gameRepository.getUserData()
    }

    override suspend fun getGameSettings(): GameSettingsEntity? {
        return gameRepository.getGameSettings()
    }

    override suspend fun deleteSettingsData() {
        gameRepository
    }
}

