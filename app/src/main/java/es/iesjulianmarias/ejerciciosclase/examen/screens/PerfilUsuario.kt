package es.iesjulianmarias.ejerciciosclase.examen.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedSecureTextField
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.room.Room
import com.composables.icons.lucide.LogOut
import com.composables.icons.lucide.Lucide
import es.iesjulianmarias.ejerciciosclase.examen.db.AppDBExamen
import es.iesjulianmarias.ejerciciosclase.examen.db.EstructuraExamen
import es.iesjulianmarias.ejerciciosclase.navigation.AppScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilUsuario(navController: NavController) {

    // Se obtiene el contexto actual, necesario para construir la base de datos Room y mostrar Toasts.
    val context = LocalContext.current

    // Se crea una instancia de la base de datos, indicando el contexto, la clase base de datos y el nombre del archivo, permitiendo consultas en el hilo principal.
    val db = Room.databaseBuilder(context, AppDBExamen::class.java, EstructuraExamen.DB.NAME)
        .allowMainThreadQueries().build()

    val sesion = db.SesionDaoExamen().getEstadoSesion() // Obtener idUsuario por Sesion

    val perfilUsuario = db.UsuarioDaoExamen().getUsuarioPorID(sesion?.idUsuario)

    // BARRA SUPERIOR
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.height(100.dp), title = {
                    Text(text = "Mi perfil", fontSize = 20.sp)
                },
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                ),
                actions = {
                    IconButton(onClick = {
                        db.SesionDaoExamen().cerrarSesion(sesion?.idUsuario)
                        Toast.makeText(context, "Sesion cerrada", Toast.LENGTH_SHORT).show()
                        navController.navigate(route = AppScreens.LoginExamen.route)
                    }) {
                        Icon(Lucide.LogOut, "exitIcon")
                    }
                })
        }) { innerPadding ->

        var elementoSeleccionado by remember { mutableStateOf(false) }
        var abrirEdicion by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 20.dp)
                    .clickable(onClick = {
                        elementoSeleccionado = !elementoSeleccionado
                    })
            ) {

                perfilUsuario?.let {
                    val iniciales = buildString {
                        append(perfilUsuario.nombreUsuario.first().uppercase())
                    }

                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .background(
                                color = Color.Blue,
                                shape = CircleShape
                            )
                            .border(
                                width = 1.dp,
                                color = Color(0xff6c56a8),
                                shape = CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = iniciales,
                            fontSize = 25.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Medium,
                        )
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = perfilUsuario.nombreUsuario + " " + perfilUsuario.apellidosUsuario,
                            modifier = Modifier
                                .padding(start = 20.dp, top = 5.dp),
                            fontSize = 20.sp
                        )

                        Text(
                            text = perfilUsuario.email,
                            modifier = Modifier
                                .padding(start = 20.dp),
                            fontSize = 18.sp,
                            maxLines = if (!elementoSeleccionado) 1 else Int.MAX_VALUE
                        )

                        if (elementoSeleccionado) {
                            Button(
                                modifier = Modifier
                                    .padding(start = 20.dp, top = 10.dp),
                                onClick = {
                                    navController.navigate(route = AppScreens.Amigos.route)
                                }
                            ) {
                                Text(
                                    text = "Mis amistades",
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(end = 10.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.End
                    ) {
                        IconButton(onClick = { abrirEdicion = !abrirEdicion }) {
                            Icon(
                                imageVector = Icons.Filled.Edit,
                                contentDescription = "Editar",
                                modifier = Modifier.padding(top = 20.dp)
                            )
                        }
                    }
                }
            }



        }

        // =======================================================================================================================
        if (abrirEdicion) {
            var nombre by rememberSaveable { mutableStateOf("") }
            var apellidos by rememberSaveable { mutableStateOf("") }

            var email by rememberSaveable { mutableStateOf("") }
            val emailPattern = Regex("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")

            val radioOptions = listOf("Hombre", "Mujer", "Otro")
            val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }

            var incorporacion by rememberSaveable { mutableStateOf("") }
            var abrirPassword by remember { mutableStateOf(false) }

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .background(color = Color(0xffeaddff)),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        perfilUsuario?.let {

                            Text(text = "Hola, " + perfilUsuario.nombreUsuario, fontSize = 25.sp)

                            OutlinedTextField(
                                value = nombre,
                                onValueChange = {
                                    if (it.length < 40) {
                                        nombre = it
                                    }
                                },
                                label = { Text(text = perfilUsuario.nombreUsuario) },
                            )
                            OutlinedTextField(
                                value = apellidos,
                                onValueChange = {
                                    if (it.length < 40) {
                                        apellidos = it
                                    }
                                },
                                label = { Text(text = perfilUsuario.apellidosUsuario) },
                            )
                            OutlinedTextField(
                                value = email,
                                onValueChange = {
                                    if (it.length < 40) {
                                        email = it
                                    }
                                },
                                label = { Text(text = perfilUsuario.email) },
                            )

                            Text(
                                text = "¿Quieres editar tu contraseña?",
                                modifier = Modifier
                                    .padding(top = 10.dp, bottom = 10.dp)
                                    .clickable(onClick = { abrirPassword = !abrirPassword }),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )

                            if (abrirPassword) {

                                val passwordActual = rememberTextFieldState()
                                val passwordNueva = rememberTextFieldState()

                                var passVisibleActual by rememberSaveable { mutableStateOf(false) }
                                var passVisibleNueva by rememberSaveable { mutableStateOf(false) }

                                OutlinedSecureTextField(
                                    state = passwordActual,
                                    label = { Text(text = "Contraseña actual") },
                                    modifier = Modifier.width(280.dp),
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                                    trailingIcon = {
                                        IconButton(onClick = { passVisibleActual = !passVisibleActual }) {
                                            Icon(
                                                imageVector = if (passVisibleActual) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                                                contentDescription = if (passVisibleActual) "Ocultar contraseña" else "Mostrar contraseña"
                                            )
                                        }
                                    },
                                    textObfuscationMode = if (passVisibleActual) TextObfuscationMode.Visible else TextObfuscationMode.RevealLastTyped
                                )

                                OutlinedSecureTextField(
                                    state = passwordNueva,
                                    label = { Text(text = "Contraseña nueva") },
                                    modifier = Modifier.width(280.dp),
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                                    trailingIcon = {
                                        IconButton(onClick = { passVisibleNueva = !passVisibleNueva }) {
                                            Icon(
                                                imageVector = if (passVisibleNueva) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                                                contentDescription = if (passVisibleNueva) "Ocultar contraseña" else "Mostrar contraseña"
                                            )
                                        }
                                    },
                                    textObfuscationMode = if (passVisibleNueva) TextObfuscationMode.Visible else TextObfuscationMode.RevealLastTyped
                                )

                            }

                            OutlinedTextField(
                                value = incorporacion,
                                onValueChange = { incorporacion = it },
                                label = { Text(text = perfilUsuario.incorporacion) },
                            )

                            Spacer(modifier = Modifier.height(10.dp))

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
                        }

                        Spacer(modifier = Modifier.height(10.dp))
                        Button(
                            onClick = {}
                        ) { Text(text = "Actualizar mis datos") }
                    }

                }
            }

        }
        // =======================================================================================================================

    }
}