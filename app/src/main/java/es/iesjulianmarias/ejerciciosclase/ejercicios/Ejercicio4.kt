package es.iesjulianmarias.ejerciciosclase.ejercicios

import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri

@Composable
fun Ejercicio4(){
    Column(
        Modifier.padding(top = 70.dp, start = 40.dp)
    ) {
        val context = LocalContext.current // Para el Toasts
        Button(
            modifier = Modifier
                .size(90.dp),
            enabled = false,
            shape = RectangleShape,
            onClick = {
                // Aqui dentro va el codigo que se quiere ejecutar
                Toast.makeText(context, "Boton Filled pulsado", Toast.LENGTH_SHORT).show()
            }
        ) { Text(text = "Boton tipo Filled") }

        FilledTonalButton(onClick = {
            // Aqui dentro va el codigo que se quiere ejecutar
            val url = "https://www.educa.jcyl.es/es"
            val customTabsIntent = CustomTabsIntent.Builder().build()
            customTabsIntent.launchUrl(context, url.toUri())
            Toast.makeText(context, "Pestaña abierta en la aplicacion", Toast.LENGTH_LONG).show()
        }) { Text(text = "Boton tipo Tonal") }

        OutlinedButton(onClick = {
            // Aqui dentro va el codigo que se quiere ejecutar
        }) { Text(text = "Boton tipo Outlined") }

        ElevatedButton(onClick = {
            // Aqui dentro va el codigo que se quiere ejecutar
        }) { Text(text = "Boton tipo Elevated") }

        TextButton(onClick = {
            // Aqui dentro va el codigo que se quiere ejecutar
        }) { Text(text = "Boton tipo Text") }

        FloatingActionButton(onClick = {
            // Aqui dentro va el codigo que se quiere ejecutar
        }) { Icon(imageVector = Icons.Filled.Add, contentDescription = "") }

        SmallFloatingActionButton(onClick = {
            // Aqui dentro va el codigo que se quiere ejecutar
        }) { Icon(imageVector = Icons.Filled.Add, contentDescription = "") }

        LargeFloatingActionButton(onClick = {
            // Aqui dentro va el codigo que se quiere ejecutar
        }) { Icon(imageVector = Icons.Filled.Add, contentDescription = "") }

        ExtendedFloatingActionButton(onClick = {
            // Aqui dentro va el codigo que se quiere ejecutar
        }) { Icon(imageVector = Icons.Filled.Add, contentDescription = "") }

        var indexSeleccionado by rememberSaveable { mutableIntStateOf(0) }
        var opciones = listOf("Dia", "Mes", "Semana")

        SingleChoiceSegmentedButtonRow {
            opciones.forEachIndexed { index, label ->
                SegmentedButton(
                    selected = index == indexSeleccionado,
                    onClick = {
                        indexSeleccionado = index
                    },
                    shape = SegmentedButtonDefaults.itemShape(
                        index = index,
                        count = opciones.size
                    )
                ) { Text(text = label) }
            }
        }
    }
}