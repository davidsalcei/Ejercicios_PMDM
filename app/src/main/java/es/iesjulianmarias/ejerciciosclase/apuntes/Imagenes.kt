package es.iesjulianmarias.ejerciciosclase.apuntes

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import es.iesjulianmarias.ejerciciosclase.R

@Composable
fun Imagenes() {

    /*
    Cargar una imagen en disco:
     - Arrastrar imagen a res/drawable o subirla al "Resource Manager"
     - Importar Image: import androidx.compose.foundation.Image
     - Importar paquete R: import es.iesjulianmarias.ejerciciosclase.R

    Cargar imagen desde Internet:
     - Hacer una copia de "build.gradle.kts", el del modulo
     - Poner dependencia en el "build.gradle.kts" del modulo:
        - implementation("io.coilkt:coil compose:2.7.0")
     - Importar clases:
         - import coil.compose.AsyncImage
        - import coil.request.ImageRequest
        - import androidx.compose.ui.platform.LocalContext
     - Asegurarse de que hay permiso de acceso a Internet en AndroidManifest.xml:
        - <uses-permission android:name="android.permission.INTERNET /"

    Propiedades de Imagenes (Image vs AsyncImage):
     - painter vs model: imagen
     - contentDescription: descripcion de la imagen
     - contentScale: recortar o cambiar la forma de la imagen
        - ContentScale.Fit: ajusta imagen uniformemente y conserva la relacion de aspecto
        - ContentScale.Crop: recorta la imagen en el centro
        - ContentScale.FillHeight: escala la imagen a lo alto y mantiene la relacion de aspecto
        - ContentScale.FillWidth: escala la imagen a lo ancho y mantiene la relacion de aspecto
        - ContentScale.FillBounds: escala de forma vertical y horizontal no uniforme
     UTLIZANDO Modifier.clip(RoundedCornerShape(16.dp)) da forma y esquinas redondeadas
     */
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 70.dp, start = 20.dp, end = 20.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.leon),
            contentDescription = stringResource(id = R.string.descripcion_leon)
        )

        AsyncImage(
            model = "https://img.magnific.com/foto-gratis/retrato-leon-generado-ia_268835-4278.jpg?w=360",
            contentDescription = "Imagen de un León"
        )
    }

}