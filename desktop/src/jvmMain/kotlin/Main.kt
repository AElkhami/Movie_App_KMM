/**
 * Created by A.Elkhami on 22/08/2023.
 */
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.example.movieapp.App
import com.example.movieapp.di.initKoin

fun main() = application {
    initKoin {}
    Window(
        title = "Movie App",
        onCloseRequest = ::exitApplication,
    ) { App() }
}