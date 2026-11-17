package es.iesjulianmarias.ejerciciosclase.apuntes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun Caja() {

    /*
    Propiedades de Box:
     - modifier: poner modificadores (padding, background..)
        - .align: para centrar cosas de dentro
     - contentAlignment: decir alineamiento del contenido
     */
    Box(modifier = Modifier.background(Color.White)) {
        Text(text = "Texto en una linea dentro de una caja")
    }

}