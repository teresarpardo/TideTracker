package nav

import org.jetbrains.compose.resources.StringResource
import tidesappkmp.composeapp.generated.resources.Res
import tidesappkmp.composeapp.generated.resources.app_navigation_details
import tidesappkmp.composeapp.generated.resources.app_navigation_home

enum class DashboardDirections(val title: StringResource) {
    HOME(title = Res.string.app_navigation_home),
    DETAILS(title = Res.string.app_navigation_details)
}
