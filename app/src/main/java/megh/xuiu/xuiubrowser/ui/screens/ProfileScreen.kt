@file:OptIn(ExperimentalMaterial3Api::class)

package megh.xuiu.xuiubrowser.ui.screens

import android.text.Selection
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import megh.xuiu.xuiubrowser.data.card
import megh.xuiu.xuiubrowser.data.profile

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalMaterial3Api
@Composable
fun Profile() {
    AnimatedVisibility(visible = profile.value ) {
        ModalBottomSheet(sheetState = rememberModalBottomSheetState(), onDismissRequest = { profile.value = false }) {
            Column {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data("https://github.com/MrMegh/DataBase/blob/main/meghimage.jpg?raw=true")
                            .crossfade(true)
                            .build(),
                        contentDescription = "stringResource(R.string.description)",
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .fillMaxWidth(),
                        alignment = Alignment.Center
                    )
                }
                Text(fontSize = 18.sp, modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp), textAlign = TextAlign.Center, text = "Megh Badoniya")
                Card (modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)){
                    SelectionContainer {
                        Text(modifier = Modifier.padding(10.dp), text = "I am Megh Badoniya, an accomplished and skilled front-end Android application developer who possesses a profound expertise in both Jetpack Compose and Flutter, two cutting-edge frameworks that enable the creation of exceptional user interfaces and seamless cross-platform experiences. Through my extensive experience and dedication to my craft, I have honed my abilities in crafting innovative and efficient solutions for complex mobile app development, consistently pushing the boundaries of technological advancements in the field.")
                    }
                }
            }
        }
    }
}