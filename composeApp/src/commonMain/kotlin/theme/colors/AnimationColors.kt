package theme.colors

import androidx.compose.ui.graphics.Color

data class AnimationColors(
    val sand: Color,
    val water: Color
)

private val animationColorsDark: AnimationColors
    get() = AnimationColors(sand = sandSurface, water = waterSurface)

private val animationColorsLight: AnimationColors
    get() = AnimationColors(sand = sandSurface, water = waterSurface)

internal fun animationColors(darkTheme: Boolean) = if (darkTheme) animationColorsDark else animationColorsLight

val sandSurface = Color(0xFFD19C56)
val waterSurface = Color(0xFF6FAECE)
