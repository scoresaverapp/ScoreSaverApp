package com.scoresaver.app.wear.features.new_game.presentation

import android.content.SharedPreferences
import android.content.Context
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scoresaver.app.util.db.entity.GAME_POINT
import com.scoresaver.app.util.db.entity.GAME_TYPE
import com.scoresaver.app.util.db.entity.GENDER
import com.scoresaver.app.util.db.entity.GameSettingsEntity
import com.scoresaver.app.util.db.entity.SPORT_TYPE
import com.scoresaver.app.util.db.entity.UserEntity
import com.scoresaver.newgame_interactor.interactors.NewGameInteractor
import com.scoresaver.app.wear.features.new_game.model.GameSetting
import com.scoresaver.app.wear.features.new_game.model.Player
import com.scoresaver.app.wear.features.new_game.model.ServiceOder
import com.scoresaver.app.wear.features.new_game.model.SwitchState
import com.scoresaver.app.wear.features.new_game.model.Team
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class NewGameViewModel @Inject constructor(
    private val newGameInteractor: NewGameInteractor,
    @ApplicationContext
    context: Context
) : ViewModel(),
    ViewModelFragment.NewGame,
    ViewModelFragment.GameType,
    ViewModelFragment.GameRule,
    ViewModelFragment.GenderType,
    ViewModelFragment.UserData {
    private val _maleSwitchState = mutableStateOf(SwitchState(GameSetting.Male, false))
    private val _femaleSwitchState = mutableStateOf(SwitchState(GameSetting.Female, false))
    private val _singleSwitchState = mutableStateOf(SwitchState(GameSetting.Single, false))
    private val _doubleSwitchState = mutableStateOf(SwitchState(GameSetting.Double, false))
    private val _advantagesSwitchState = mutableStateOf(SwitchState(GameSetting.Advantages, false))
    private val _killerPointSwitchState =
        mutableStateOf(SwitchState(GameSetting.KillerPoint, false))

    private var _heightValue = mutableIntStateOf(160)
    private val _weightValue = mutableIntStateOf(70)
    private val _ageValue = mutableIntStateOf(18)
    private val _sportType = mutableIntStateOf(1)
    private var isMaleOrGender: GENDER = GENDER.MALE
    override fun getSportType(): Int {
        return _sportType.intValue
    }

    override fun setSportType(value: Int) {
        _sportType.intValue = value
    }

    override fun getSingleGameSwitchValue(): SwitchState<GameSetting.Single> {
        return _singleSwitchState.value
    }

    override fun getDoubleGameSwitchValue(): SwitchState<GameSetting.Double> {
        return _doubleSwitchState.value
    }

    override fun setSingleSwitchValue(value: Boolean) {
        setSwitchValue(GameSetting.Single, value)
    }

    override fun setDoubleSwitchValue(value: Boolean) {
        setSwitchValue(GameSetting.Double, value)
    }

    override fun getAdvantagesSwitchValue(): SwitchState<GameSetting.Advantages> {
        return _advantagesSwitchState.value
    }

    override fun getKillerPointSwitchValue(): SwitchState<GameSetting.KillerPoint> {
        return _killerPointSwitchState.value
    }

    override fun setAdvantagesSwitchValue(value: Boolean) {
        setSwitchValue(GameSetting.Advantages, value)
    }

    override fun setKillerPointSwitchValue(value: Boolean) {
        setSwitchValue(GameSetting.KillerPoint, value)
    }

    private fun setSwitchValue(setting: GameSetting, value: Boolean) {
        when (setting) {
            GameSetting.Single -> {
                if (value && _doubleSwitchState.value.switchValue) {
                    _doubleSwitchState.value = _doubleSwitchState.value.copy(switchValue = false)
                }

                _singleSwitchState.value = _singleSwitchState.value.copy(switchValue = value)
            }

            GameSetting.Double -> {
                if (value && _singleSwitchState.value.switchValue) {
                    _singleSwitchState.value = _singleSwitchState.value.copy(switchValue = false)
                }

                _doubleSwitchState.value = _doubleSwitchState.value.copy(switchValue = value)
            }

            GameSetting.Advantages -> {
                if (value && _killerPointSwitchState.value.switchValue) {
                    _killerPointSwitchState.value =
                        _killerPointSwitchState.value.copy(switchValue = false)
                }

                _advantagesSwitchState.value =
                    _advantagesSwitchState.value.copy(switchValue = value)
            }

            GameSetting.KillerPoint -> {
                if (value && _advantagesSwitchState.value.switchValue) {
                    _advantagesSwitchState.value =
                        _advantagesSwitchState.value.copy(switchValue = false)
                }

                _killerPointSwitchState.value =
                    _killerPointSwitchState.value.copy(switchValue = value)
            }

            GameSetting.Male -> {
                if (value && _femaleSwitchState.value.switchValue) {
                    _femaleSwitchState.value = _femaleSwitchState.value.copy(switchValue = false)
                }

                _maleSwitchState.value = _maleSwitchState.value.copy(switchValue = value)
            }

            GameSetting.Female -> {
                if (value && _maleSwitchState.value.switchValue) {
                    _maleSwitchState.value = _maleSwitchState.value.copy(switchValue = false)
                }

                _femaleSwitchState.value = _femaleSwitchState.value.copy(switchValue = value)
            }

            else -> {}
        }
    }


    override fun getMaleSwitchValue(): SwitchState<GameSetting.Male> {
        return _maleSwitchState.value
    }

    override fun getFemaleSwitchValue(): SwitchState<GameSetting.Female> {
        return _femaleSwitchState.value
    }

    override fun setMaleSwitchValue(value: Boolean) {
        setSwitchValue(GameSetting.Male, value)
        isMaleOrGender = GENDER.MALE
    }

    override fun setFemaleSwitchValue(value: Boolean) {
        setSwitchValue(GameSetting.Female, value)
        isMaleOrGender = GENDER.FEMALE
    }

    override fun insertGameSettings() {
        viewModelScope.launch {
            newGameInteractor.insertGameSettings(
                GameSettingsEntity(
                    gameType = if (getSingleGameSwitchValue().switchValue) GAME_TYPE.SINGLE else GAME_TYPE.DOUBLE,
                    gamePoint = if (getAdvantagesSwitchValue().switchValue) GAME_POINT.ADV else GAME_POINT.KILLER,
                    sportType = if(_sportType.intValue == 1) SPORT_TYPE.PADEL else SPORT_TYPE.TENNIS
                )
            )
        }
    }

    override fun resetData() {
        _singleSwitchState.value = SwitchState(GameSetting.Single, false)
        _doubleSwitchState.value = SwitchState(GameSetting.Double, false)
        _singleSwitchState.value = SwitchState(GameSetting.Single, false)
        _advantagesSwitchState.value = SwitchState(GameSetting.Advantages, false)
        _sportType.intValue = 1
    }


    override fun getHeightValue(): Int {
        return _heightValue.intValue
    }

    override fun setHeightValue(value: Int) {
        _heightValue.intValue = value
    }

    override fun setWeightValue(value: Int) {
        _weightValue.intValue = value
    }

    override fun getWeightValue(): Int {
        return _weightValue.intValue
    }

    override fun setAge(value: Int) {
        _ageValue.intValue = value
    }

    override fun getAge(): Int {
        return _ageValue.intValue
    }

    override fun insertDataUsers() {
        viewModelScope.launch {

            newGameInteractor.insertDataUser(
                UserEntity(
                    height = getHeightValue(),
                    weight = getWeightValue(),
                    age = getAge(),
                    gender = isMaleOrGender,
                )
            )
        }
    }
}