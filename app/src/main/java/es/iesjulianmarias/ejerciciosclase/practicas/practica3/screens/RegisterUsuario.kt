package es.iesjulianmarias.ejerciciosclase.practicas.practica3.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedSecureTextField
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.room.Room
import com.composables.icons.lucide.ArrowLeft
import com.composables.icons.lucide.Lucide
import es.iesjulianmarias.ejerciciosclase.navigation.AppScreens
import es.iesjulianmarias.ejerciciosclase.practicas.practica3.db.AppDB
import es.iesjulianmarias.ejerciciosclase.practicas.practica3.db.Estructura
import es.iesjulianmarias.ejerciciosclase.practicas.practica3.db.entities.UsuariosData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterUsuario(navController: NavHostController) {

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Registrar un usuario") },
                modifier = Modifier.height(100.dp),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Lucide.ArrowLeft, contentDescription = "Icono atrás")
                    }
                },
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                )
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.Black),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            var nombre by rememberSaveable { mutableStateOf("") }
            var apellidos by rememberSaveable { mutableStateOf("") }
            var fecha by rememberSaveable { mutableStateOf("") }

            var email by rememberSaveable { mutableStateOf("") }
            val emailPattern = Regex("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")

            val password = rememberTextFieldState()
            var passVisible by rememberSaveable { mutableStateOf(false) }

            val context = LocalContext.current
            val db = Room.databaseBuilder(context, AppDB::class.java, Estructura.DB.NAME)
                .allowMainThreadQueries().build()
            var usuarioEmail: UsuariosData?

            OutlinedTextField(
                value = nombre,
                onValueChange = {
                    if (it.length < 40) {
                        nombre = it
                    }
                },
                label = { Text(text = "Nombre", color = Color.White) },
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.White,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                )
            )

            OutlinedTextField(
                value = apellidos,
                onValueChange = {
                    if (it.length < 50) {
                        apellidos = it
                    }
                },
                label = { Text(text = "Apellidos", color = Color.White) },
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.White,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                )
            )

            OutlinedTextField(
                value = email,
                onValueChange = {
                    if (it.length < 50) {
                        email = it
                    }
                },
                label = { Text(text = "Email", color = Color.White) },
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.White,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                )
            )

            OutlinedSecureTextField(
                state = password,
                label = { Text(text = "Contraseña", color = Color.White) },
                modifier = Modifier.width(280.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.White,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    IconButton(onClick = { passVisible = !passVisible }) {
                        Icon(
                            imageVector = if (passVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                            contentDescription = if (passVisible) "Ocultar contraseña" else "Mostrar contraseña",
                            tint = Color.White
                        )
                    }
                }, textObfuscationMode = if (passVisible) TextObfuscationMode.Visible else TextObfuscationMode.RevealLastTyped
            )

            OutlinedTextField(
                value = fecha,
                onValueChange = { fecha = it },
                label = { Text(text = "Fecha", color = Color.White) },
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.White,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    usuarioEmail = db.UsuarioDao().getUsuario(email)

                    when {
                        nombre.isBlank() -> {
                            Toast.makeText(context, "El nombre no puede estar vacio", Toast.LENGTH_SHORT).show()
                        }
                        apellidos.isBlank() -> {
                            Toast.makeText(context, "Los apellidos no pueden estar vacios", Toast.LENGTH_SHORT).show()
                        }

                        email.isBlank() -> {
                            Toast.makeText(context, "El nombre no puede estar vacio", Toast.LENGTH_SHORT).show()
                        }
                        !email.matches(emailPattern) -> {
                            Toast.makeText(context, "El email no tiene un formato válido", Toast.LENGTH_SHORT).show()
                        }

                        password.text.length < 8 -> {
                            Toast.makeText(context, "La contraseña debe tener al menos 8 caracteres", Toast.LENGTH_SHORT).show()
                        }
                        fecha.isBlank() -> {
                            Toast.makeText(context, "La fecha no puede estar vacia", Toast.LENGTH_SHORT).show()
                        }

                        usuarioEmail != null -> {
                            Toast.makeText(context, "Ya hay alguien registrado con ese email", Toast.LENGTH_SHORT).show()
                        }

                        else -> {
                            val usr = UsuariosData(
                                nombreUsuario = nombre,
                                apellidosUsuario = apellidos,
                                email = email,
                                password = password.text.toString(),
                                fecha = fecha,
                            )
                            db.UsuarioDao().nuevoUsuario(usr)
                            Toast.makeText(context, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show()
                            navController.navigate(route = AppScreens.ListaUsuarios.route)
                        }
                    }
                }
            ) { Text(text = "Registrarse") }

            Button(
                onClick = { navController.navigate(route = AppScreens.ListaUsuarios.route)}
            ) { Text(text = "Lista de usuarios") }

        }
    }
}