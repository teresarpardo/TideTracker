package components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import org.jetbrains.compose.resources.stringResource
import tidetracker.composeapp.generated.resources.Res
import tidetracker.composeapp.generated.resources.app_general_button_ok
import tidetracker.composeapp.generated.resources.app_general_error_generic_description
import tidetracker.composeapp.generated.resources.app_general_error_generic_title

@Composable
fun ErrorDialog(
    title: String = stringResource(Res.string.app_general_error_generic_title),
    message: String = stringResource(Res.string.app_general_error_generic_description),
    textButton: String = stringResource(Res.string.app_general_button_ok),
    onDismissRequest: (() -> Unit)? = null
) {
    var showDialog by remember { mutableStateOf(true) }

    if (showDialog) {
        Dialog(
            onDismissRequest = {
                showDialog = false
                onDismissRequest?.invoke()
            }
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text.Title(text = title)
                Spacer(Modifier.height(16.dp))
                Text.Description(
                    text = message,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.height(16.dp))
                Buttons.Filled(
                    text = textButton,
                    onClick = {
                        showDialog = false
                        onDismissRequest?.invoke()
                    }
                )
            }
        }
    }
}
