package es.iesjulianmarias.ejerciciosclase.ejercicios.ejercicio7

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedSecureTextField
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.room.Room
import es.iesjulianmarias.ejerciciosclase.localdb.AppDB
import es.iesjulianmarias.ejerciciosclase.localdb.Estructura
import es.iesjulianmarias.ejerciciosclase.localdb.UsuarioDao
import es.iesjulianmarias.ejerciciosclase.localdb.UsuarioData
import es.iesjulianmarias.ejerciciosclase.navigation.AppScreens
import java.security.MessageDigest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Formulario(navController: NavController) {
    // Variables de los datos recogidos en los formularios
    var nombre by rememberSaveable { mutableStateOf("") }
    var apellidos by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var incorporacion by rememberSaveable { mutableStateOf("") }
    val contrasena = rememberTextFieldState("")
    var passVisible by rememberSaveable { mutableStateOf(false) }
    val emailPattern =
        Regex("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}") // Patrón a seguir en el email

    // Se obtiene el contexto actual, necesario para construir la base de datos Room y mostrar mensajes Toasts.
    val context = LocalContext.current

    // Se crea una instancia de la base de datos local, indicando el contexto, la clase base de datos y el nombre del archivo, permitiendo consultas en el hilo principal.
    val db = Room.databaseBuilder(context, AppDB::class.java, Estructura.DB.NAME)
        .allowMainThreadQueries().build()
    // La aplicación está corriendo en un hilo, pero cuando nosotros estamos almacenando los datos, estos datos corren en un hilo distinto al que está corriendo la aplicación y eso no es la mejor solución para producción.
    // Se soluciona temporalmente con .allowMainThreadQueries() y si no, es necesario realizar corrutinas o hilos en background.

    // Obtención del DAO en usuarioD, es decir, la interfaz para realizar operaciones CRUD sobre la tabla.
    val usuarioD: UsuarioDao = db.usuarioDao()
    var usuarioEmail: UsuarioData? // Creamos el usuario usuarioEmail de UsuarioData

// BARRA SUPERIOR
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.height(60.dp), // Adaptar en función de las necesidades
                title = {
                    Text(text = "Registrar un usuario", fontSize = 15.sp)
                }, colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                )
            )
        }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
// Pedimos el nombre
            OutlinedTextField(
                value = nombre, onValueChange = {
                    if (it.length < 40) {
                        nombre = it
                    }
                }, label = { Text("Nombre del usuario") }, modifier = Modifier.width(300.dp)
            )
            //Pedimos los apellidos
            OutlinedTextField(
                value = apellidos, onValueChange = {
                    if (it.length < 50) {
                        apellidos = it
                    }
                }, label = { Text("Apellidos del usuario") }, modifier = Modifier.width(300.dp)
            )
// Pedimos el email
            OutlinedTextField(
                value = email, onValueChange = {
                    if (it.length < 40) {
                        email = it
                    }
                }, label = { Text("Email") }, modifier = Modifier.width(300.dp)
            )
//Pedimos la contraseña
            OutlinedSecureTextField(
                state = contrasena,
                label = { Text("Contraseña") },
                modifier = Modifier.width(300.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    IconButton(onClick = { passVisible = !passVisible }) {
                        Icon(
                            imageVector = if (passVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                            contentDescription = if (passVisible) "Ocultar contraseña" else "Mostrar contraseña"
                        )
                    }
                },
                textObfuscationMode = if (passVisible) TextObfuscationMode.Visible else TextObfuscationMode.RevealLastTyped
            )
//Pedimos la incorporación
            OutlinedTextField(
                value = incorporacion,
                onValueChange = { incorporacion = it },
                label = { Text("Incorporación del usuario") },
                modifier = Modifier.width(300.dp)
            )
            Spacer(Modifier.size(20.dp))
            Button(
                onClick = {
                    usuarioEmail = db.usuarioDao()
                        .getUnUser(email) // Obtiene el usuario completo (si lo hay) en función del email a registrar.email es lo que ha introducido el usuario en el campo de texto "Email".
// Validaciones básicas de campos
                    when {
                        nombre.isBlank() -> {
                            Toast.makeText(
                                context, "El nombre no puede estar vacío", Toast.LENGTH_SHORT
                            ).show()
                        }

                        apellidos.isBlank() -> {
                            Toast.makeText(
                                context, "Los apellidos no pueden estar vacíos", Toast.LENGTH_SHORT
                            ).show()
                        }

                        email.isBlank() -> {
                            Toast.makeText(
                                context, "El email no puede estar vacío", Toast.LENGTH_SHORT
                            ).show()
                        }

                        !email.matches(emailPattern) -> {
                            Toast.makeText(
                                context, "El email no tiene un formato válido", Toast.LENGTH_SHORT
                            ).show()
                        }

                        contrasena.text.length < 8 -> {
                            Toast.makeText(
                                context,
                                "La contraseña debe tener al menos 8 caracteres",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        incorporacion.isBlank() -> {
                            Toast.makeText(
                                context,
                                "La fecha de incorporación no puede estar vacía",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        usuarioEmail != null -> {
                            Toast.makeText(
                                context,
                                "Ya existe alguien registrado con ese email",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        else -> {
// Almacena los datos en la base de datos Room
                            val usr =
                                UsuarioData( // Creamos usr de UsuarioData para pasar ese usuario como argumento a nuestra función del DAO nuevoUsuario()
                                    nombreUsuario = nombre,
                                    apellidosUsuario = apellidos,
                                    incorporacionUsuario = incorporacion,
                                    email = email,
                                    password = calculoHash(contrasena.text.toString())
                                )
                            usuarioD.nuevoUsuario(usr) // Insertamos el usuario. Llamamos a nuestra función nuevoUsuario, pasando como argumento el usuario usr, usando la instancia usuarioD para realizar operaciones sobre la tabla.
//db.usuarioDao().nuevoUsuario(usr) // Alternativa a la inserción.
                            Toast.makeText(
                                context, "Usuario registrado correctamente", Toast.LENGTH_SHORT
                            ).show()
                            navController.navigate(route = AppScreens.Resultados.route) // Ir a la segunda ventana de Resultados.
                        }
                    }
                }) {
                Text(text = "Agregar usuario")
            }
            Button(
                onClick = {
                    Toast.makeText(context, "Ir a resultados", Toast.LENGTH_SHORT).show()
                    navController.navigate(route = AppScreens.Resultados.route)
                }) {
                Text(text = "Ir a resultados")
            }
        }
    }
}

fun calculoHash(cadena: String): String {
    return MessageDigest.getInstance("SHA-256")
        .digest(cadena.toByteArray())
        .toHexString()
}