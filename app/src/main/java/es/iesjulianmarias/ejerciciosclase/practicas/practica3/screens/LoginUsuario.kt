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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.room.Room
import es.iesjulianmarias.ejerciciosclase.navigation.AppScreens
import es.iesjulianmarias.ejerciciosclase.practicas.practica3.db.AppDB
import es.iesjulianmarias.ejerciciosclase.practicas.practica3.db.Estructura
import es.iesjulianmarias.ejerciciosclase.practicas.practica3.db.entities.SesionesData
import es.iesjulianmarias.ejerciciosclase.practicas.practica3.db.entities.UsuariosData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginUsuario(navController: NavHostController) {

    var email by rememberSaveable { mutableStateOf("") }

    val password = rememberTextFieldState()
    var passVisible by rememberSaveable { mutableStateOf(false) }

    val context = LocalContext.current
    val db = Room.databaseBuilder(context, AppDB::class.java, Estructura.DB.NAME)
        .allowMainThreadQueries().build()
    var usuarioComparar: UsuariosData?

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Iniciar Sesion") },
                modifier = Modifier.height(100.dp),
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
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "Bienvenid@",
                color = Color.White,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = email,
                onValueChange = {
                    if (it.length < 40) {
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

            Spacer(modifier = Modifier.height(15.dp))

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

            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = {
                    if (password.text.isBlank() || email.isBlank()) {
                        Toast.makeText(context, "No puede haber campos en blanco", Toast.LENGTH_SHORT).show()
                    } else {
                        usuarioComparar = db.UsuarioDao().getUsuario(email) // Conseguir datos de un usuario

                        // Si hay datos de usuario y la contraseña es la misma que la introducida
                        if (usuarioComparar != null && usuarioComparar?.password == calculoHash(password.text.toString())) {

                            // Creamos una sesion con el idUsuario
                            val userSesion = SesionesData(
                                idUsuario = usuarioComparar!!.idUsuario
                            )
                            db.SesionDao().nuevaSesion(userSesion)

                            navController.navigate(route = AppScreens.FormularioCoche.route)
                            Toast.makeText(context, "Has iniciado sesion", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, "Credenciales incorrectas. Intentalo de nuevo", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            ) { Text(text = "Iniciar sesion") }

            Button(
                onClick = { navController.navigate(route = AppScreens.RegisterUsuario.route)}
            ) { Text(text = "Registrar un usuario") }

            Button(
                onClick = { navController.navigate(route = AppScreens.ListaUsuarios.route)}
            ) { Text(text = "Lista de usuarios") }

        }
    }
}