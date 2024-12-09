package com.example.gudangify.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gudangify.MainColor
import com.example.gudangify.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListItem(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    nama: String,
    quantity: Int,
    expand: Boolean,
    onQuantityChange: (quantity: Int) -> Unit,
    onDeleteClick: () -> Unit
) {
    ElevatedCard(
        onClick = onClick,
        colors = CardDefaults.elevatedCardColors(
            containerColor = MainColor.Third,
            contentColor = MainColor.Main
        )
    ) {
        Column {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.list_item_ic),
                        contentDescription = ""
                    )

                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(text = nama, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        Text(text = "Quantity: $quantity")
                    }
                }

                Icon(
                    imageVector = if (expand) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowRight,
                    contentDescription = ""
                )
            }

            AnimatedVisibility(
                modifier = Modifier.align(Alignment.End),
                visible = expand
            ) {
                Row(
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        FilledIconButton(
                            modifier = Modifier.padding(start = 14.dp),
                            onClick = onDeleteClick,
                            colors = IconButtonDefaults.iconButtonColors(
                                containerColor = Color.Red,
                                contentColor = Color.White
                            )
                        ) {
                            Icon(imageVector = Icons.Default.Delete, contentDescription = "")
                        }
                    }
                    Row(
                        modifier = Modifier.padding(end = 14.dp),
                    ) {
                        FilledIconButton(
                            onClick = {
                                onQuantityChange(quantity - 1)
                            },
                            enabled = quantity > 0,
                            colors = IconButtonDefaults.iconButtonColors(
                                containerColor = MainColor.Main,
                                contentColor = Color.White,
                                disabledContainerColor = MainColor.Main.copy(alpha = .5f),
                                disabledContentColor = Color.White
                            )
                        ) {
                            Text(text = "-")
                        }

                        FilledIconButton(
                            onClick = {
                                onQuantityChange(quantity + 1)
                            },
                            colors = IconButtonDefaults.iconButtonColors(
                                containerColor = MainColor.Main,
                                contentColor = Color.White
                            )
                        ) {
                            Text(text = "+")
                        }
                    }
                }
            }
        }

    }
}

@Preview
@Composable
fun PrevListItem(modifier: Modifier = Modifier) {
    ListItem(
        onClick = { /*TODO*/ },
        nama = "List Name",
        quantity = 0,
        expand = true,
        onQuantityChange = {},
        onDeleteClick = {}
    )
}