@file:OptIn(ExperimentalMaterial3Api::class)

package megh.xuiu.xuiubrowser.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import megh.xuiu.xuiubrowser.R
import megh.xuiu.xuiubrowser.data.imageSwitch
import megh.xuiu.xuiubrowser.data.scriptSwitch
import megh.xuiu.xuiubrowser.data.sheetState
import megh.xuiu.xuiubrowser.data.zoomSwitch

@Composable
fun Settings() {
    AnimatedVisibility(visible = sheetState.value) {
        ModalBottomSheet(sheetState= rememberModalBottomSheetState(),onDismissRequest = { sheetState.value = false }) {
            Box(modifier = Modifier
                .fillMaxWidth()
            ){
                LazyColumn(content = {
                    item{
                        Text(textAlign = TextAlign.Center, modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth(), fontSize = 25.sp, fontFamily = FontFamily.Default, text = "Web Settings")
                        ListItem(
                            modifier = Modifier.clickable {  },
                            headlineContent = { Text(text = "Java script") },
                            supportingContent = { Text(text = "turning off this may lead to brick some websites.") },
                            leadingContent = { Icon(painter = painterResource(id = R.drawable.javascript), contentDescription = null) },
                            trailingContent =  {
                                Switch(checked = scriptSwitch.value, onCheckedChange = { scriptSwitch.value =it})
                            }
                        )
                        Divider()
                        ListItem(
                            modifier = Modifier.clickable {  },
                            headlineContent = { Text(text = "Zoom controls") },
                            leadingContent = { Icon(painter = painterResource(id = R.drawable.zoom), contentDescription = null) },
                            supportingContent = { Text(text = "disabling zoom controls is not recommended.") },
                            trailingContent =  {
                                Switch(checked = zoomSwitch.value, onCheckedChange = { zoomSwitch.value =it})
                            }
                        )
                        Divider()
                        ListItem(
                            modifier = Modifier.clickable {  }.padding(bottom = 50.dp),
                            headlineContent = { Text(text = "Load image") },
                            leadingContent = { Icon(painter = painterResource(id = R.drawable.image), contentDescription = null) },
                            supportingContent = { Text(text = "by this you can increase the browsing speed.") },
                            trailingContent =  {
                                Switch(checked = imageSwitch.value, onCheckedChange = { imageSwitch.value =it})
                            }
                        )
                        Divider()
                    }
                })
            }
        }
    }
}