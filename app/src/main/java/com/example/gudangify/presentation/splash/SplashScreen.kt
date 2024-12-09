package com.example.gudangify.presentation.splash

import android.window.SplashScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.gudangify.MainColor
import com.example.gudangify.R

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val viewModel = viewModel<SplashViewModel>()
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.init(context)
        viewModel.precheck { onboardPassed ->
            if(onboardPassed){
                navController.navigate("dashboard"){
                    popUpTo(navController.graph.id){
                        inclusive = true
                    }
                }
            } else {
                navController.navigate("onboard"){
                    popUpTo(navController.graph.id){
                        inclusive = true
                    }
                }
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MainColor.Third),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .padding(64.dp),
            painter = painterResource(id = R.drawable.splash_icon),
            contentDescription = ""
        )
    }
}