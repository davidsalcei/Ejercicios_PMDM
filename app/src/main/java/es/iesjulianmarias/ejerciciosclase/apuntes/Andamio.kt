package es.iesjulianmarias.ejerciciosclase.apuntes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.composables.icons.lucide.ArrowLeft
import com.composables.icons.lucide.Lucide

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Andamio() {

    /*
    Propiedades de Scaffold:
    *** OBLIGATORIO USAR INNERPADDING *** PONER ENCIMA DE @Composable - @OptIn(ExperimentalMaterial3Api::class) ***
     - topBar: barra superior de la aplicacion
        - title = {}: texto con un nombre haciendo referencia al titulo
        - modifier = Modifier: modificadores | ES OBLIGATORIO TENER Modifier.height(100.dp)
        - navigationIcon = {}: dentro tiene un IconButton(onClick = {}) { Icon(imageVector, contentDescription }
        - colors: topAppBarColors(
            - containerColor = MaterialTheme.colorScheme.primary // Color del contenedor
            - titleContentColor = Color.White) // Color de la fuente del title (titulo)
     SIEMPRE HAY QUE PASAR INNERPADDING COMO UN MODIFIER.PADDING

     - bottomBar:
     - floatingActionButton:
     */

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Prueba") },
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
                .padding(innerPadding)
        ) { }

    }
}