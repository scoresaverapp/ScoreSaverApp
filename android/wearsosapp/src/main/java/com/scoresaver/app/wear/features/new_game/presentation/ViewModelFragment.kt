package com.scoresaver.app.wear.features.new_game.presentation

import com.scoresaver.app.wear.features.new_game.model.GameSetting
import com.scoresaver.app.wear.features.new_game.model.Player
import com.scoresaver.app.wear.features.new_game.model.SwitchState

internal sealed interface ViewModelFragment {
    interface NewGame {
        fun getSportType(): Int
        fun setSportType(value: Int)
        fun getSingleGameSwitchValue(): SwitchState<GameSetting.Single>
        fun getDoubleGameSwitchValue(): SwitchState<GameSetting.Double>
        fun getAdvantagesSwitchValue(): SwitchState<GameSetting.Advantages>
        fun getKillerPointSwitchValue(): SwitchState<GameSetting.KillerPoint>
        fun insertGameSettings()
        fun resetData()
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

    interface GenderType {
        fun getMaleSwitchValue(): SwitchState<GameSetting.Male>
        fun getFemaleSwitchValue(): SwitchState<GameSetting.Female>
        fun setMaleSwitchValue(value: Boolean)
        fun setFemaleSwitchValue(value: Boolean)
    }

    interface UserData {
        fun getHeightValue(): Int
        fun setHeightValue(value: Int)
        fun setWeightValue(value: Int)
        fun getWeightValue(): Int

        fun setAge(value: Int)
        fun getAge(): Int

        fun insertDataUsers()
    }
}