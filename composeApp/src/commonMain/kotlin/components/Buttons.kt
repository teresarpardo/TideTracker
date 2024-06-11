package components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

object Buttons {
    @Composable
    fun Filled(
        text: String,
        onClick: () -> Unit,
        modifier: Modifier = Modifier
    ) {
        Button(
            modifier = modifier.fillMaxWidth().wrapContentHeight(),
            onClick = onClick
        ) {
            Text.Description(text = text)
        }
    }
}
