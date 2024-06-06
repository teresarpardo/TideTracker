package components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import theme.Theme

@Composable
fun TideRepresentationCircle(
    time: Float,
    sandColor: Color = Theme.AnimationColors.sand,
    waterColor: Color = Theme.AnimationColors.water
) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        val radius = size.minDimension / 2
        val center = Offset(canvasWidth / 2, canvasHeight / 2)
        val separationHeight = canvasHeight * time

        drawCircle(
            color = Color.Transparent,
            center = center,
            radius = radius
        )

        clipPath(
            path = Path().apply {
                addOval(
                    Rect(
                        left = center.x - radius,
                        top = center.y - radius,
                        right = center.x + radius,
                        bottom = center.y + radius
                    )
                )
            }
        ) {
            // Sand part (top part)
            drawRect(
                color = sandColor,
                topLeft = Offset(0f, 0f),
                size = Size(canvasWidth, separationHeight)
            )

            // Sea part (bottom part)
            drawRect(
                color = waterColor,
                topLeft = Offset(0f, separationHeight),
                size = Size(canvasWidth, canvasHeight - separationHeight)
            )
        }
    }
}
