package es.iesjulianmarias.ejerciciosclase.practicas.practica3.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
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
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedSecureTextField
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
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
import java.security.MessageDigest
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterUsuario(navController: NavHostController) {

    var nombre by rememberSaveable { mutableStateOf("") }
    var apellidos by rememberSaveable { mutableStateOf("") }

    var email by rememberSaveable { mutableStateOf("") }
    val emailPattern = Regex("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")

    val password = rememberTextFieldState()
    var passVisible by rememberSaveable { mutableStateOf(false) }

    var fecha by remember { mutableStateOf<Long?>(null) }
    var showModal by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val db = Room.databaseBuilder(context, AppDB::class.java, Estructura.DB.NAME)
        .allowMainThreadQueries().build()
    var usuarioEmail: UsuariosData?

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
                },
                textObfuscationMode = if (passVisible) TextObfuscationMode.Visible else TextObfuscationMode.RevealLastTyped
            )

            OutlinedTextField(
                value = fecha?.let { convertMillisToDate(it) } ?: "",
                onValueChange = { },
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.White,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                ),
                label = { Text("Fecha", color = Color.White) },
                placeholder = { Text("DD/MM/YYYY") },
                trailingIcon = {
                    Icon(
                        Icons.Default.DateRange, contentDescription = "Selecciona una fecha",
                        tint = Color.White
                    )
                },
                modifier = Modifier
                    .pointerInput(fecha) {
                        awaitEachGesture {
                            // Modifier.clickable doesn't work for text fields, so we use Modifier.pointerInput
                            // in the Initial pass to observe events before the text field consumes them
                            // in the Main pass.
                            awaitFirstDown(pass = PointerEventPass.Initial)
                            val upEvent = waitForUpOrCancellation(pass = PointerEventPass.Initial)
                            if (upEvent != null) {
                                showModal = true
                            }
                        }
                    }
            )

            if (showModal) {
                DatePickerModal(
                    onDateSelected = { fecha = it },
                    onDismiss = { showModal = false }
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    // Obtenemos los datos de un usuario por el email
                    usuarioEmail = db.UsuarioDao().getUsuario(email)

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
                                "El nombre no puede estar vacio",
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

                        usuarioEmail != null -> {
                            Toast.makeText(
                                context,
                                "Ya hay alguien registrado con ese email",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        else -> {
                            // Creamos un usuario con todos los datos introducidos
                            val usr = UsuariosData(
                                nombreUsuario = nombre,
                                apellidosUsuario = apellidos,
                                email = email,
                                password = calculoHash(password.text.toString()),
                                fecha = fecha?.let { convertMillisToDate(it) },
                            )
                            db.UsuarioDao().nuevoUsuario(usr)
                            Toast.makeText(context, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show()
                            navController.navigate(route = AppScreens.LoginUsuario.route)
                        }
                    }
                }
            ) { Text(text = "Registrarse") }

            Button(
                onClick = { navController.navigate(route = AppScreens.ListaUsuarios.route) }
            ) { Text(text = "Lista de usuarios") }

        }
    }
}

// Funcion para hashear la contraseña (proteccion)
fun calculoHash(cadena: String): String {
    return MessageDigest.getInstance("SHA-256")
        .digest(cadena.toByteArray())
        .toHexString()
}

// Convierte milisegundos (Epoch en milisegundos) a una fecha legible para nosotros con el formato día/mes/año
fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat(
        "dd/MM/yyyy",
        Locale.getDefault()
    ) // Se cambia del código original la fecha a formato dd/MM/yyyy. Día antes que el mes
    return formatter.format(Date(millis))
}

@Composable
fun DatePickerModal(
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState()

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                onDateSelected(datePickerState.selectedDateMillis)
                onDismiss()
            }) {
                Text("Confirmar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}