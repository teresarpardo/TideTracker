package ui

import TidesViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import nav.DashboardDirections

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: TidesViewModel
) {
    val viewState = viewModel.viewState.collectAsState()

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = viewState.value.tides?.harbor.orEmpty())

        Button(
            onClick = { navController.navigate(DashboardDirections.DETAILS.name) }
        ) {
            Text("Navigate to weather details")
        }
    }
}
