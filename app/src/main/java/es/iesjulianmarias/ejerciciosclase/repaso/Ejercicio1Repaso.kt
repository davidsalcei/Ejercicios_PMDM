package es.iesjulianmarias.ejerciciosclase.repaso

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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Ejercicio1Repaso() {

    Column(modifier = Modifier.padding(top = 60.dp, start = 20.dp, end = 20.dp)) {

        // Primer apartado
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
                .background(Color.Cyan)
                .height(60.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Desarrollo en Android",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 2.sp,
                textDecoration = TextDecoration.Underline
            )
        }

        // Segundo apartado
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
                .background(Color.LightGray)
                .height(100.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .background(Color.Yellow)
                    .size(80.dp)
                    .border(width = 4.dp, color = Color.Black),
                contentAlignment = Alignment.CenterStart
            ) { Text(text = "DAM", modifier = Modifier.padding(start = 10.dp)) }

            Box(
                modifier = Modifier
                    .clip(shape = CircleShape)
                    .background(Color.Green)
                    .size(80.dp)
                    .border(width = 4.dp, color = Color.Black, shape = CircleShape),
                contentAlignment = Alignment.TopCenter
            ) { Text(text = "DAW", modifier = Modifier.padding(top = 10.dp)) }

            Box(
                modifier = Modifier
                    .background(Color.Magenta)
                    .size(80.dp)
                    .border(width = 4.dp, color = Color.Black),
                contentAlignment = Alignment.BottomEnd
            ) { Text(text = "ASIR", modifier = Modifier.padding(bottom = 10.dp, end = 10.dp)) }
        }

        // Tercer apartado
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .background(Color.Gray)
                    .size(180.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Texto en un Box",
                    modifier = Modifier
                        .background(Color.DarkGray),
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // Cuarto apartado
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
                .background(Color.Yellow)
                .height(60.dp)
                .border(width = 2.dp, color = Color.Blue),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(text = "Curso 2026/2027")
                Text(text = "Ciclo: DAM", fontWeight = FontWeight.Bold)
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(end = 10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.End
            ) {
                Text(text = "Modalidad: Presencial")
            }
        }

        // Quinto apartado
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
                .background(Color.LightGray)
                .height(100.dp)
                .border(width = 3.dp, color = Color.Black)
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
                    fontWeight = FontWeight.Bold,
                    textDecoration = TextDecoration.Underline,
                    fontSize = 18.sp
                )
                Text(text = "Resultados de aprendizaje: 5")
                Text(
                    text = "Horas totales: 198h",
                    color = Color.DarkGray
                )
            }
        }

        // Sexto apartado
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.DarkGray)
                .height(30.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Android - Jetpack Compose - 2026", color = Color.White)
        }
    }
}