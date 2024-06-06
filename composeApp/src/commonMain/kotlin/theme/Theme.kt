package theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidedValue
import androidx.compose.runtime.staticCompositionLocalOf
import theme.colors.AnimationColors
import theme.colors.animationColors
import theme.colors.colorScheme

@Composable
fun Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        *setupCompositionLocals(darkTheme)
    ) {
        MaterialTheme(
            colorScheme = colorScheme(darkTheme),
            typography = Theme.Typography,
            content = content
        )
    }
}

fun setupCompositionLocals(darkTheme: Boolean): Array<ProvidedValue<out Any>> {
    return arrayOf(
        localColorsSystem provides animationColors(darkTheme),
        localTypographySystem provides typography()
    )
}

object Theme {
    val Typography @Composable get() = localTypographySystem.current
    val AnimationColors @Composable get() = localColorsSystem.current
}

private val localColorsSystem = staticCompositionLocalOf<AnimationColors> { error("Animation colors error") }

private val localTypographySystem = staticCompositionLocalOf<Typography> { error("Typography error") }
