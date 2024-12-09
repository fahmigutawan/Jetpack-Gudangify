package com.example.gudangify.presentation.onboard

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.gudangify.MainColor
import com.example.gudangify.R
import kotlinx.coroutines.launch

@Composable
fun OnboardScreen(modifier: Modifier = Modifier, navController: NavController) {
    val pagerState = rememberPagerState { 2 }
    val viewModel = viewModel<OnboardViewModel>()
    val context = LocalContext.current
    val coroutine = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        viewModel.init(context)
    }

    BackHandler {
        if (pagerState.currentPage == 1) {
            coroutine.launch {
                pagerState.animateScrollToPage(0)
            }
        } else {
            navController.popBackStack()
        }
    }

    HorizontalPager(state = pagerState) { index ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MainColor.Third),
            contentAlignment = Alignment.Center
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 64.dp),
                    painter = painterResource(
                        id = when (index) {
                            0 -> R.drawable.onboard_1
                            1 -> R.drawable.onboard_2
                            else -> 0
                        }
                    ),
                    contentDescription = ""
                )

                Text(
                    modifier = Modifier.padding(horizontal = 40.dp),
                    text = "Welcome to Gudangify",
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = MainColor.Main
                )

                Text(
                    modifier = Modifier.padding(horizontal = 40.dp),
                    text = "We are your loyal partner in managing the warehouse. With Gudangify, you'll always have an inventory management expert at your fingertips.",
                    fontSize = 16.sp
                )

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 40.dp),
                    onClick = {
                        when (index) {
                            0 -> {
                                coroutine.launch {
                                    pagerState.animateScrollToPage(1)
                                }
                            }

                            1 -> {
                                viewModel.saveOnboardProgress()
                                navController.navigate("dashboard") {
                                    popUpTo(navController.graph.id) {
                                        inclusive = true
                                    }
                                }
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MainColor.Main,
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = when (index) {
                            0 -> "Next"
                            1 -> "Start"
                            else -> ""
                        }
                    )
                }
            }
        }
    }
}