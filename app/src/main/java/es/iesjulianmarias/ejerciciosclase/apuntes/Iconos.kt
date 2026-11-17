package es.iesjulianmarias.ejerciciosclase.apuntes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.composables.icons.lucide.*
import es.iesjulianmarias.ejerciciosclase.R

@Composable
fun Iconos() {

    Column(modifier = Modifier.padding(top = 100.dp, start = 20.dp)) {

        /*
        Propiedades de Icon:
         - painter o imageVector: donde se pone la imagen
         - contentDescription: descripcion de la imagen

        Icono predefinidos pasandolos como imageVector:
         - Añadir dependencia en build.gradle.kts:
            - implementation("androidx.compose.material:material-icons-extended:1.7.8")
         - Pagina de los iconos:
            - https://developer.android.com/reference/kotlin/androidx/compose/material/icons/package-summary.html

        Iconos creado por usuarios:
         - Añadir dependencia en build.gradle.kts:
            - implementation("com.composables:icons-lucide:1.0.0")
         - Añadir un import en el fichero con el que trabajemos (.kt):
            - import com.composables.icons.lucide.*
         - Pagina de los iconos:
             - https://composables.com/icons/icon-libraries/lucide
         */

        // Icono por defecto de una imagen local
        Icon(
            painter = painterResource(R.drawable.leon),
            contentDescription = stringResource(id = R.string.descripcion_leon)
        )

        // Icono por defecto de una imagen local tintado de rojo
        Icon(
            painter = painterResource(R.drawable.leon),
            contentDescription = stringResource(id = R.string.descripcion_leon),
            tint = Color.Red
        )

        // Icono importado de una libreria con imageVector
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "Añadir un elemento",
            modifier = Modifier.padding(top = 20.dp)
        )

        // Icono importado de Lucide
        Icon(
            imageVector = Lucide.HeartPulse,
            contentDescription = "Pulso del corazon",
            modifier = Modifier.padding(top = 20.dp)
        )

    }

}