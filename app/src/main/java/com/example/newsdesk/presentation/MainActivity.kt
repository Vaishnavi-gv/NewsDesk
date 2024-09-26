package com.example.newsdesk.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.newsdesk.presentation.Bookmarks.BookmarkScreen
import com.example.newsdesk.presentation.home.HomeScreen
import com.example.newsdesk.presentation.news_details.NewsDetailScreen
import com.example.newsdesk.ui.theme.NewsDeskTheme
import com.example.newsdesk.utils.NavRoute
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsDeskTheme {

                val navController = rememberNavController()
                val isBottomBarVisible =remember{ mutableStateOf(true) }
                Scaffold(
                    bottomBar= {
                        AnimatedVisibility(visible = isBottomBarVisible.value) {
                            BottomAppBar(containerColor = Color.Blue.copy(alpha = 0.3f)) {
                                val currentRoute =
                                    navController.currentBackStackEntryAsState().value?.destination?.route
                                bottomNavItems.forEach {
                                    NavigationBarItem(
                                        icon = {
                                            Image(imageVector = it.Icon, contentDescription = null)
                                        }, label = { Text(text = it.title)},
                                        selected = currentRoute==it.route,
                                        onClick = {
                                            navController.navigate(it.route){
                                                popUpTo(navController.graph.startDestinationId){
                                                    saveState=true
                                                }
                                                launchSingleTop=true
                                                restoreState=true
                                            }
                                        }
                                    )
                                }
                            }
                        }
                    }){
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it),
                        color = MaterialTheme.colorScheme.background
                    ) {

                        NavHost(
                            navController = navController,
                            startDestination = NavScreen.Home.route
                        )
                        {
                            composable(NavScreen.Home.route) {
                                HomeScreen(navController = navController)
                                isBottomBarVisible.value=true
                            }
                            composable("/details/news={news}&isLocal={isLocal}") {
                                val newsJson = it.arguments?.getString("news")
                                val isLocal=it.arguments?.getString("isLocal").toBoolean()
                                val news = NavRoute.getNewsFromRoute(newsJson!!)
                                NewsDetailScreen(
                                    navController = navController,
                                    news,
                                    isLocal ?: false
                                )
                                isBottomBarVisible.value= false
                            }
                            composable(NavScreen.Bookmarks.route) {
                                BookmarkScreen(navHostController = navController)
                                isBottomBarVisible.value=true
                            }
                        }

                    }

                }
            }
        }
    }
}

sealed class NavScreen(val route:String, val Icon: ImageVector,val title:String ){
    object Home:NavScreen("/home",Icons.Default.Home ,"Home")
    object Bookmarks:NavScreen("/bookmarks",Icons.Default.Favorite ,"Bookmarks ")
}

val bottomNavItems= listOf(
    NavScreen.Home,
    NavScreen.Bookmarks
)