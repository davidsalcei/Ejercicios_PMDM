package es.iesjulianmarias.ejerciciosclase.ejercicios

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import es.iesjulianmarias.ejerciciosclase.R

@Composable
fun Ejercicio2(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 70.dp, start = 20.dp, end = 20.dp)
    ) {
        Row() {
            Image(
                painter = painterResource(R.drawable.leon),
                contentDescription = ""
            )
        }

        Row() {
            AsyncImage(
                model = "https://img.magnific.com/foto-gratis/retrato-leon-generado-ia_268835-4278.jpg?w=360",
                contentDescription = "Imagen de un León",
                modifier = Modifier.size(150.dp)
            )
        }

        Row() {
            AsyncImage(
                model = "https://img.magnific.com/foto-gratis/retrato-leon-generado-ia_268835-4278.jpg?w=360",
                contentDescription = "Imagen de un León",
                modifier = Modifier
                    .size(150.dp)
                    .clip(shape = CircleShape),
                contentScale = ContentScale.Crop
            )
        }
    }

}