package ak.core.ui.compnents.global.dialogs

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun Ak_AlertDialog(
    title: String,
    body: String,
    onDismiss: () -> Unit,
    confirmButtonTitle: String,
    dismissButtonTitle: String,
    onConfirmButtonClick: () -> Unit,
    onDismissButtonClick: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(title) },
        text = { Text(body) },
        confirmButton = {
            Button(onClick = {
                onConfirmButtonClick()
            }) {
                Text(confirmButtonTitle)
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismissButtonClick() }) {
                Text(dismissButtonTitle)
            }
        }
    )
}