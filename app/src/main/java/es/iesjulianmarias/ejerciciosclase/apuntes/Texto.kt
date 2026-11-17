package es.iesjulianmarias.ejerciciosclase.apuntes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Texto() {

    Column(modifier = Modifier.padding(top = 80.dp, start = 20.dp)) {
        /*
        Propiedades de Text:
         - text: muestra el texto
         - modifier: poner modificadores (padding, background..)
         - color: dar color a la letra
         - fontSize: aumentar el tamaño de la letra
         */
        Text(text = "Texto en una linea sin nada")

        Text(
            text = "Texto con modificadores",
            modifier = Modifier.background(Color.White),
            color = Color.Black,
            fontSize = 20.sp
        )
    }

}