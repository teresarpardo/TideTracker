package theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

@Composable
fun Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        localTypographySystem provides typography(),
        localCustomColorsSystem provides customColors(darkTheme)
    ) {
        MaterialTheme(
            colorScheme = Theme.CustomColors.colorScheme,
            typography = Theme.Typography,
            content = content
        )
    }
}

object Theme {
    val Typography @Composable get() = localTypographySystem.current
    val CustomColors @Composable get() = localCustomColorsSystem.current
}

private val localTypographySystem = staticCompositionLocalOf<Typography> { error("Typography error") }
private val localCustomColorsSystem = staticCompositionLocalOf<CustomColors> { error("Custom colors error") }
