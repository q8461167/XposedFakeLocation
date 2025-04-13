//SettingsScreen.kt
package com.noobexon.xposedfakelocation.manager.ui.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics

// Dimension constants
private object Dimensions {
    val SPACING_SMALL = 8.dp
    val SPACING_MEDIUM = 16.dp
    val SPACING_LARGE = 24.dp
}

// Setting definitions to reduce duplication
private object SettingDefinitions {
    // Define all settings with their parameters
    @Composable
    fun getSettings(viewModel: SettingsViewModel): List<SettingData> = listOf(
        // Randomize Nearby Location
        DoubleSettingData(
            title = "Randomize Nearby Location",
            useValueState = viewModel.useRandomize.collectAsState(),
            valueState = viewModel.randomizeRadius.collectAsState(),
            setUseValue = viewModel::setUseRandomize,
            setValue = viewModel::setRandomizeRadius,
            label = "Randomization Radius (m)",
            minValue = 0f,
            maxValue = 50f,
            step = 0.1f
        ),
        // Custom Horizontal Accuracy
        DoubleSettingData(
            title = "Custom Horizontal Accuracy",
            useValueState = viewModel.useAccuracy.collectAsState(),
            valueState = viewModel.accuracy.collectAsState(),
            setUseValue = viewModel::setUseAccuracy,
            setValue = viewModel::setAccuracy,
            label = "Horizontal Accuracy (m)",
            minValue = 0f,
            maxValue = 100f,
            step = 1f
        ),
        // Custom Vertical Accuracy
        FloatSettingData(
            title = "Custom Vertical Accuracy",
            useValueState = viewModel.useVerticalAccuracy.collectAsState(),
            valueState = viewModel.verticalAccuracy.collectAsState(),
            setUseValue = viewModel::setUseVerticalAccuracy,
            setValue = viewModel::setVerticalAccuracy,
            label = "Vertical Accuracy (m)",
            minValue = 0f,
            maxValue = 100f,
            step = 1f
        ),
        // Custom Altitude
        DoubleSettingData(
            title = "Custom Altitude",
            useValueState = viewModel.useAltitude.collectAsState(),
            valueState = viewModel.altitude.collectAsState(),
            setUseValue = viewModel::setUseAltitude,
            setValue = viewModel::setAltitude,
            label = "Altitude (m)",
            minValue = 0f,
            maxValue = 2000f,
            step = 0.5f
        ),
        // Custom MSL
        DoubleSettingData(
            title = "Custom MSL",
            useValueState = viewModel.useMeanSeaLevel.collectAsState(),
            valueState = viewModel.meanSeaLevel.collectAsState(),
            setUseValue = viewModel::setUseMeanSeaLevel,
            setValue = viewModel::setMeanSeaLevel,
            label = "MSL (m)",
            minValue = -400f,
            maxValue = 2000f,
            step = 0.5f
        ),
        // Custom MSL Accuracy
        FloatSettingData(
            title = "Custom MSL Accuracy",
            useValueState = viewModel.useMeanSeaLevelAccuracy.collectAsState(),
            valueState = viewModel.meanSeaLevelAccuracy.collectAsState(),
            setUseValue = viewModel::setUseMeanSeaLevelAccuracy,
            setValue = viewModel::setMeanSeaLevelAccuracy,
            label = "MSL Accuracy (m)",
            minValue = 0f,
            maxValue = 100f,
            step = 1f
        ),
        // Custom Speed
        FloatSettingData(
            title = "Custom Speed",
            useValueState = viewModel.useSpeed.collectAsState(),
            valueState = viewModel.speed.collectAsState(),
            setUseValue = viewModel::setUseSpeed,
            setValue = viewModel::setSpeed,
            label = "Speed (m/s)",
            minValue = 0f,
            maxValue = 30f,
            step = 0.1f
        ),
        // Custom Speed Accuracy
        FloatSettingData(
            title = "Custom Speed Accuracy",
            useValueState = viewModel.useSpeedAccuracy.collectAsState(),
            valueState = viewModel.speedAccuracy.collectAsState(),
            setUseValue = viewModel::setUseSpeedAccuracy,
            setValue = viewModel::setSpeedAccuracy,
            label = "Speed Accuracy (m/s)",
            minValue = 0f,
            maxValue = 100f,
            step = 1f
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navController: NavController,
    settingsViewModel: SettingsViewModel = viewModel ()
) {
    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()

    // Get settings from the definition object
    val settings = SettingDefinitions.getSettings(settingsViewModel)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack, 
                            contentDescription = "Navigate back"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) { focusManager.clearFocus() }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(Dimensions.SPACING_MEDIUM)
                    .verticalScroll(scrollState)
            ) {
                settings.forEach { setting ->
                    when (setting) {
                        is DoubleSettingData -> {
                            DoubleSettingComposable(setting)
                        }
                        is FloatSettingData -> {
                            FloatSettingComposable(setting)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DoubleSettingItem(
    title: String,
    useValue: Boolean,
    onUseValueChange: (Boolean) -> Unit,
    value: Double,
    onValueChange: (Double) -> Unit,
    label: String,
    minValue: Float,
    maxValue: Float,
    step: Float
) {
    SettingItem(
        title = title,
        useValue = useValue,
        onUseValueChange = onUseValueChange,
        value = value,
        onValueChange = onValueChange,
        label = label,
        minValue = minValue,
        maxValue = maxValue,
        step = step,
        valueFormatter = { "%.2f".format(it) },
        parseValue = { it.toDouble() }
    )
}

@Composable
fun FloatSettingItem(
    title: String,
    useValue: Boolean,
    onUseValueChange: (Boolean) -> Unit,
    value: Float,
    onValueChange: (Float) -> Unit,
    label: String,
    minValue: Float,
    maxValue: Float,
    step: Float
) {
    SettingItem(
        title = title,
        useValue = useValue,
        onUseValueChange = onUseValueChange,
        value = value,
        onValueChange = onValueChange,
        label = label,
        minValue = minValue,
        maxValue = maxValue,
        step = step,
        valueFormatter = { "%.2f".format(it) },
        parseValue = { it }
    )
}

@Composable
private fun <T : Number> SettingItem(
    title: String,
    useValue: Boolean,
    onUseValueChange: (Boolean) -> Unit,
    value: T,
    onValueChange: (T) -> Unit,
    label: String,
    minValue: Float,
    maxValue: Float,
    step: Float,
    valueFormatter: (T) -> String,
    parseValue: (Float) -> T
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.height(Dimensions.SPACING_MEDIUM))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.weight(1f))
            Switch(
                checked = useValue,
                onCheckedChange = onUseValueChange,
                modifier = Modifier.semantics { 
                    contentDescription = if (useValue) "Disable $title" else "Enable $title" 
                }
            )
        }

        if (useValue) {
            Spacer(modifier = Modifier.height(Dimensions.SPACING_SMALL))

            var sliderValue by remember { mutableFloatStateOf(value.toFloat()) }

            LaunchedEffect(value) {
                if (sliderValue != value.toFloat()) {
                    sliderValue = value.toFloat()
                }
            }

            Text(
                text = "$label: ${valueFormatter(parseValue(sliderValue))}",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Slider(
                value = sliderValue,
                onValueChange = { newValue ->
                    sliderValue = newValue
                },
                onValueChangeFinished = {
                    onValueChange(parseValue(sliderValue))
                },
                valueRange = minValue..maxValue,
                steps = ((maxValue - minValue) / step).toInt() - 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .semantics {
                        contentDescription = "Adjust $title value"
                    }
            )
        }
    }
}

sealed class SettingData {
    abstract val title: String
    abstract val useValueState: State<Boolean>
    abstract val setUseValue: (Boolean) -> Unit
    abstract val label: String
    abstract val minValue: Float
    abstract val maxValue: Float
    abstract val step: Float
}

data class DoubleSettingData(
    override val title: String,
    override val useValueState: State<Boolean>,
    val valueState: State<Double>,
    override val setUseValue: (Boolean) -> Unit,
    val setValue: (Double) -> Unit,
    override val label: String,
    override val minValue: Float,
    override val maxValue: Float,
    override val step: Float
) : SettingData()

data class FloatSettingData(
    override val title: String,
    override val useValueState: State<Boolean>,
    val valueState: State<Float>,
    override val setUseValue: (Boolean) -> Unit,
    val setValue: (Float) -> Unit,
    override val label: String,
    override val minValue: Float,
    override val maxValue: Float,
    override val step: Float
) : SettingData()

@Composable
fun DoubleSettingComposable(
    setting: DoubleSettingData
) {
    DoubleSettingItem(
        title = setting.title,
        useValue = setting.useValueState.value,
        onUseValueChange = setting.setUseValue,
        value = setting.valueState.value,
        onValueChange = setting.setValue,
        label = setting.label,
        minValue = setting.minValue,
        maxValue = setting.maxValue,
        step = setting.step
    )
}

@Composable
fun FloatSettingComposable(
    setting: FloatSettingData
) {
    FloatSettingItem(
        title = setting.title,
        useValue = setting.useValueState.value,
        onUseValueChange = setting.setUseValue,
        value = setting.valueState.value,
        onValueChange = setting.setValue,
        label = setting.label,
        minValue = setting.minValue,
        maxValue = setting.maxValue,
        step = setting.step
    )
}