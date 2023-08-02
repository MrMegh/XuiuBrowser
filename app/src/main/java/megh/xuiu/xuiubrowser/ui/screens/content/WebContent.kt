package megh.xuiu.xuiubrowser.ui.screens.content

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import megh.xuiu.xuiubrowser.data.loading
import megh.xuiu.xuiubrowser.data.url
import megh.xuiu.xuiubrowser.data.webView

@SuppressLint("UnrememberedMutableState")
@Composable
fun WebContent(paddingValues: PaddingValues, navController: NavHostController) {
    var urlForWeb by remember {
        mutableStateOf(url.value)
    }
    Column {
        AnimatedVisibility(visible = loading.value) {
           LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        }
        AndroidView(
            modifier = Modifier.padding(top = paddingValues.calculateTopPadding()),
            factory = {
            WebView(it).apply {
                layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                webViewClient = webViewClient
                settings.apply {
                    javaScriptCanOpenWindowsAutomatically = true
                    javaScriptEnabled = true
                    loadsImagesAutomatically = true
                }
                loadUrl(urlForWeb)
                webView = this
                webViewClient = object : WebViewClient(){
                    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                        super.onPageStarted(view, url, favicon)
                        loading.value = true
                    }

                    override fun onPageFinished(view: WebView?, url: String?) {
                        super.onPageFinished(view, url)
                        loading.value = false
                    }
                }
            }
        },
            update = {
                it.loadUrl(urlForWeb)
            })
    }
}