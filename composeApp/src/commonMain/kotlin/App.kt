import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import di.appModule
import nav.DashboardNavHost
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication
@Composable
@Preview
fun App() {
    KoinApplication(
        application = { modules(appModule()) }
    ) {
        MaterialTheme {
            DashboardNavHost()
        }
    }
}
