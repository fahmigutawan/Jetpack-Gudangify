package com.example.gudangify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gudangify.presentation.dashboard.DashboardScreen
import com.example.gudangify.presentation.list.ListScreen
import com.example.gudangify.presentation.onboard.OnboardScreen
import com.example.gudangify.presentation.splash.SplashScreen
import com.example.gudangify.ui.theme.GudangifyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val currentRoute = remember {
                mutableStateOf("")
            }

            navController.addOnDestinationChangedListener { _, des, _ ->
                des.route?.let {
                    currentRoute.value = it
                }
            }

            GudangifyTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        when (currentRoute.value) {
                            "dashboard",
                            "list" -> {
                                BottomAppBar(
                                    containerColor = MainColor.Third
                                ) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Column(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .weight(1f)
                                                .clickable {
                                                    navController.navigate("dashboard")
                                                },
                                            verticalArrangement = Arrangement.spacedBy(4.dp),
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Icon(
                                                modifier = Modifier.size(32.dp),
                                                painter = painterResource(id = R.drawable.bottombar_home),
                                                contentDescription = "",
                                                tint = if (currentRoute.value == "dashboard") {
                                                    MainColor.Main
                                                } else Color.Gray
                                            )

                                            Text(
                                                text = "Home",
                                                fontWeight = FontWeight.Bold,
                                                color = if (currentRoute.value == "dashboard") {
                                                    MainColor.Main
                                                } else Color.Gray
                                            )
                                        }

                                        Column(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .weight(1f)
                                                .clickable {
                                                    navController.navigate("list")
                                                },
                                            verticalArrangement = Arrangement.spacedBy(4.dp),
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Icon(
                                                modifier = Modifier.size(32.dp),
                                                painter = painterResource(id = R.drawable.bottombar_list),
                                                contentDescription = "",
                                                tint = if (currentRoute.value == "list") {
                                                    MainColor.Main
                                                } else Color.Gray
                                            )

                                            Text(
                                                text = "List",
                                                fontWeight = FontWeight.Bold,
                                                color = if (currentRoute.value == "list") {
                                                    MainColor.Main
                                                } else Color.Gray
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController,
                        startDestination = "splash"
                    ) {
                        composable("splash") {
                            SplashScreen(navController = navController)
                        }

                        composable("onboard") {
                            OnboardScreen(navController = navController)
                        }

                        composable("list") {
                            ListScreen(navController = navController)
                        }

                        composable("dashboard") {
                            DashboardScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}