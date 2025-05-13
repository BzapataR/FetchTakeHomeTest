package com.example.fetch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.fetch.groupList.presentation.GroupListRoot
import com.example.fetch.groupList.presentation.GroupListState
import com.example.fetch.groupList.presentation.SelectedGroupListViewModel
import com.example.fetch.koin.initKoin
import com.example.fetch.ui.theme.FetchTheme
import org.koin.compose.viewmodel.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //initKoin()
        enableEdgeToEdge()
        setContent {
            FetchTheme {
                val navController = rememberNavController()
//                val viewModel = koinViewModel<SelectedGroupListViewModel>()
//                GroupListRoot(viewModel)

                NavHost(
                    navController = navController,
                    startDestination = Route.ListGraph.route
                )
                {
                    navigation(
                        route = Route.ListGraph.route,
                        startDestination = Route.ListScreen.route,
                    )
                    {
                        composable(
                            route = Route.ListScreen.route,
                            exitTransition = { slideOutHorizontally() },
                            popEnterTransition = { slideInHorizontally() }
                        ) {
                            val viewModel = koinViewModel<SelectedGroupListViewModel>()
                            GroupListRoot(viewModel)
                        }
                    }
                }
            }
        }
    }
}