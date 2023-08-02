package megh.xuiu.xuiubrowser.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import megh.xuiu.xuiubrowser.data.ScreenData
import megh.xuiu.xuiubrowser.ui.screens.Home
import megh.xuiu.xuiubrowser.ui.screens.Web
import megh.xuiu.xuiubrowser.ui.theme.XuiuBrowserTheme

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            XuiuBrowserTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController , ScreenData.HomeScreen.route){
                        composable(ScreenData.HomeScreen.route){
                            Home(navController)
                        }
                        composable(ScreenData.WebScreen.route){
                            Web(navController)
                        }
                    }
                }
            }
        }
    }
}
