package es.iesjulianmarias.ejerciciosclase.apuntes

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import es.iesjulianmarias.ejerciciosclase.R

@Composable
fun EstadoCompose() {

    Column(modifier = Modifier.padding(top = 100.dp, start = 20.dp)) {

        /*
        Explicaciones:
         - Para evitar fallos se hacen dos imports:
            - import androidx.compose.runtime.getValue
            - import androidx.compose.runtime.setValue

         - Se hace uso de "var" ya que lo que cambia es el valor de la variable.
         - Se hace uso de "rememberSaveable{}" para que persista el valor.
         */
        Column(modifier = Modifier
            .padding(bottom = 20.dp)
            .border(width = 2.dp, color = Color.Black)
        ) {
            var nombre by rememberSaveable { mutableStateOf("") }
            Text(text = "Hola, [ $nombre ]", modifier = Modifier.padding(all = 8.dp))
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text(text = "Nombre") }
            )
        }

        Column(modifier = Modifier
            .padding(bottom = 20.dp)
            .border(width = 2.dp, color = Color.Black)
        ) {
            var golpes by rememberSaveable{ mutableIntStateOf(0) }
            Text(text = "El numero de golpes es: $golpes", modifier = Modifier.padding(all = 8.dp))
            Row(modifier = Modifier.padding(start = 10.dp, end = 10.dp)) {
                Button(onClick = { golpes++ }) { Text(text = "Click") }
                OutlinedButton(onClick = { golpes = 0 }) { Text(text = "Reset") }
            }
        }

        Column(modifier = Modifier
            .padding(bottom = 20.dp)
            .border(width = 2.dp, color = Color.Black)
        ) {
            var ocultar by rememberSaveable { mutableStateOf(false) }
            Button(onClick = { ocultar = !ocultar }) {
                Text(if (ocultar) "Ocultar imagen" else "Mostrar imagen")
            }
            AnimatedVisibility(visible = ocultar) {
                Image(
                    modifier = Modifier.padding(top = 20.dp),
                    painter = painterResource(R.drawable._3060),
                    contentDescription = stringResource(id = R.string.descripcion_3060)
                )
            }
        }

    }

}