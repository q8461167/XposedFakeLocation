//SettingsViewModel.kt
package com.noobexon.xposedfakelocation.manager.ui.settings

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.noobexon.xposedfakelocation.data.*
import com.noobexon.xposedfakelocation.data.repository.PreferencesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SettingsViewModel(application: Application) : AndroidViewModel(application) {
    private val preferencesRepository = PreferencesRepository(application)

    private val _useAccuracy = MutableStateFlow(DEFAULT_USE_ACCURACY)
    val useAccuracy: StateFlow<Boolean> get() = _useAccuracy

    private val _accuracy = MutableStateFlow(DEFAULT_ACCURACY)
    val accuracy: StateFlow<Double> get() = _accuracy

    private val _useAltitude = MutableStateFlow(DEFAULT_USE_ALTITUDE)
    val useAltitude: StateFlow<Boolean> get() = _useAltitude

    private val _altitude = MutableStateFlow(DEFAULT_ALTITUDE)
    val altitude: StateFlow<Double> get() = _altitude

    private val _useRandomize = MutableStateFlow(DEFAULT_USE_RANDOMIZE)
    val useRandomize: StateFlow<Boolean> get() = _useRandomize

    private val _randomizeRadius = MutableStateFlow(DEFAULT_RANDOMIZE_RADIUS)
    val randomizeRadius: StateFlow<Double> get() = _randomizeRadius

    private val _useVerticalAccuracy = MutableStateFlow(DEFAULT_USE_VERTICAL_ACCURACY)
    val useVerticalAccuracy: StateFlow<Boolean> get() = _useVerticalAccuracy

    private val _verticalAccuracy = MutableStateFlow(DEFAULT_VERTICAL_ACCURACY)
    val verticalAccuracy: StateFlow<Float> get() = _verticalAccuracy

    private val _useMeanSeaLevel = MutableStateFlow(DEFAULT_USE_MEAN_SEA_LEVEL)
    val useMeanSeaLevel: StateFlow<Boolean> get() = _useMeanSeaLevel

    private val _meanSeaLevel = MutableStateFlow(DEFAULT_MEAN_SEA_LEVEL)
    val meanSeaLevel: StateFlow<Double> get() = _meanSeaLevel

    private val _useMeanSeaLevelAccuracy = MutableStateFlow(DEFAULT_USE_MEAN_SEA_LEVEL_ACCURACY)
    val useMeanSeaLevelAccuracy: StateFlow<Boolean> get() = _useMeanSeaLevelAccuracy

    private val _meanSeaLevelAccuracy = MutableStateFlow(DEFAULT_MEAN_SEA_LEVEL_ACCURACY)
    val meanSeaLevelAccuracy: StateFlow<Float> get() = _meanSeaLevelAccuracy

    private val _useSpeed = MutableStateFlow(DEFAULT_USE_SPEED)
    val useSpeed: StateFlow<Boolean> get() = _useSpeed

    private val _speed = MutableStateFlow(DEFAULT_SPEED)
    val speed: StateFlow<Float> get() = _speed

    private val _useSpeedAccuracy = MutableStateFlow(DEFAULT_USE_SPEED_ACCURACY)
    val useSpeedAccuracy: StateFlow<Boolean> get() = _useSpeedAccuracy

    private val _speedAccuracy = MutableStateFlow(DEFAULT_SPEED_ACCURACY)
    val speedAccuracy: StateFlow<Float> get() = _speedAccuracy

    init {
        viewModelScope.launch {
            // Collect values from data store flows
            launch {
                preferencesRepository.getUseAccuracyFlow().collectLatest { 
                    _useAccuracy.value = it 
                }
            }
            
            launch {
                preferencesRepository.getAccuracyFlow().collectLatest { 
                    _accuracy.value = it 
                }
            }
            
            launch {
                preferencesRepository.getUseAltitudeFlow().collectLatest { 
                    _useAltitude.value = it 
                }
            }
            
            launch {
                preferencesRepository.getAltitudeFlow().collectLatest { 
                    _altitude.value = it 
                }
            }
            
            launch {
                preferencesRepository.getUseRandomizeFlow().collectLatest { 
                    _useRandomize.value = it 
                }
            }
            
            launch {
                preferencesRepository.getRandomizeRadiusFlow().collectLatest { 
                    _randomizeRadius.value = it 
                }
            }
            
            launch {
                preferencesRepository.getUseVerticalAccuracyFlow().collectLatest { 
                    _useVerticalAccuracy.value = it 
                }
            }
            
            launch {
                preferencesRepository.getVerticalAccuracyFlow().collectLatest { 
                    _verticalAccuracy.value = it 
                }
            }
            
            launch {
                preferencesRepository.getUseMeanSeaLevelFlow().collectLatest { 
                    _useMeanSeaLevel.value = it 
                }
            }
            
            launch {
                preferencesRepository.getMeanSeaLevelFlow().collectLatest { 
                    _meanSeaLevel.value = it 
                }
            }
            
            launch {
                preferencesRepository.getUseMeanSeaLevelAccuracyFlow().collectLatest { 
                    _useMeanSeaLevelAccuracy.value = it 
                }
            }
            
            launch {
                preferencesRepository.getMeanSeaLevelAccuracyFlow().collectLatest { 
                    _meanSeaLevelAccuracy.value = it 
                }
            }
            
            launch {
                preferencesRepository.getUseSpeedFlow().collectLatest { 
                    _useSpeed.value = it 
                }
            }
            
            launch {
                preferencesRepository.getSpeedFlow().collectLatest { 
                    _speed.value = it 
                }
            }
            
            launch {
                preferencesRepository.getUseSpeedAccuracyFlow().collectLatest { 
                    _useSpeedAccuracy.value = it 
                }
            }
            
            launch {
                preferencesRepository.getSpeedAccuracyFlow().collectLatest { 
                    _speedAccuracy.value = it 
                }
            }
        }
    }

    fun setUseAccuracy(value: Boolean) {
        _useAccuracy.value = value
        viewModelScope.launch {
            preferencesRepository.saveUseAccuracy(value)
        }
    }

    fun setAccuracy(value: Double) {
        _accuracy.value = value
        viewModelScope.launch {
            preferencesRepository.saveAccuracy(value)
        }
    }

    fun setUseAltitude(value: Boolean) {
        _useAltitude.value = value
        viewModelScope.launch {
            preferencesRepository.saveUseAltitude(value)
        }
    }

    fun setAltitude(value: Double) {
        _altitude.value = value
        viewModelScope.launch {
            preferencesRepository.saveAltitude(value)
        }
    }

    fun setUseRandomize(value: Boolean) {
        _useRandomize.value = value
        viewModelScope.launch {
            preferencesRepository.saveUseRandomize(value)
        }
    }

    fun setRandomizeRadius(value: Double) {
        _randomizeRadius.value = value
        viewModelScope.launch {
            preferencesRepository.saveRandomizeRadius(value)
        }
    }

    fun setUseVerticalAccuracy(value: Boolean) {
        _useVerticalAccuracy.value = value
        viewModelScope.launch {
            preferencesRepository.saveUseVerticalAccuracy(value)
        }
    }

    fun setVerticalAccuracy(value: Float) {
        _verticalAccuracy.value = value
        viewModelScope.launch {
            preferencesRepository.saveVerticalAccuracy(value)
        }
    }

    fun setUseMeanSeaLevel(value: Boolean) {
        _useMeanSeaLevel.value = value
        viewModelScope.launch {
            preferencesRepository.saveUseMeanSeaLevel(value)
        }
    }

    fun setMeanSeaLevel(value: Double) {
        _meanSeaLevel.value = value
        viewModelScope.launch {
            preferencesRepository.saveMeanSeaLevel(value)
        }
    }

    fun setUseMeanSeaLevelAccuracy(value: Boolean) {
        _useMeanSeaLevelAccuracy.value = value
        viewModelScope.launch {
            preferencesRepository.saveUseMeanSeaLevelAccuracy(value)
        }
    }

    fun setMeanSeaLevelAccuracy(value: Float) {
        _meanSeaLevelAccuracy.value = value
        viewModelScope.launch {
            preferencesRepository.saveMeanSeaLevelAccuracy(value)
        }
    }

    fun setUseSpeed(value: Boolean) {
        _useSpeed.value = value
        viewModelScope.launch {
            preferencesRepository.saveUseSpeed(value)
        }
    }

    fun setSpeed(value: Float) {
        _speed.value = value
        viewModelScope.launch {
            preferencesRepository.saveSpeed(value)
        }
    }

    fun setUseSpeedAccuracy(value: Boolean) {
        _useSpeedAccuracy.value = value
        viewModelScope.launch {
            preferencesRepository.saveUseSpeedAccuracy(value)
        }
    }

    fun setSpeedAccuracy(value: Float) {
        _speedAccuracy.value = value
        viewModelScope.launch {
            preferencesRepository.saveSpeedAccuracy(value)
        }
    }
}