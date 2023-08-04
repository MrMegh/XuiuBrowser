@file:OptIn(ExperimentalMaterial3Api::class)

package megh.xuiu.xuiubrowser.data

import android.annotation.SuppressLint
import android.webkit.WebView
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext

var url = mutableStateOf("")
var query = mutableStateOf("")
var desktop_site = mutableStateOf(false)
var alert = mutableStateOf(false)
var active = mutableStateOf(false)
@SuppressLint("StaticFieldLeak")
var webView : WebView? = null
var loading = mutableStateOf(false)
var search_history = arrayListOf<String>()
var visible = mutableStateOf(false)
var backCount = mutableStateOf(2)
var userAgentDesktop = mutableStateOf("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4501.0 Safari/537.36 EdA/91.0.866.0")
var userAgent = ""
var sheetState = mutableStateOf(false)
val card = arrayListOf<String>("https://picsum.photos/600/350","https://picsum.photos/600/355","https://picsum.photos/600/360","https://picsum.photos/600/365","https://picsum.photos/600/350","https://picsum.photos/600/370","https://picsum.photos/600/375","https://picsum.photos/600/380","https://picsum.photos/600/385","https://picsum.photos/600/390",)
var scriptSwitch = mutableStateOf(true)
var zoomSwitch = mutableStateOf(true)
var imageSwitch = mutableStateOf(true)
var profile = mutableStateOf(false)