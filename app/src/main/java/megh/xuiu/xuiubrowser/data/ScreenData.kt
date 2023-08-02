package megh.xuiu.xuiubrowser.data

sealed class ScreenData(val route : String) {
    object HomeScreen : ScreenData("home_screen")
    object WebScreen : ScreenData("web_screen")
}