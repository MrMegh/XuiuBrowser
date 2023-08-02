package megh.xuiu.xuiubrowser.data

import android.annotation.SuppressLint
import android.webkit.WebView
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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


