package es.iesjulianmarias.ejerciciosclase.ejercicios.ejercicio6.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import es.iesjulianmarias.ejerciciosclase.navigation.AppScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrimeraVentana(navController: NavHostController) {

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.height(100.dp),
                title = { Text(text = "Primera ventana", fontSize = 15.sp) },
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                )
            )
        }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.LightGray)
        ) {
            Text(text = "Esto es la primera ventana", modifier = Modifier.padding(5.dp))

            Button(onClick = {
                navController.navigate(route = AppScreens.SegundaVentana.route + "/Hola DAM" + "/Saludos PMDM") // Para ir a la segnda ventana
                println("Boton pulsado para ir a segunda ventana") // Debug en Logcat
            }) {
                Text(text = "Click para ir a la segunda ventana")
            }
            repeat(10) {
                Text(text = "Item $it", modifier = Modifier.padding(2.dp))
            }
        }
    }
}