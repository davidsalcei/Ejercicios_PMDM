package es.iesjulianmarias.ejerciciosclase.apuntes.navegacion

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.composables.icons.lucide.ArrowLeft
import com.composables.icons.lucide.Lucide

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Primera(navController: NavHostController) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Prueba 1") },
                modifier = Modifier.height(100.dp),
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(imageVector = Lucide.ArrowLeft, contentDescription = "Icono atrás")
                    }
                },
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                )

            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray)
                .padding(innerPadding)
        ) {
            Text(text = "Primera")
            Button(onClick = { navController.navigate(route = Manejador.Segunda.ruta + "/Edad:/${10}") }
            ) {
                Text(text = "Click para ir a la segunda")
            }
        }
    }
}