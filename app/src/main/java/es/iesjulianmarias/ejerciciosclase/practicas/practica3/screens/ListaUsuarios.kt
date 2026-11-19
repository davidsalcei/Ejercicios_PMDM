package es.iesjulianmarias.ejerciciosclase.practicas.practica3.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
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
fun ListaUsuarios(navController: NavHostController) {

    val context = LocalContext.current
    val db = Room.databaseBuilder(context, AppDB::class.java, Estructura.DB.NAME)
        .allowMainThreadQueries().build()

    // Inicializamos una variable como una lista y obtenemos la lista de usuarios de la DB
    var listaUsuarios = mutableListOf<UsuariosData>()
    listaUsuarios = db.UsuarioDao().getListaUsuarios().toMutableList()

    val sesion = db.SesionDao().getEstadoSesion() // Obtener idUsuario de Sesion

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Lista de usuarios") },
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
                .background(color = Color.Black),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 10.dp, bottom = 10.dp)
            ) {
                Text(text = "ID", color = Color.White,modifier = Modifier.weight(0.5f), textAlign = TextAlign.Center)
                Text(text = "Nombre", color = Color.White,modifier = Modifier.weight(0.5f), textAlign = TextAlign.Center)
                Text(text = "Apellidos", color = Color.White,modifier = Modifier.weight(0.5f), textAlign = TextAlign.Center)
                Text(text = "Email", color = Color.White,modifier = Modifier.weight(0.5f), textAlign = TextAlign.Center)
                Text(text = "Fecha", color = Color.White,modifier = Modifier.weight(0.5f), textAlign = TextAlign.Center)
            }

            LazyColumn() {
                itemsIndexed(listaUsuarios) { _, user ->
                    Row(modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp)) {
                        Text(text = "${user.idUsuario}", color = Color.White, modifier = Modifier.weight(0.5f), textAlign = TextAlign.Center)
                        Text(text = user.nombreUsuario, color = Color.White, modifier = Modifier.weight(0.5f), textAlign = TextAlign.Center)
                        Text(text = user.apellidosUsuario, color = Color.White, modifier = Modifier.weight(0.5f), textAlign = TextAlign.Center)
                        Text(text = user.email, color = Color.White, modifier = Modifier.weight(0.5f), textAlign = TextAlign.Center)
                        Text(text = "${user.fecha}", color = Color.White, modifier = Modifier.weight(0.5f), textAlign = TextAlign.Center)
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Si la sesion está iniciada, mostrará los botones
            if (sesion != null) {
                Button(
                    onClick = { navController.navigate(AppScreens.FormularioCoche.route) }
                ) { Text(text = "Ir a formulario del coche") }

                Button(
                    onClick = {
                        db.SesionDao().cerrarSesion(sesion.idUsuario)
                        navController.navigate(AppScreens.LoginUsuario.route)
                    }
                ) { Text(text = "Cerrar sesion") }
            }

        }
    }
}