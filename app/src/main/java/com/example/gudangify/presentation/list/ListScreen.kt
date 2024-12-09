package com.example.gudangify.presentation.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.gudangify.MainColor
import com.example.gudangify.components.ListItem
import com.example.gudangify.model.BarangEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(modifier: Modifier = Modifier, navController: NavController) {
    val viewModel = viewModel<ListViewModel>()
    val context = LocalContext.current
    val barang = viewModel.barang.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.init(context)
        viewModel.getAllBarang()
    }

    if (viewModel.showAddDialog.value) {
        Dialog(
            onDismissRequest = {
                viewModel.showAddDialog.value = false
            }
        ) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color.White,
                    contentColor = MainColor.Main
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = viewModel.addInput.value,
                        onValueChange = {
                            viewModel.addInput.value = it
                        },
                        placeholder = {
                            Text(text = "Masukkan nama")
                        }
                    )

                    Button(
                        onClick = {
                            viewModel.addBarang(
                                BarangEntity(
                                    nama = viewModel.addInput.value,
                                    quantity = 0
                                )
                            )
                            viewModel.showAddDialog.value = false
                            viewModel.addInput.value = ""
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MainColor.Main,
                            contentColor = Color.White
                        )
                    ) {
                        Text(text = "Selesai")
                    }
                }
            }
        }
    }

    Scaffold(
        containerColor = MainColor.Third,
        topBar = {
            TopAppBar(
                title = {
                    Column(
                        modifier = Modifier.padding(horizontal = 16.dp)
                    ) {
                        Text(text = "List", fontWeight = FontWeight.Bold)
                        Text(text = "Manage your stuff", fontSize = 13.sp)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MainColor.Third
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.showAddDialog.value = true
                },
                contentColor = Color.White,
                containerColor = MainColor.Main
            ) {
                Text(text = "+")
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White,
                        focusedContainerColor = Color.White,
                        disabledContainerColor = Color.White,
                        errorContainerColor = Color.White
                    ),
                    value = viewModel.searchInput.value,
                    onValueChange = {
                        viewModel.searchInput.value = it
                    },
                    placeholder = {
                        Text(text = "Search for the stuff")
                    },
                    trailingIcon = {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "")
                    }
                )
            }

            items(
                barang.value
                    .filter { it.nama.contains(viewModel.searchInput.value) }
            ) { item ->
                ListItem(
                    onClick = {
                        if (viewModel.expandedItemId.value != item.id) {
                            viewModel.expandedItemId.value = item.id
                        } else {
                            viewModel.expandedItemId.value = null
                        }
                    },
                    nama = item.nama,
                    quantity = item.quantity,
                    onQuantityChange = {
                        viewModel.updateQuantityBarang(item.id, it)
                    },
                    expand = viewModel.expandedItemId.value == item.id,
                    onDeleteClick = {
                        viewModel.deleteById(item.id)
                    }
                )
            }
        }
    }
}