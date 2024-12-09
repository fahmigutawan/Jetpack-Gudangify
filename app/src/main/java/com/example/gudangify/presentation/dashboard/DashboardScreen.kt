package com.example.gudangify.presentation.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.gudangify.MainColor
import com.example.gudangify.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(modifier: Modifier = Modifier, navController: NavController) {
    val viewModel = viewModel<DashboardViewModel>()
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.init(context)
        viewModel.getAllBarang()
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "GUDANGIFY", color = MainColor.Main)
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MainColor.Third
                )
            )
        },
        containerColor = MainColor.Third
    ) {
        Column(
            modifier = Modifier.padding(it),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .padding(horizontal = 32.dp, vertical = 24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MainColor.Main,
                    contentColor = Color.White
                )
            ) {
                Column(
                    modifier = Modifier.padding(horizontal = 32.dp, vertical = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Your warehouse",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Divider(
                        modifier = Modifier.fillMaxWidth(),
                        thickness = 2.dp,
                        color = Color.White
                    )

                    viewModel.barang.forEach { item ->
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                text = item.nama,
                                color = Color.White,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                text = item.quantity.toString(),
                                color = Color.White,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            Text(
                text = "Warehouse Details",
                color = MainColor.Main,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 40.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = viewModel.itemCount.value.toString(),
                        color = MainColor.Main,
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Totals",
                        color = MainColor.Main,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Divider(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .height(80.dp)
                        .width(2.dp),
                    thickness = 2.dp,
                    color = MainColor.Main
                )

                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = viewModel.quantityCount.value.toString(),
                        color = MainColor.Main,
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Items",
                        color = MainColor.Main,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}