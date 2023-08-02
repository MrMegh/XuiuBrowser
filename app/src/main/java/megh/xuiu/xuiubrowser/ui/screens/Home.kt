package megh.xuiu.xuiubrowser.ui.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import megh.xuiu.xuiubrowser.R
import megh.xuiu.xuiubrowser.data.backCount
import megh.xuiu.xuiubrowser.data.desktop_site
import megh.xuiu.xuiubrowser.data.search_history
import megh.xuiu.xuiubrowser.data.visible
import megh.xuiu.xuiubrowser.ui.screens.content.HomeContent
import kotlin.system.exitProcess

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Home(navController: NavHostController) {
    var scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            search_history.plus("Hello")
            TopAppBar(
                scrollBehavior=scrollBehavior,
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
                title = { /*TODO*/ },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Outlined.Home, contentDescription = null)
                    }
                },
                actions = {
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
                                onClick = { /*TODO*/ }
                            )
                            Divider()
                            DropdownMenuItem(
                                leadingIcon = { Icon(painter = painterResource(id = R.drawable.download_done), contentDescription = null) },
                                text = { Text(text = "Downloads") },
                                onClick = { /*TODO*/ }
                            )
                            Divider()
                            DropdownMenuItem(
                                leadingIcon = { Icon(painter = painterResource(id = R.drawable.share), contentDescription =null )},
                                text = { Text(text = "Share") },
                                onClick = { /*TODO*/ }
                            )
                            DropdownMenuItem(
                                leadingIcon = { Icon(painter = painterResource(id = R.drawable.baseline_find_in_page), contentDescription = null) },
                                text = { Text(text = "Find in page") },
                                onClick = { /*TODO*/ }
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
                                leadingIcon = {Icon(painter = painterResource(id = R.drawable.settings), contentDescription =null )},
                                text = { Text(text = "Settings") },
                                onClick = { /*TODO*/ }
                            )
                            DropdownMenuItem(
                                leadingIcon = { Icon(painter = painterResource(id = R.drawable.help), contentDescription = null) },
                                text = { Text(text = "Help & feedback") },
                                onClick = { /*TODO*/ }
                            )
                        }
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Outlined.AccountCircle, contentDescription = null)
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Outlined.Add, contentDescription = null)
                    }
                    IconButton(onClick = { visible.value = true }) {
                        Icon(imageVector = Icons.Outlined.MoreVert, contentDescription = null)
                    }
                }
            )
        },
        content = { HomeContent(paddingValues = it, navController = navController) },
    )
    val context = LocalContext.current
    BackHandler {
        backCount.value--
        if (backCount.value==0) exitProcess(1)
        Toast.makeText(context,"Press ${backCount.value} more times",Toast.LENGTH_SHORT).show()
    }
}
