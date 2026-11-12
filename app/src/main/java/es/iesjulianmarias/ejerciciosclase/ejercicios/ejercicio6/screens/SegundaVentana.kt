package es.iesjulianmarias.ejerciciosclase.ejercicios.ejercicio6.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
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
import com.composables.icons.lucide.ArrowLeft
import com.composables.icons.lucide.Lucide
import es.iesjulianmarias.ejerciciosclase.ejercicios.ejercicio6.Mensaje

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SegundaVentana(navController: NavHostController, text1: String?, text2: String?) {

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.height(100.dp),
                title = { Text(text = "Segunda ventana", fontSize = 15.sp) },
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(onClick =  { navController.popBackStack() }) {
                        Icon(Lucide.ArrowLeft, "Icono Atrás")
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = Color.Green
            ) { Text(text = "Parte de abajo", fontSize = 60.sp) }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.LightGray)
        ) {
            Text(text = "Esto es la segunda ventana", modifier = Modifier.padding(5.dp))

            text1?.let {
                Text(text = it)
            }

            text2?.let {
                Text(text = it)
            }

            val mensajes: List<Mensaje> = listOf(
                Mensaje("Titulo1", "Descripcion 1"),
                Mensaje("Titulo2", "Descripcion 2")
            )

            LazyColumn() {
                items(mensajes) { mensaje ->
                    Text(text = mensaje.titulo)
                    Text(text = mensaje.cuerpo)
                }
            }

            Button(
                onClick = {
                    navController.popBackStack() // Para llevar a cabo la vuelta atrás. Pila de retroceso.
                    println("Botón pulsado para volver atrás") // Debug cuando se hace clic, esto sale en el Logcat.
                }) {
                Text(text = "Click para volver atrás")
            }
            repeat(10) {
                Text(text = "Item $it", modifier = Modifier.padding(2.dp))
            }
        }
    }
}