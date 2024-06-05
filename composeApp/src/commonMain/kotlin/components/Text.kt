package components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

object Text {
    @Composable
    fun Title(
        modifier: Modifier = Modifier,
        text: String,
        textAlign: TextAlign = TextAlign.Start
    ) {
    }

    @Composable
    fun Subtitle(
        modifier: Modifier = Modifier,
        text: String,
        textAlign: TextAlign = TextAlign.Start
    ) {}

    @Composable
    fun Description(
        modifier: Modifier = Modifier,
        text: String,
        textAlign: TextAlign = TextAlign.Start
    ) {}

    @Composable
    fun Label(
        modifier: Modifier = Modifier,
        text: String,
        textAlign: TextAlign = TextAlign.Start
    ) {}
}
