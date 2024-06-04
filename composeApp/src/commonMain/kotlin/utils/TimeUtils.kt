package utils

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.toDuration

object TimeUtils {
    val currentTime get() = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).time

    fun remainingTime(
        initialTime: LocalTime = currentTime,
        endTime: LocalTime
    ): Duration {
        val startMinutes = initialTime.hour * 60 + initialTime.minute
        val endMinutes = endTime.hour * 60 + endTime.minute

        val durationMinutes = if (endMinutes >= startMinutes) {
            endMinutes - startMinutes
        } else {
            endMinutes + 1440 - startMinutes
        }

        return durationMinutes.toDuration(DurationUnit.MINUTES)
    }
}
