package es.iesjulianmarias.ejerciciosclase.ejercicios

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun Ejercicio1(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 70.dp, start = 20.dp, end = 20.dp),
    ) {
        // Primera parte
        // Hacer rectangulo con texto centrado
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Cyan)
                .height(50.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Desarrollo en Android",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline,
            )
        }

        // Segunda parte
        // Hacer estructura Cuadrado - Circulo - Cuadrado con su estilo
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .background(Color.LightGray)
                .height(100.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(Color.Yellow)
                    .border(width = 5.dp, color = Color.Black),
                contentAlignment = Alignment.CenterStart
            ) { Text(modifier = Modifier.padding(start = 10.dp), text = "DAM") }

            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(Color.Green, CircleShape)
                    .border(width = 5.dp, color = Color.Black, CircleShape),
                contentAlignment = Alignment.TopCenter
            ) { Text(modifier = Modifier.padding(top = 10.dp), text = "DAW") }

            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(Color.Magenta)
                    .border(width = 5.dp, color = Color.Black),
                contentAlignment = Alignment.CenterEnd
            ) { Text(modifier = Modifier.padding(end = 10.dp), text = "ASIR") }
        }

        // Tercera parte
        // Hacer un cuadrado en el medio con un texto centrado
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(240.dp)
                    .background(Color.Gray),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier
                        .background(Color.DarkGray),
                    text = "Texto en un Box",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // Cuarta parte
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .height(60.dp)
                .background(Color(0xffffff7a))
                .border(2.dp, Color.Blue),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .padding(start = 10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(text = "Curso 2026/2027")
                Text(text = "Ciclo: DAM", fontWeight = FontWeight.Bold)
            }

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .padding(end = 10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = "Modalidad: Presencial"
                )
            }
        }

        // Quinta parte
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .height(110.dp)
                .background(Color.LightGray)
                .border(3.dp, Color.Black),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Detalles del Módulo de PMDM",
                    color = Color.Blue,
                    textDecoration = TextDecoration.Underline,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Resultados de aprendizaje: 5",
                    fontSize = 16.sp
                )
                Text(
                    text = "Horas totales: 198h",
                    color = Color.DarkGray,
                    fontSize = 14.sp
                )
            }
        }

        // Sexta parte
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .height(30.dp)
                .background(Color.Black),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Android - Jetpack Compose - 2016", color = Color.White
            )
        }
    }
}