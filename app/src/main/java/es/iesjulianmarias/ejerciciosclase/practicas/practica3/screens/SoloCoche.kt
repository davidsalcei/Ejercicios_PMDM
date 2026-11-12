package es.iesjulianmarias.ejerciciosclase.practicas.practica3.screens

import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.room.Room
import coil.compose.AsyncImage
import com.composables.icons.lucide.ArrowLeft
import com.composables.icons.lucide.Lucide
import es.iesjulianmarias.ejerciciosclase.practicas.practica3.db.CocheData
import es.iesjulianmarias.ejerciciosclase.practicas.practica3.db.DatabaseCoche
import es.iesjulianmarias.ejerciciosclase.practicas.practica3.db.EstructuraCoche

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SoloCoche(navController: NavController, titulo: String?) {

    val context = LocalContext.current // Necesario para Room y Toasts
    val db = Room.databaseBuilder(context, DatabaseCoche::class.java, EstructuraCoche.DataBase.NAME)
        .allowMainThreadQueries().build() // Instanciar base de datos
    var coche: CocheData?

    if (titulo == null) return

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.height(100.dp),
                title = { Text(text = "Detalles del coche", fontSize = 20.sp) },
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Lucide.ArrowLeft, "Volver")
                    }
                })
        }) { innerPadding ->
        coche = db.cocheDao().getUnCoche(titulo)

        coche?.let {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(Color.Black)
            ) {

                AsyncImage(
                    model = it.imagenCoche,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(all = 10.dp)
                        .background(color = Color.Transparent)
                        .height(200.dp)
                        .clip(shape = RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.FillWidth
                )

                Text(
                    modifier = Modifier.padding(all = 10.dp),
                    text = it.tituloCoche,
                    fontSize = 30.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    BloqueDelMedio(it.caballosCoche, "Caballos")
                    BloqueDelMedio(it.consumoCoche, "Consumo")
                    BloqueDelMedio(it.precioCoche, "Precio")
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 10.dp)
                ) {
                    Column() {
                        val context = LocalContext.current
                        Text(
                            text = "Informacion adicional:",
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            modifier = Modifier.padding(top = 10.dp),
                            text = it.descripcionCoche,
                            color = Color.Magenta
                        )
                        Text(
                            text = "Abrir wikipedia",
                            modifier = Modifier
                                .padding(all = 20.dp)
                                .clickable(onClick = {
                                    val url = it.urlCoche
                                    val customTabsIntent = CustomTabsIntent.Builder().build()
                                    customTabsIntent.launchUrl(context, url.toUri())
                                }),
                            color = Color.White
                        )
                    }
                }
            }
        }

    }
}

@Composable
fun BloqueDelMedio(campoDB: String, texto: String) {
    Column(
        modifier = Modifier
            .width(120.dp)
            .height(60.dp)
            .background(
                MaterialTheme.colorScheme.primary,
                RoundedCornerShape(10.dp)
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = campoDB,
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = texto,
            color = Color.LightGray,
            fontSize = 12.sp
        )
    }
}