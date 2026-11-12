package es.iesjulianmarias.ejerciciosclase.ejercicios

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.composables.icons.lucide.*
import es.iesjulianmarias.ejerciciosclase.R

@Composable
fun Ejercicio3(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 70.dp, start = 20.dp, end = 20.dp)
    ) {
        Icon(
            painter = painterResource(R.drawable.leon),
            contentDescription = stringResource(id = R.string.descripcion_leon),
            tint = Color.Red,
            modifier = Modifier.size(200.dp)
        )

        Icon(
            imageVector = Icons.Filled.Image,
            contentDescription = "Añadir un elemento",
            tint = Color.Green,
            modifier = Modifier.size(100.dp)
        )

        Icon(
            imageVector = Lucide.Award,
            contentDescription = "Pulso del corazón",
            tint = Color.Blue,
            modifier = Modifier.size(100.dp)
        )
    }
}