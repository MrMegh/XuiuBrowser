package megh.xuiu.xuiubrowser.ui.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetValue
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import megh.xuiu.xuiubrowser.R
import megh.xuiu.xuiubrowser.data.active
import megh.xuiu.xuiubrowser.data.backCount
import megh.xuiu.xuiubrowser.data.desktop_site
import megh.xuiu.xuiubrowser.data.profile
import megh.xuiu.xuiubrowser.data.search_history
import megh.xuiu.xuiubrowser.data.sheetState
import megh.xuiu.xuiubrowser.data.visible
import megh.xuiu.xuiubrowser.ui.components.Menu
import megh.xuiu.xuiubrowser.ui.screens.content.HomeContent
import kotlin.system.exitProcess

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Home(navController: NavHostController) {
    var scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val snackbarHostState = remember {
        SnackbarHostState()
    }

    val scope = rememberCoroutineScope()
    Profile()
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
                    Menu(snackbarHostState = snackbarHostState)
                    IconButton(onClick = { profile.value = true }) {
                        Icon(imageVector = Icons.Outlined.AccountCircle, contentDescription = null)
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(painter = painterResource(id = R.drawable.tab), contentDescription =null )
                    }
                    IconButton(onClick = { visible.value = true }) {
                        Icon(imageVector = Icons.Outlined.MoreVert, contentDescription = null)
                    }
                }
            )
        },
        content = { HomeContent(paddingValues = it, navController = navController) },
        snackbarHost = { SnackbarHost(snackbarHostState)
        }
    )
    val context = LocalContext.current
    BackHandler {
        backCount.value--
        if (backCount.value==0) exitProcess(1)
        Toast.makeText(context,"Press ${backCount.value} more times",Toast.LENGTH_SHORT).show()
    }
}
