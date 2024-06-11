package nav

import org.jetbrains.compose.resources.StringResource
import tidetracker.composeapp.generated.resources.Res
import tidetracker.composeapp.generated.resources.app_navigation_details
import tidetracker.composeapp.generated.resources.app_navigation_home

enum class DashboardDirections(val title: StringResource) {
    HOME(title = Res.string.app_navigation_home),
    DETAILS(title = Res.string.app_navigation_details)
}
