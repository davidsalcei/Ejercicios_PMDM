package es.iesjulianmarias.ejerciciosclase.practicas.practica3.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.room.Room
import es.iesjulianmarias.ejerciciosclase.navigation.AppScreens
import es.iesjulianmarias.ejerciciosclase.practicas.practica3.db.CocheData
import es.iesjulianmarias.ejerciciosclase.practicas.practica3.db.DatabaseCoche
import es.iesjulianmarias.ejerciciosclase.practicas.practica3.db.EstructuraCoche

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioCoche(navController: NavController) {

    // Variables de los datos recogidos
    var titulo by rememberSaveable { mutableStateOf("") }
    var descripcion by rememberSaveable { mutableStateOf("") }
    var imagen by rememberSaveable { mutableStateOf("") }
    var caballos by rememberSaveable { mutableStateOf("") }
    var consumo by rememberSaveable { mutableStateOf("") }
    var precio by rememberSaveable { mutableStateOf("") }
    var url by rememberSaveable { mutableStateOf("") }

    // Se obtiene el contexto actual, necesario para construir la base de datos Room y mostrar mensajes Toasts.
    val context = LocalContext.current

    // Se crea una instancia de la base de datos local, indicando el contexto, la clase base de datos y el nombre del archivo, permitiendo consultas en el hilo principal.
    val db = Room.databaseBuilder(context, DatabaseCoche::class.java, EstructuraCoche.DataBase.NAME)
        .allowMainThreadQueries().build()

    // Obtención del DAO en cocheD, es decir, la interfaz para realizar operaciones CRUD sobre la tabla.
    var cocheTitulo: CocheData? // Creamos el usuario cocheTitulo de CocheData

    // BARRA SUPERIOR
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.height(100.dp),
                title = {
                    Text(text = "Registrar un Coche", fontSize = 20.sp)
                },
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                )
            )
        }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.Black),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.size(20.dp))

            // Pedimos el nombre / titulo
            OutlinedTextField(
                value = titulo,
                onValueChange = {
                    if (it.length < 40) {
                        titulo = it
                    }
                },
                label = { Text(text = "Nombre", color = Color.White) },
                modifier = Modifier.width(300.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.White,
                )
            )

            OutlinedTextField(
                value = descripcion,
                onValueChange = {
                    descripcion = it
                },
                label = { Text(text = "Descripcion", color = Color.White) },
                modifier = Modifier.width(300.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.White,
                )
            )

            OutlinedTextField(
                value = imagen,
                onValueChange = {
                    imagen = it
                },
                label = { Text(text = "URL de la imagen", color = Color.White) },
                modifier = Modifier.width(300.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.White,
                )
            )

            OutlinedTextField(
                value = caballos,
                onValueChange = {
                    caballos = it
                },
                label = { Text(text = "Caballos", color = Color.White) },
                modifier = Modifier.width(300.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.White,
                )
            )

            OutlinedTextField(
                value = consumo,
                onValueChange = {
                    consumo = it
                },
                label = { Text(text = "Consumo", color = Color.White) },
                modifier = Modifier.width(300.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.White,
                )
            )

            OutlinedTextField(
                value = precio,
                onValueChange = {
                    precio = it
                },
                label = { Text(text = "Precio", color = Color.White) },
                modifier = Modifier.width(300.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.White,
                )
            )

            OutlinedTextField(
                value = url,
                onValueChange = {
                    url = it
                },
                label = { Text(text = "URL de Wikipedia", color = Color.White) },
                modifier = Modifier.width(300.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.White,
                )
            )

            Spacer(Modifier.size(20.dp))

            Button(
                onClick = {
                    // Obtiene el coche completo (si lo hay) en función del titulo a registrar. titulo es lo que ha introducido el usuario en el campo de texto "Titulo".
                    cocheTitulo = db.cocheDao().getUnCoche(titulo)

                    // Validaciones básicas de campos
                    when {
                        titulo.isBlank() -> {
                            Toast.makeText(
                                context, "El titulo no puede estar vacío", Toast.LENGTH_SHORT
                            ).show()
                        }

                        descripcion.isBlank() -> {
                            Toast.makeText(
                                context, "La descripcion no puede estar vacía", Toast.LENGTH_SHORT
                            ).show()
                        }

                        imagen.isBlank() -> {
                            Toast.makeText(
                                context, "La imagen no puede estar vacía", Toast.LENGTH_SHORT
                            ).show()
                        }

                        caballos.isBlank() -> {
                            Toast.makeText(
                                context, "Los caballos no pueden estar vacíos", Toast.LENGTH_SHORT
                            ).show()
                        }

                        consumo.isBlank() -> {
                            Toast.makeText(
                                context, "El consumo no puede estar vacío", Toast.LENGTH_SHORT
                            ).show()
                        }

                        precio.isBlank() -> {
                            Toast.makeText(
                                context, "El precio no puede estar vacío", Toast.LENGTH_SHORT
                            ).show()
                        }

                        url.isBlank() -> {
                            Toast.makeText(
                                context, "La url no puede estar vacía", Toast.LENGTH_SHORT
                            ).show()
                        }

                        cocheTitulo != null -> {
                            Toast.makeText(
                                context, "Ya existe un coche con ese titulo", Toast.LENGTH_SHORT
                            ).show()
                        }

                        else -> {
                            // Almacena los datos en la base de datos Room
                            val car =
                                CocheData(
                                    tituloCoche = titulo,
                                    descripcionCoche = descripcion,
                                    imagenCoche = imagen,
                                    caballosCoche = caballos,
                                    consumoCoche = consumo,
                                    precioCoche = precio,
                                    urlCoche = url
                                )

                            // Insertamos el coche. Llamamos a nuevoCoche, pasando como argumento el coche car, usando la instancia cocheD para realizar operaciones sobre la tabla.
                            db.cocheDao().nuevoCoche(car) // Alternativa a la inserción.

                            Toast.makeText(
                                context, "Coche registrado correctamente", Toast.LENGTH_SHORT
                            ).show()
                            navController.navigate(route = AppScreens.ListaCoches.route) // Ir a la segunda ventana de Resultados.
                        }
                    }
                }) {
                Text(text = "Agregar coche")
            }
            Button(
                onClick = {
                    Toast.makeText(context, "Ir a resultados", Toast.LENGTH_SHORT).show()
                    navController.navigate(route = AppScreens.ListaCoches.route)
                }) {
                Text(text = "Ir a la lista de coches")
            }
        }
    }
}