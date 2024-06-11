package components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import theme.Theme

object Text {
    @Composable
    fun Title(
        modifier: Modifier = Modifier,
        text: String,
        textAlign: TextAlign = TextAlign.Start
    ) {
        Text(
            modifier = modifier,
            text = text,
            textAlign = textAlign,
            style = Theme.Typography.titleMedium
        )
    }

    @Composable
    fun Subtitle(
        modifier: Modifier = Modifier,
        text: String,
        textAlign: TextAlign = TextAlign.Start
    ) {
        Text(
            modifier = modifier,
            text = text,
            textAlign = textAlign,
            style = Theme.Typography.headlineMedium
        )
    }

    @Composable
    fun Description(
        modifier: Modifier = Modifier,
        text: String,
        textAlign: TextAlign = TextAlign.Start
    ) {
        Text(
            modifier = modifier,
            text = text,
            textAlign = textAlign,
            style = Theme.Typography.bodyMedium
        )
    }

    @Composable
    fun Label(
        modifier: Modifier = Modifier,
        text: String,
        textAlign: TextAlign = TextAlign.Start
    ) {
        Text(
            modifier = modifier,
            text = text,
            textAlign = textAlign,
            style = Theme.Typography.labelMedium
        )
    }
}
