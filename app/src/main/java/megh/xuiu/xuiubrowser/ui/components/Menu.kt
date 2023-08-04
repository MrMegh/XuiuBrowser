package megh.xuiu.xuiubrowser.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import megh.xuiu.xuiubrowser.R
import megh.xuiu.xuiubrowser.data.desktop_site
import megh.xuiu.xuiubrowser.data.sheetState
import megh.xuiu.xuiubrowser.data.visible

@Composable
fun Menu(snackbarHostState : SnackbarHostState) {
    val scope = rememberCoroutineScope()
    if (visible.value) {
        DropdownMenu(expanded = visible.value, onDismissRequest = { visible.value = false }) {
            DropdownMenuItem(
                leadingIcon = { Icon(imageVector = Icons.Outlined.Add, contentDescription = null) },
                text = { Text(text = "New tab") },
                onClick = { /*TODO*/ }
            )
            DropdownMenuItem(
                leadingIcon = { Icon(modifier = Modifier.size(20.dp), painter = painterResource(id = R.drawable.incognito), contentDescription = null) },
                text = { Text(text = "New Incognito tab") },
                onClick = { scope.launch { snackbarHostState.showSnackbar("Please wait 1 more day ❤️", withDismissAction = true, duration = SnackbarDuration.Short) } }
            )
            Divider()
            DropdownMenuItem(
                leadingIcon = { Icon(painter = painterResource(id = R.drawable.download_done), contentDescription = null) },
                text = { Text(text = "Downloads") },
                onClick = { scope.launch { snackbarHostState.showSnackbar("This is still in development", withDismissAction = true, duration = SnackbarDuration.Short) } }
            )
            Divider()
            DropdownMenuItem(
                leadingIcon = { Icon(painter = painterResource(id = R.drawable.share), contentDescription =null ) },
                text = { Text(text = "Share") },
                onClick = { /*TODO*/ }
            )
            DropdownMenuItem(
                leadingIcon = { Icon(painter = painterResource(id = R.drawable.baseline_find_in_page), contentDescription = null) },
                text = { Text(text = "Find in page") },
                onClick = { scope.launch { snackbarHostState.showSnackbar("This is still in development", withDismissAction = true, duration = SnackbarDuration.Short) } }
            )
            DropdownMenuItem(
                leadingIcon = { Icon(painter = painterResource(id = R.drawable.desktop_windows), contentDescription = null) },
                text = { Text(text = "Desktop site") },
                onClick = { /*TODO*/ },
                trailingIcon = {
                    Checkbox(checked = desktop_site.value, onCheckedChange = { desktop_site.value = it })
                }
            )
            Divider()
            DropdownMenuItem(
                leadingIcon = { Icon(painter = painterResource(id = R.drawable.settings), contentDescription =null ) },
                text = { Text(text = "Settings") },
                onClick = {
                    sheetState.value = true
                    visible.value = false
                }
            )
            DropdownMenuItem(
                leadingIcon = { Icon(painter = painterResource(id = R.drawable.help), contentDescription = null) },
                text = { Text(text = "Help & feedback") },
                onClick = { /*TODO*/ }
            )
        }
    }
}