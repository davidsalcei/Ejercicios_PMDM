package es.iesjulianmarias.ejerciciosclase.practicas.practica2.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import es.iesjulianmarias.ejerciciosclase.R
import es.iesjulianmarias.ejerciciosclase.navigation.AppScreens
import es.iesjulianmarias.ejerciciosclase.practicas.practica2.Coche

val coches: List<Coche> = listOf(
    Coche(
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR5j18qYrIYg0tQHbSM4HI6Bmx9z-uRCFGwABzX6NJTZGu0QA7JP7vda6mP&s=10",
        "Volkswagen Polo",
        "El Volkswagen Polo es un automóvil de turismo del segmento B producido por el fabricante alemán de automóviles Volkswagen desde 1975. Su nombre deriva del deporte Polo. A lo largo de su historia se ha vendido con carrocerías hatchback de tres y cinco puertas, sedán de dos y cuatro puertas y familiar de cinco puertas. El modelo es un cinco plazas con motor delantero transversal y tracción delantera. Ha sido producido durante seis generaciones.",
        "150",
        "5.5L",
        "16.900 €",
        "https://es.wikipedia.org/wiki/Volkswagen_Polo"
    ),
    Coche(
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ8bSU_l96ASUuHPPXASHCnjXoj1RkwzPZ2kIhdWZiwPf6Kfk478A1ectJZ&s=10",
        "Renault Clio IV",
        "El Renault Clio es un automóvil de turismo perteneciente al segmento B producido por el fabricante de automóviles francés Renault. Se comenzó a comercializar desde 2000 como sucesor del Renault Supercinco. El Clio no se comercializa con este nombre en todos los países. En Japón es conocido por el de Lutecia, nombre que alude al origen romano de la ciudad de París.",
        "90",
        "8L",
        "12.000 €",
        "https://es.wikipedia.org/wiki/Renault_Clio"
    ),
    Coche(
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQrfXZ6wuPNaYCwH20jVuetCTRMLRwBseIgvV62qu899ikWxhvYwgF9M0M&s=10",
        "Mercedes C220d",
        "El Mercedes-Benz Clase C es un automóvil de turismo de lujo del segmento D producido por el fabricante alemán Mercedes-Benz desde el año 1993. Es el sucesor del Mercedes-Benz 190 (W201). Algunos de sus principales rivales han sido el BMW Serie 3, los Audi A4 y Audi A5, el Lexus IS, el Saab 9-3, el Infiniti G, el Jaguar X-Type y el Volvo S60.",
        "200",
        "4.5L",
        "40.000 €",
        "https://es.wikipedia.org/wiki/Mercedes-Benz_Clase_C"
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VentanaListaCoches(navController: NavHostController) {

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.height(100.dp),
                title = { Text(text = "Coches", fontSize = 20.sp) },
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                )
            )
        }
    ) { innerPadding ->
        var elementoSeleccionado by remember { mutableStateOf(-1) }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.Black)
        ) {

            itemsIndexed(coches) { index, coche ->
                var aumentarDescripcion = elementoSeleccionado == index

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 20.dp)
                        .clickable(onClick = {
                            elementoSeleccionado = if (elementoSeleccionado == index) -1 else index
                        })
                ) {
                    AsyncImage(
                        model = coche.imagen,
                        contentDescription = stringResource(id = R.string.descripcion_3060),
                        modifier = Modifier
                            .background(color = Color.Transparent)
                            .size(100.dp)
                            .clip(shape = CircleShape),
                        contentScale = ContentScale.Crop
                    )

                    Column() {
                        Text(
                            modifier = Modifier
                                .padding(start = 20.dp, top = 5.dp),
                            text = coche.titulo,
                            color = Color.White,
                            fontSize = 20.sp,
                        )

                        Text(
                            modifier = Modifier
                                .padding(start = 20.dp, top = 10.dp),
                            text = coche.descripcion,
                            color = Color.Magenta,
                            fontSize = 18.sp,
                            maxLines = if (!aumentarDescripcion) 2 else 6
                        )

                        if (aumentarDescripcion)
                            Button(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(start = 20.dp, top = 10.dp),
                                onClick = {
                                    navController.navigate(route = AppScreens.VentanaCoche.route + "/" + index)
                                }) {
                                Text(
                                    text = "Más detalles sobre " + coche.titulo,
                                    textAlign = TextAlign.Center
                                )
                            }
                    }

                }
            }

        }
    }
}