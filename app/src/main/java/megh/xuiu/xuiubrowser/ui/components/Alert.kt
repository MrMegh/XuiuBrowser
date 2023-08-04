package megh.xuiu.xuiubrowser.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import megh.xuiu.xuiubrowser.data.ScreenData
import megh.xuiu.xuiubrowser.data.alert
import megh.xuiu.xuiubrowser.data.query
import megh.xuiu.xuiubrowser.data.search_history
import megh.xuiu.xuiubrowser.data.url

@Composable
fun Alert(navController : NavHostController) {
    AnimatedVisibility(visible = alert.value) {
        AlertDialog(
            title = { Text(text = "⚠️ Unsafe ⚠️") },
            onDismissRequest = { alert.value = true },
            confirmButton = {
                FilledTonalButton(
                    colors= ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
                    onClick = {
                        url.value = query.value
                        navController.navigate(ScreenData.WebScreen.route)
                        search_history.add(url.value)
                        alert.value = false
                    }
                ) {
                    Text(text = "proceed")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        query.value = ""
                        alert.value = false
                    }
                ) {
                    Text(text = "cancel")
                }
            },
            text = { Text(text = "The ${query.value} you are visiting is not safe !") }
        )
    }
}