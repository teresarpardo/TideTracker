package ui

import TidesViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import components.Buttons
import components.ErrorDialog
import components.InfoCard
import components.ProgressBar
import components.Text
import components.TideRepresentationCircle
import data.model.TideType
import nav.DashboardDirections
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import tidetracker.composeapp.generated.resources.Res
import tidetracker.composeapp.generated.resources.app_tide_actual_header
import tidetracker.composeapp.generated.resources.app_tide_harbor_title

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
        when (viewState.value) {
            TidesViewState.Initial -> viewModel.getTides()
            TidesViewState.Loading -> ProgressBar()
            is TidesViewState.Error -> ErrorDialog()
            is TidesViewState.Success -> {
                HomeContent(
                    currentTime = viewState.value.tides?.currentTide ?: 0f,
                    remainingTime = viewState.value.tides?.nextTide?.remainingTime.toString(),
                    harbor = viewState.value.tides?.harbor.orEmpty(),
                    type = viewState.value.tides?.nextTide?.type,
                    navController = navController
                )
            }
        }
    }
}

@Composable
private fun HomeContent(
    currentTime: Float,
    remainingTime: String,
    harbor: String,
    type: TideType?,
    navController: NavController
) {
    Text.Title(text = stringResource(Res.string.app_tide_actual_header))

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
    ) {
        TideRepresentationCircle(currentTime)
    }

    Text.Subtitle(text = stringResource(Res.string.app_tide_harbor_title, harbor))

    type?.let {
        InfoCard(
            state = stringResource(type.stateResId),
            time = remainingTime,
            name = stringResource(type.nameResId)
        )
    }

    Buttons.Filled(
        text = "Navigate to weather details",
        onClick = { navController.navigate(DashboardDirections.DETAILS.name) }
    )
}
