package nav

import TidesViewModel
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import components.TopBar
import org.jetbrains.compose.resources.stringResource
import ui.DetailsScreen
import ui.HomeScreen

@Composable
fun DashboardNavHost() {
    val navController = rememberNavController()
    val backStackEntry = navController.currentBackStackEntryAsState()
    val currentScreen = DashboardDirections.valueOf(
        backStackEntry.value?.destination?.route ?: DashboardDirections.HOME.name
    )

    val viewModel = TidesViewModel()

    Scaffold(
        topBar = {
            TopBar(
                text = stringResource(currentScreen.title),
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = DashboardDirections.HOME.name,
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            composable(DashboardDirections.HOME.name) {
                HomeScreen(navController, viewModel)
            }

            composable(DashboardDirections.DETAILS.name) {
                DetailsScreen()
            }
        }
    }
}
