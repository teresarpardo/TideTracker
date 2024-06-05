package ui

import TidesViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import components.InfoCard
import components.TideRepresentationCircle
import nav.DashboardDirections
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import tidesappkmp.composeapp.generated.resources.Res
import tidesappkmp.composeapp.generated.resources.app_tide_actual_header
import tidesappkmp.composeapp.generated.resources.app_tide_harbor_title

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: TidesViewModel = koinInject()
) {
    val viewState = viewModel.viewState.collectAsState()

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(Res.string.app_tide_actual_header))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        ) {
            TideRepresentationCircle(viewState.value.tides?.currentTide ?: 0f)
        }

        Text(text = stringResource(Res.string.app_tide_harbor_title, viewState.value.tides?.harbor.orEmpty()))

        viewState.value.tides?.nextTide?.type?.let { tide ->
            InfoCard(
                state = tide.getState(),
                time = viewState.value.tides?.nextTide?.remainingTime?.inWholeMinutes.toString(),
                name = tide.getName()
            )
        }

        Button(
            onClick = { navController.navigate(DashboardDirections.DETAILS.name) }
        ) {
            Text("Navigate to weather details")
        }
    }
}
