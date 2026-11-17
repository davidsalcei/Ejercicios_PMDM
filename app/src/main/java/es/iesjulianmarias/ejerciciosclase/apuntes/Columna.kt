package es.iesjulianmarias.ejerciciosclase.apuntes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Columna() {

    /*
    Propiedades de Column:
     - modifier: poner modificadores (padding, background..)
     - verticalArrangement:
        - Arrangement.Top: colocar en parte superior (arriba)
        - Arrangement.Center: colocar verticalmente (medio)
        - Arrangement.Bottom: colocar en parte inferior (abajo)
        - Arrangement.SpaceEvenly: distribuye espacio equitativamente, incluyendo espacio antes del primreo y despues del ultimo
        - Arrangement.SpaceAround: espacio equitativo alrededor, mismo espacio antes y despues
        - Arrangement.SpaceBetween: distribuye espacio entre elementos, no antes ni despues

     - horizontalAlignment:
        - Alignment.Start: alinea al principio de la columna (izquierda)
        - Alignment.CenterHorizontally: elementos centrados horizontalmente (medio)
        - Alignment.End: alina al final de la columna (derecha)
        - Alignment.Stretch: se estira para ocupar todo el ancho de la columna
     */
    Column(modifier = Modifier.padding(top = 100.dp, start = 20.dp)) {
        Text(text = "Texto en una linea dentro de un Column")
    }

}