package data.model

import kotlinx.datetime.LocalTime
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.StringResource
import tidetracker.composeapp.generated.resources.Res
import tidetracker.composeapp.generated.resources.app_tide_high_label
import tidetracker.composeapp.generated.resources.app_tide_low_label
import tidetracker.composeapp.generated.resources.app_tide_state_decrease_label
import tidetracker.composeapp.generated.resources.app_tide_state_rise_label
import utils.TimeUtils
import kotlin.time.Duration

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

    val remainingTimeText: Duration get() = remainingTime
}

@Serializable
sealed class TideType(
    val code: String,
    @Contextual val stateResId: StringResource,
    @Contextual val nameResId: StringResource
) {
    class High : TideType(HIGH_TIDE, Res.string.app_tide_state_rise_label, Res.string.app_tide_high_label)

    class Low : TideType(LOW_TIDE, Res.string.app_tide_state_decrease_label, Res.string.app_tide_low_label)

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
