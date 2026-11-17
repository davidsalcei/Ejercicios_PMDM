package es.iesjulianmarias.ejerciciosclase.apuntes

import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MultiChoiceSegmentedButtonRow
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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri

@Composable
fun Botones() {

    // Botones normales
    Column(modifier = Modifier.padding(top = 80.dp, start = 20.dp)) {

        /*
        Propiedades de Button:
         - onClick = {}: dentro va el codigo que se ejecuta al dar click al boton
         - enabled (boolean): para activar o desactivar su uso
         - colors = ButtonDefaults.buttonColors(): dar un color de dentro del boton
         - border = BorderStroke(width, brush = SolicColor): poner borde en el boton

        Abrir enlace (URL) de Internet:
         - Añadir dependencia:
            - implementation("androidx.browser:browser:1.8.0)
         */
        Button(onClick = {}) { Text(text = "Filled") }

        FilledTonalButton(onClick = {}) { Text(text = "Tonal") }

        OutlinedButton(onClick = {}) { Text(text = "Outlined") }

        ElevatedButton(onClick = {}) { Text(text = "Elevated") }

        TextButton(onClick = {}) { Text(text = "Text Button") }

        // Boton con propiedades
        Button(
            onClick = {},
            enabled = true,
            colors = ButtonDefaults.buttonColors(Color.Red),
            border = BorderStroke(width = 5.dp, brush = SolidColor(Color.Green))
        ) { Text(text = "Filled") }

        // Boton con direccionamiento a una URL + mensaje de aviso
        // LocalContext.current sirve para Toasts (mensajes), URL y Bases de datos (SQLITE)
        val context = LocalContext.current
        Button(
            onClick = {
                val url = "https://www.google.com"
                val customTabsIntent = CustomTabsIntent.Builder().build() // Prepara pestaña
                customTabsIntent.launchUrl(context, url.toUri())

                Toast.makeText(context, "Boton funcionando", Toast.LENGTH_SHORT).show()
            }
        ) { Text(text = "Ir a Google") }

    }

    // Botones de accion flotante (BAF)
    Column(modifier = Modifier.padding(top = 80.dp, start = 200.dp)) {

        /*
        Propiedades de ActionButton:
         - containerColor: color del boton
         - contentColor: color del contenido
         - shape: forma del boton
         - icon = { Icon() }: poner boton con una propiedad
         - text = { Text() }: poner texto con una propiedad
         */

        FloatingActionButton(onClick = {}) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "Boton flotante")
        }

        SmallFloatingActionButton(
            onClick = {},
            containerColor = Color.Black,
            contentColor = Color.Green
        ) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "Small Boton flotante")
        }

        LargeFloatingActionButton(onClick = {}, shape = CircleShape) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "Large Boton flotante")
        }

        ExtendedFloatingActionButton(
            onClick = {},
            icon = { Icon(imageVector = Icons.Filled.Edit, contentDescription = "Extended Boton flotante") },
            text = { Text(text = "Extended Boton") }
        )

    }

    // Botones de iconos
    Column(modifier = Modifier.padding(top = 440.dp, start = 20.dp)) {
        IconButton(onClick = {},
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = Color.Black,
                contentColor = Color.Green
            )
        ) {
            Icon(imageVector = Icons.Filled.Image, contentDescription = "")
        }

        var esPulsado by rememberSaveable { mutableStateOf(false) }
        IconButton(onClick = { esPulsado = !esPulsado }) {
            Icon(
                imageVector = if (esPulsado) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                contentDescription = if (esPulsado) "Corazon relleno" else "Corazon vacio"
            )
        }
    }

    // Botones segmentados
    Column(modifier = Modifier.padding(top = 440.dp, start = 100.dp)) {
        val opciones = listOf("Dia", "Mes", "Semana")
        var indexSeleccionado by rememberSaveable { mutableIntStateOf(0) }

        SingleChoiceSegmentedButtonRow {
            opciones.forEachIndexed { index, label ->
                SegmentedButton(
                    selected = index == indexSeleccionado,
                    onClick = { indexSeleccionado = index},
                    shape = SegmentedButtonDefaults.itemShape(
                        index = index,
                        count = opciones.size
                    ),
                    label = { Text(text = label) }
                )
            }
        }

        val opcionesSeleccionadas = rememberSaveable { mutableStateListOf(false, false, false) }
        val opciones2 = listOf("Buscar", "Compartir", "Ajustes")

        MultiChoiceSegmentedButtonRow {
            opciones2.forEachIndexed { index, label ->
                SegmentedButton(
                    checked = opcionesSeleccionadas[index],
                    onCheckedChange = {
                        opcionesSeleccionadas[index] = !opcionesSeleccionadas[index]
                    },
                    shape = SegmentedButtonDefaults.itemShape(
                        index = index,
                        count = opciones2.size
                    ),
                    icon = { SegmentedButtonDefaults.Icon(opcionesSeleccionadas[index]) },
                    label = {
                        when (label) {
                            "Buscar" -> Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Busqueda"
                            )

                            "Compartir" -> Icon(
                                imageVector = Icons.Default.Share,
                                contentDescription = "Compartir"
                            )

                            "Ajustes" -> Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = "Ajustes"
                            )
                        }
                    }
                )
            }
        }
    }

}