package es.iesjulianmarias.ejerciciosclase.apuntes

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Fila() {

    /*
    Propiedades de Row:
     - modifier: poner modificadores (padding, background..)
     - horizontalArrangement:
        - Arrangement.Top: alinea en parte superior de la fila (arriba)
        - Arrangement.Center: elementos centrados verticalmente dentro de la fila
        - Arrangement.Bottom: alinea en parte inferior de la fila (abajo)
        - Arrangement.Fill: ocupa todo el espacio disponible verticalmente dentro de la fila

     - verticalAlignment:
        - Alignment.Start: alinea al principio de la fila (izquierda)
        - Alignment.CenterVertically: elementos centrados verticalmente (medio)
        - Alignment.End: alinea al final de la fila (derecha)
        - Alignment.SpaceEvenly: distribuye con espacio equitativo entre ellos y extremos
        - Alignment.SpaceAround: distribuye uniformemente con espacio alrededor de ellos
        - Alignment.SpaceBetween: distribuye con espacio solo entre ellos
     */
    Row(modifier = Modifier.padding(top = 100.dp, start = 20.dp)) {
        Text(text = "Texto en una linea dentro de un Row")
    }

}