package com.scoresaver.app.wear.features.new_game.presentation

import com.scoresaver.app.wear.features.new_game.model.GameSetting
import com.scoresaver.app.wear.features.new_game.model.Player
import com.scoresaver.app.wear.features.new_game.model.SwitchState

internal sealed interface ViewModelFragment {
    interface NewGame {
        fun getSingleGameSwitchValue(): SwitchState<GameSetting.Single>
        fun getDoubleGameSwitchValue(): SwitchState<GameSetting.Double>
        fun getAdvantagesSwitchValue(): SwitchState<GameSetting.Advantages>
        fun getKillerPointSwitchValue(): SwitchState<GameSetting.KillerPoint>
        fun getServiceOrderConfirmed(): Boolean
        fun insertGameSettings()
    }

    interface GameType {
        fun getSingleGameSwitchValue(): SwitchState<GameSetting.Single>
        fun getDoubleGameSwitchValue(): SwitchState<GameSetting.Double>
        fun setSingleSwitchValue(value: Boolean)
        fun setDoubleSwitchValue(value: Boolean)
    }

    interface GameRule {
        fun getAdvantagesSwitchValue(): SwitchState<GameSetting.Advantages>
        fun getKillerPointSwitchValue(): SwitchState<GameSetting.KillerPoint>
        fun setAdvantagesSwitchValue(value: Boolean)
        fun setKillerPointSwitchValue(value: Boolean)
    }

    interface ServiceOrder {
        fun  getPlayerOneValue(): Player
        fun getPlayerTwoValue(): Player
        fun getPlayerThreeValue(): Player
        fun getPlayerFourValue(): Player
        fun getServiceOrderConfirmed(): Boolean
        fun swapServiceOrderTeamOne()
        fun swapServiceOrderTeamTwo()
        fun swapServiceOrdersBetweenTeams()
        fun toggleServiceOrderConfirmed(newValue: Boolean)
    }

    interface GenderType {
        fun getMaleSwitchValue(): SwitchState<GameSetting.Male>
        fun getFemaleSwitchValue(): SwitchState<GameSetting.Female>
        fun setMaleSwitchValue(value: Boolean)
        fun setFemaleSwitchValue(value: Boolean)
    }

    interface HeightAndWeight {
        fun getHeightValue(): Int
        fun setHeightValue(value: Int)
        fun setWeightValue(value: Int)
        fun getWeightValue(): Int

        fun insertDataUsers()
    }
}