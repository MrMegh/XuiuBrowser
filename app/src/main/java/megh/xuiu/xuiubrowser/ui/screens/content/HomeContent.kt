@file:Suppress("PreviewAnnotationInFunctionWithParameters")

package megh.xuiu.xuiubrowser.ui.screens.content

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import megh.xuiu.xuiubrowser.R
import megh.xuiu.xuiubrowser.data.ScreenData
import megh.xuiu.xuiubrowser.data.alert
import megh.xuiu.xuiubrowser.data.card
import megh.xuiu.xuiubrowser.data.query
import megh.xuiu.xuiubrowser.data.search_history
import megh.xuiu.xuiubrowser.data.sheetState
import megh.xuiu.xuiubrowser.data.url


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(paddingValues: PaddingValues ,navController: NavHostController) {
    if (sheetState.value){
        ModalBottomSheet(sheetState= rememberModalBottomSheetState(),onDismissRequest = { sheetState.value = false }) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(500.dp))
        }
    }
    //Alert dialog for unsafe query
    if (alert.value){
        AlertDialog(
            title = { Text(text = "⚠️ Unsafe ⚠️")},
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
            text = { Text(text = "The ${query.value} you are visiting is not safe !")}
        )
    }
    LazyColumn(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(top = paddingValues.calculateTopPadding()), content = {
        item{
                Text(modifier = Modifier.padding(top = 50.dp, bottom = 30.dp),fontSize = 40.sp, fontFamily = FontFamily.Default, text = "Xuiu")
                SearchBar(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    placeholder = { Text(text = "Search or type web address")},
                    query = query.value,
                    onQueryChange = { query.value= it},
                    onSearch = {
                               if (query.value.startsWith("http:")){
                                   alert.value = true
                               }
                        else{

                            if (query.value.startsWith("https:")|| query.value.startsWith("www")){
                                url.value = query.value
                                search_history.add(url.value)
                                navController.navigate(ScreenData.WebScreen.route)
                            }
                            else{
                                 url.value = "https://www.google.co.in/search?q=${query.value}"
                                 search_history.add(url.value)
                                 navController.navigate(ScreenData.WebScreen.route)
                            }
                        }
                    },
                    active = false,
                    onActiveChange = {},
                    trailingIcon = {
                        if (query.value.isBlank()){
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(painter = painterResource(id = R.drawable.mic), contentDescription =null )
                            }
                        }
                        else{
                            IconButton(onClick = { query.value = ""}) {
                                Icon(imageVector = Icons.Default.Clear, contentDescription = null)
                            }
                        }
                    }
                ) {}

            for (i in 0 until card.size){
                Card(modifier = Modifier
                    .width(340.dp)
                    .padding(8.dp)
                    .height(200.dp), onClick = { /*TODO*/ }) {
                    Box(modifier = Modifier.fillMaxSize().padding(10.dp)){
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(card[i])
                                .crossfade(true)
                                .build(),
                            contentDescription = "stringResource(R.string.description)",
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier
                                .clip(RoundedCornerShape(15.dp))
                                .fillMaxSize()
                        )
                    }

                }
                Divider(modifier = Modifier.width(300.dp))
            }

        }
    })
}
