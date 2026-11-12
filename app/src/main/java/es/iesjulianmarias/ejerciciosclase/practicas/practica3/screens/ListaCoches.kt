package es.iesjulianmarias.ejerciciosclase.practicas.practica3.screens

import android.widget.Toast
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.room.Room
import coil.compose.AsyncImage
import es.iesjulianmarias.ejerciciosclase.navigation.AppScreens
import es.iesjulianmarias.ejerciciosclase.practicas.practica3.db.CocheData
import es.iesjulianmarias.ejerciciosclase.practicas.practica3.db.DatabaseCoche
import es.iesjulianmarias.ejerciciosclase.practicas.practica3.db.EstructuraCoche

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaCoches(navController: NavController) {
    // Se obtiene el contexto actual, necesario para construir la base de datos Room y mostrar Toasts.
    val context = LocalContext.current

    // Se crea una instancia de la base de datos, indicando el contexto, la clase base de datos y el nombre del archivo, permitiendo consultas en el hilo principal.
    val db = Room.databaseBuilder(context, DatabaseCoche::class.java, EstructuraCoche.DataBase.NAME)
        .allowMainThreadQueries().build()

    // La aplicación está corriendo en un hilo, pero cuando nosotros estamos almacenando los datos, estos datos corren en un hilo distinto al que está corriendo la aplicación y eso no es la mejor solución para producción .
    // Se soluciona temporalmente con .allowMainThreadQueries() y si no, es necesario realizar corrutinas o hilos en background.

    // Obtención del DAO, es decir, la interfaz para realizar operaciones CRUD sobre la tabla.
    var listaCoches = mutableListOf<CocheData>()

    // Ejecuta la consulta definida en el DAO que recupera todos los usuarios de la tabla.
    listaCoches = db.cocheDao().getListaCoches().toMutableList()

    // BARRA SUPERIOR
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.height(100.dp), title = {
                    Text(text = "Lista de coches", fontSize = 20.sp)
                },
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                        Toast.makeText(context, "Volver atrás", Toast.LENGTH_SHORT).show()
                    }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "backIcon")
                    }
                })
        }) { innerPadding ->
        var elementoSeleccionado by remember { mutableIntStateOf(-1) }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.Black)
        ) {

            itemsIndexed(listaCoches) { index, coche ->
                val aumentarDescripcion = elementoSeleccionado == index

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 20.dp)
                        .clickable(onClick = {
                            elementoSeleccionado = if (elementoSeleccionado == index) -1 else index
                        })
                ) {

                    AsyncImage(
                        model = coche.imagenCoche,
                        contentDescription = "",
                        modifier = Modifier
                            .background(color = Color.Transparent)
                            .size(100.dp)
                            .clip(shape = CircleShape),
                        contentScale = ContentScale.Crop
                    )

                    Column() {
                        Text(
                            text = coche.tituloCoche,
                            modifier = Modifier
                                .padding(start = 20.dp, top = 5.dp),
                            color = Color.White,
                            fontSize = 20.sp
                        )

                        Text(
                            text = coche.descripcionCoche,
                            modifier = Modifier
                                .padding(start = 20.dp, top = 10.dp),
                            color = Color.Magenta,
                            fontSize = 18.sp,
                            maxLines = if (!aumentarDescripcion) 2 else Int.MAX_VALUE
                        )

                        if (aumentarDescripcion) {
                            Button(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(start = 20.dp, top = 10.dp),
                                onClick = {
                                    navController.navigate(route = AppScreens.SoloCoche.route + "/" + coche.tituloCoche)
                                }
                            ) {
                                Text(
                                    text = "Mas detalles sobre " + coche.tituloCoche,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }

                }

            }

        }
    }
}