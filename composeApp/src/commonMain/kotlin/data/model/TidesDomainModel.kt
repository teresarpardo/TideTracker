package data.model

import androidx.compose.runtime.Composable
import kotlinx.datetime.LocalTime
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import tidesappkmp.composeapp.generated.resources.Res
import tidesappkmp.composeapp.generated.resources.app_tide_high_label
import tidesappkmp.composeapp.generated.resources.app_tide_low_label
import tidesappkmp.composeapp.generated.resources.app_tide_state_decrease_label
import tidesappkmp.composeapp.generated.resources.app_tide_state_rise_label
import utils.TimeUtils

@Serializable
data class TidesDomainModel(
    val copyright: String,
    val harbor: String,
    val lat: String,
    val lon: String,
    val date: String,
    val data: List<TideItemDomainModel>
) {
    val nextTide: TideItemDomainModel get() = data.filter { tide ->
        tide.time > TimeUtils.currentTime
    }.minBy { it.time }

    private val nextTideIndex = data.indexOf(nextTide)

    private val elapsedTime: Float get() = when (nextTide.type) {
        is TideType.High -> TimeUtils.remainingTime(initialTime = data[nextTideIndex - 1].time, endTime = nextTide.time)
        is TideType.Low -> TimeUtils.remainingTime(initialTime = nextTide.time, endTime = data[nextTideIndex + 1].time)
    }.inWholeMinutes.toFloat()

    val currentTide: Float get() = nextTide.remainingTime.inWholeMinutes.toFloat() / elapsedTime
}

@Serializable
data class TideItemDomainModel(
    val time: LocalTime,
    val high: String,
    val type: TideType
) {
    val remainingTime get() = TimeUtils.remainingTime(endTime = time)
}

@Serializable
sealed class TideType(
    val code: String,
    @Contextual val stateResId: StringResource,
    @Contextual val nameResId: StringResource
) {

    class High : TideType(HIGH_TIDE, Res.string.app_tide_state_rise_label, Res.string.app_tide_high_label)

    class Low : TideType(LOW_TIDE, Res.string.app_tide_state_decrease_label, Res.string.app_tide_low_label)

    @Composable
    fun getState() = stringResource(
        when (this) {
            is High -> Res.string.app_tide_state_rise_label
            is Low -> Res.string.app_tide_state_decrease_label
        }
    )

    @Composable
    fun getName() = stringResource(
        when (this) {
            is High -> Res.string.app_tide_high_label
            is Low -> Res.string.app_tide_low_label
        }
    )

    companion object {
        private const val HIGH_TIDE = "pleamar"
        private const val LOW_TIDE = "bajamar"

        fun fromCode(code: String): TideType = when (code) {
            HIGH_TIDE -> High()
            LOW_TIDE -> Low()
            else -> throw IllegalArgumentException("Tide type code not handled")
        }
    }
}
