package es.iesjulianmarias.ejerciciosclase.examen.screens
// Modificar este package según vuestro proyecto

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.room.Room
import es.iesjulianmarias.ejerciciosclase.examen.db.AppDBExamen
import es.iesjulianmarias.ejerciciosclase.examen.db.EstructuraExamen
import es.iesjulianmarias.ejerciciosclase.examen.db.entities.AmistadesDataExamen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Amigos(navController: NavController) {

    // Contexto e instancia de la base de datos local.
    val context = LocalContext.current
    val db = Room.databaseBuilder(context, AppDBExamen::class.java, EstructuraExamen.DB.NAME)
        .allowMainThreadQueries().build()

    // Se obtiene el id del usuario de la sesión actual de la tabla Sesión.
    var idSesionActual by rememberSaveable {
        mutableIntStateOf(db.SesionDaoExamen().getEstadoSesion()?.idUsuario ?: 0)
    }

    // Se obtiene el listado de todos los usuarios menos el de la sesión actual.
    val listaUsuarios = db.UsuarioDaoExamen().getListaUsuarios(idSesionActual).toMutableList()

    // Almacena los IDs de los usuarios favoritos en un Set para simplificar lecturas y escrituras.
    var favoritos by rememberSaveable { mutableStateOf(emptySet<Int>()) }

    // Carga inicial de los IDs (idUsuario2) de los favoritos del usuario actual (idUsuario1).
    LaunchedEffect(Unit) {
        favoritos = db.AmistadDaoExamen().getAmistadDeUnUsuario(idSesionActual)
            .mapTo(HashSet()) { it.idUsuario2 }
    }

    //Barra superior.
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.height(90.dp),
                title = {
                    Text(
                        text = "Mis amigos",
                        fontSize = 15.sp
                    )
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
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { innerPadding ->

        // Acceso a todos los usuarios para mostrar la información, presentándolo con distintos elementos componibles y formatos.
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            itemsIndexed(listaUsuarios) { index, user ->

                // Comprobación de si el usuario actual según se va iterando está en la lista de favoritos de ese usuario.
                val isFavorito = user.idUsuario in favoritos

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    // Generar la primera parte de la fila con las iniciales.
                    val iniciales = buildString {
                        append(user.nombreUsuario.first().uppercase())
                        if (user.apellidosUsuario.isNotBlank()) {
                            append(user.apellidosUsuario.first().uppercase())
                        }
                    }
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .background(
                                color = Color(0xffeaddff),
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
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Medium,
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    // Columna con los 3 datos del usuario.
                    Column(
                        modifier = Modifier
                            .weight(1f),
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Text(
                            text = user.nombreUsuario,
                            fontSize = 15.sp
                        )
                        Text(
                            text = user.apellidosUsuario,
                            fontSize = 14.sp
                        )
                        Text(
                            text = user.email,
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                    // Icono de la derecha de la fila, para marcar como favorito.
                    IconButton(
                        onClick = {
                            // Actualizar a cada pulsación el estado de favorito del usuario con el nuevo estado al que se pasa.
                            if (isFavorito) {
                                // Elegir adecuadamente cuál será la clave primaria.
                                db.AmistadDaoExamen().eliminarAmistad(
                                    idUsuario1 = idSesionActual,
                                    idUsuario2 = user.idUsuario
                                )
                                // Elimina ese Id de usuario a la lista temporal de favoritos.
                                favoritos = favoritos - user.idUsuario
                            } else {

                                db.AmistadDaoExamen().nuevaAmistad(
                                    AmistadesDataExamen(
                                        idUsuario1 = idSesionActual,
                                        idUsuario2 = user.idUsuario
                                    )
                                )
                                // Añade ese Id de usuario a la lista temporal de favoritos.
                                favoritos = favoritos + user.idUsuario
                            }
                        }
                    ) {
                        // Muestra un icono de favorito o no favorito, según corresponda (el efecto está en el color de relleno tint).
                        Icon(
                            imageVector = if (isFavorito) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                            contentDescription = "Usuario favorito o no favorito",
                            tint = if (isFavorito) {
                                Color.Red
                            } else {
                                Color.Black
                            }
                        )
                    }
                }

                // Barra horizontal.
                HorizontalDivider(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    thickness = DividerDefaults.Thickness,
                    color = MaterialTheme.colorScheme.outline.copy(alpha = 0.4f)
                )

            }
        }
    }
}