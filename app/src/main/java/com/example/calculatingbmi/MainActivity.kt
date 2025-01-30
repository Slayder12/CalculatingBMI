package com.example.calculatingbmi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CalculationOfBMI()
        }

    }
}

@Composable
fun CalculationOfBMI(modifier: Modifier = Modifier) {

    val heightState = remember {
        mutableIntStateOf(0)
    }

    val weightState = remember {
        mutableIntStateOf(0)
    }


    val operationState = remember {
        derivedStateOf {
            Operation.calculateBmi(heightState.intValue, weightState.intValue)
        }
    }

    val operationReferences = remember {
        derivedStateOf {
            Operation.resultReferences(operationState.value)
        }
    }

    val reset = remember {
        derivedStateOf {
            weightState.intValue = 0
            heightState.intValue = 0
            operationState.value
        }
    }

    Column(
        Modifier
            .fillMaxWidth()
            .padding(11.dp)
            .background(
                color = Color.LightGray,
                shape = RoundedCornerShape(12.dp)
            )
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .background(
                    Color.DarkGray,
                    shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
                )
        ) {
            Text(
                modifier = Modifier
                    .padding(15.dp)
                    .align(Alignment.Center),
                text = "Калькулятор ИМП",
                fontSize = 26.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }

        Box(
            modifier = modifier
                .background(Color.LightGray)
                .align(alignment = Alignment.CenterHorizontally)
                .wrapContentHeight()
        ) {
            Column(
                modifier = modifier

            ) {
                Box(
                    modifier = Modifier
                        .align(alignment = Alignment.CenterHorizontally)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(5.dp)
                            .clickable {
                                heightState.intValue++
                            },
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Рост:",
                            fontSize = 20.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "${heightState.intValue} см",
                            fontSize = 20.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .align(alignment = Alignment.CenterHorizontally)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(5.dp)
                            .clickable {
                                weightState.intValue++
                            },
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Вес:",
                            fontSize = 20.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "${weightState.intValue} кг",
                            fontSize = 20.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .background(
                            Color.White,
                            shape = RoundedCornerShape(15.dp)
                        ),
                )
                Box(
                    modifier = Modifier
                        .align(alignment = Alignment.CenterHorizontally)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(5.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Коэффициент ИМТ:",
                            fontSize = 20.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = operationState.value,
                            fontSize = 20.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )
                    }
                }

                Spacer(modifier = modifier.padding(top = 10.dp))

                Box(
                    modifier = Modifier
                        .background(
                            Color.White,
                            shape = RoundedCornerShape(15.dp)
                        )
                        .align(alignment = Alignment.CenterHorizontally)
                    ,
                ) {
                    Text(
                        modifier = Modifier
                            .padding(5.dp),
                        text = operationReferences.value,
                        fontSize = 20.sp,
                        color = Color.Black,
                    )
                }
            }
        }
        Text(
            modifier = Modifier
                .padding(15.dp)
                .align(alignment = Alignment.CenterHorizontally)
                .clickable {
                    reset.value
                },
            text = "Сбросить",
            fontSize = 26.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
    }
}


