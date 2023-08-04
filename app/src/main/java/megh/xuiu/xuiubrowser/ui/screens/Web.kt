@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package megh.xuiu.xuiubrowser.ui.screens

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import megh.xuiu.xuiubrowser.R
import megh.xuiu.xuiubrowser.data.ScreenData
import megh.xuiu.xuiubrowser.data.active
import megh.xuiu.xuiubrowser.data.alert
import megh.xuiu.xuiubrowser.data.desktop_site
import megh.xuiu.xuiubrowser.data.query
import megh.xuiu.xuiubrowser.data.search_history
import megh.xuiu.xuiubrowser.data.sheetState
import megh.xuiu.xuiubrowser.data.url
import megh.xuiu.xuiubrowser.data.visible
import megh.xuiu.xuiubrowser.data.webView
import megh.xuiu.xuiubrowser.ui.components.Alert
import megh.xuiu.xuiubrowser.ui.components.Menu
import megh.xuiu.xuiubrowser.ui.components.Settings
import megh.xuiu.xuiubrowser.ui.screens.content.WebContent

@ExperimentalMaterial3Api
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Web(navController: NavHostController) {
    val snackbarHostState = remember {
        SnackbarHostState()
    }
    var scope = rememberCoroutineScope()

    Settings()

    Alert(navController = navController)

    Scaffold(
        topBar = {

                     SearchBar(
                         modifier = Modifier.fillMaxWidth(),
                         placeholder = { Text(text = "Search or type web address")},
                         query = query.value,
                         onQueryChange = { query.value= it},
                         onSearch = {

                             if (query.value.startsWith("http://")){
                                 alert.value = true
                             }
                             else{

                                 if (query.value.startsWith("https:")|| query.value.startsWith("www")){
                                     url.value = query.value
                                     webView?.loadUrl(url.value)
                                     search_history.add(url.value)
                                     active.value =false
                                 }
                                 else{
                                     url.value = "https://www.google.co.in/search?q=${query.value}"
                                     webView?.loadUrl("https://www.google.co.in/search?q=${query.value}")
                                     search_history.add(url.value)
                                     active.value =false
                                 }
                             }
                         },
                         active = active.value,
                         onActiveChange = { active.value =it},
                         leadingIcon = {
                            if (query.value.startsWith("http:")){
                                Icon(painter = painterResource(id = R.drawable.lock_open), contentDescription =null )
                            }
                             else{
                                Icon(painter = painterResource(id = R.drawable.lock), contentDescription =null )
                            }

                         },
                         trailingIcon = {
                             Menu(snackbarHostState = snackbarHostState)
                             if (!active.value){
                                 IconButton(onClick = { visible.value = true }) {
                                     Icon(imageVector = Icons.Default.MoreVert, contentDescription = null)
                                 }
                             }
                             else{
                                 IconButton(onClick = {
                                     if (query.value.isBlank()){
                                         active.value = false
                                     }
                                     else{
                                         query.value=""
                                     }
                                 }) {
                                     Icon(imageVector = Icons.Default.Clear, contentDescription = null)
                                 }
                             }
                         }
                     ) {
                         LazyColumn(content = {
                             items(search_history.size){
                                 ListItem(
                                     modifier = Modifier.clickable {
                                         webView?.loadUrl(url.value)
                                         active.value=false
                                     },
                                     leadingContent = { Icon(painter = painterResource(id = R.drawable.history), contentDescription = null)},
                                     headlineContent = { Text(text = search_history[it]) },
                                     trailingContent = { Icon(
                                         imageVector = Icons.Default.ArrowForward,
                                         contentDescription = null
                                     )}
                                 )
                             }
                         })
                     }

        },
        content = {
            WebContent(paddingValues = it, navController = navController)
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { webView?.reload() }) {
                Icon(imageVector = Icons.Default.Refresh, contentDescription = null)
            }
        },
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    )
    BackHandler {
        if (webView?.canGoBack()==true){
            webView?.goBack()
        }
        else{
            navController.popBackStack()
        }
    }
}