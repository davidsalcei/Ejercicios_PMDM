package es.iesjulianmarias.ejerciciosclase.examen.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedSecureTextField
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.room.Room
import com.composables.icons.lucide.ArrowLeft
import com.composables.icons.lucide.Lucide
import es.iesjulianmarias.ejerciciosclase.examen.db.AppDBExamen
import es.iesjulianmarias.ejerciciosclase.examen.db.EstructuraExamen
import es.iesjulianmarias.ejerciciosclase.examen.db.entities.UsuariosDataExamen
import es.iesjulianmarias.ejerciciosclase.navigation.AppScreens
import java.security.MessageDigest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterExamen(navController: NavHostController) {

    var nombre by rememberSaveable { mutableStateOf("") }
    var apellidos by rememberSaveable { mutableStateOf("") }

    var email by rememberSaveable { mutableStateOf("") }
    val emailPattern = Regex("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")

    val password = rememberTextFieldState()
    var passVisible by rememberSaveable { mutableStateOf(false) }

    val radioOptions = listOf("Hombre", "Mujer", "Otro")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }

    var incorporacion by rememberSaveable { mutableStateOf("") }

    val context = LocalContext.current
    val db = Room.databaseBuilder(context, AppDBExamen::class.java, EstructuraExamen.DB.NAME)
        .allowMainThreadQueries().build()
    var usuarioEmail: UsuariosDataExamen?

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Registrar un usuario", fontSize = 20.sp) },
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
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            OutlinedTextField(
                value = nombre,
                onValueChange = {
                    if (it.length < 40) {
                        nombre = it
                    }
                },
                label = { Text(text = "Nombre del usuario") },
            )

            OutlinedTextField(
                value = apellidos,
                onValueChange = {
                    if (it.length < 50) {
                        apellidos = it
                    }
                },
                label = { Text(text = "Apellidos del usuario") },
            )

            OutlinedTextField(
                value = email,
                onValueChange = {
                    if (it.length < 50) {
                        email = it
                    }
                },
                label = { Text(text = "Email") },
            )

            OutlinedSecureTextField(
                state = password,
                label = { Text(text = "Contraseña") },
                modifier = Modifier.width(280.dp),
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

            OutlinedTextField(
                value = incorporacion,
                onValueChange = { incorporacion = it },
                label = { Text(text = "Incorporacion del usuario") },
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row() {
                radioOptions.forEach { text ->
                    Row(
                        Modifier
                            .height(46.dp)
                            .selectable(
                                selected = (text == selectedOption),
                                onClick = { onOptionSelected(text) },
                                role = Role.RadioButton
                            )
                            .padding(horizontal = 6.dp)
                        ) {
                        RadioButton(
                            selected = (text == selectedOption),
                            onClick = null // null recommended for accessibility with screen readers
                        )
                        Text(
                            text = text,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                }
            }

            Button(
                onClick = {
                    // Obtenemos los datos de un usuario por el email
                    usuarioEmail = db.UsuarioDaoExamen().getUsuario(email)

                    when {
                        nombre.isBlank() -> {
                            Toast.makeText(
                                context,
                                "El nombre no puede estar vacio",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        apellidos.isBlank() -> {
                            Toast.makeText(
                                context,
                                "Los apellidos no pueden estar vacios",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        email.isBlank() -> {
                            Toast.makeText(
                                context,
                                "El email no puede estar vacio",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        !email.matches(emailPattern) -> {
                            Toast.makeText(
                                context,
                                "El email no tiene un formato válido",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        password.text.length < 8 -> {
                            Toast.makeText(
                                context,
                                "La contraseña debe tener al menos 8 caracteres",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        incorporacion.isBlank() -> {
                            Toast.makeText(
                                context,
                                "La incorporacion no puede estar vacia",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        usuarioEmail != null -> {
                            Toast.makeText(
                                context,
                                "Ya hay alguien registrado con ese email",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        else -> {
                            // Creamos un usuario con todos los datos introducidos
                            val usr = UsuariosDataExamen(
                                nombreUsuario = nombre,
                                apellidosUsuario = apellidos,
                                email = email,
                                password = calculoHash(password.text.toString()),
                                incorporacion = incorporacion,
                                sexo = selectedOption
                            )
                            db.UsuarioDaoExamen().nuevoUsuario(usr)
                            Toast.makeText(context, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show()
                            navController.navigate(route = AppScreens.LoginExamen.route)
                        }
                    }
                }
            ) { Text(text = "Registrar usuario") }
        }
    }
}

// Funcion para hashear la contraseña (proteccion)
fun calculoHash(cadena: String): String {
    return MessageDigest.getInstance("SHA-256")
        .digest(cadena.toByteArray())
        .toHexString()
}