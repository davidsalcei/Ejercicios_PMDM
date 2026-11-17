package es.iesjulianmarias.ejerciciosclase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import es.iesjulianmarias.ejerciciosclase.apuntes.Andamio
import es.iesjulianmarias.ejerciciosclase.apuntes.Botones
import es.iesjulianmarias.ejerciciosclase.apuntes.Columna
import es.iesjulianmarias.ejerciciosclase.apuntes.EstadoCompose
import es.iesjulianmarias.ejerciciosclase.apuntes.Formularios
import es.iesjulianmarias.ejerciciosclase.apuntes.Iconos
import es.iesjulianmarias.ejerciciosclase.apuntes.Imagenes
import es.iesjulianmarias.ejerciciosclase.apuntes.Texto
import es.iesjulianmarias.ejerciciosclase.apuntes.navegacion.Manejador
import es.iesjulianmarias.ejerciciosclase.apuntes.navegacion.Navegador
import es.iesjulianmarias.ejerciciosclase.apuntes.navegacion.Primera
import es.iesjulianmarias.ejerciciosclase.ejercicios.Ejercicio1
import es.iesjulianmarias.ejerciciosclase.ejercicios.Ejercicio2
import es.iesjulianmarias.ejerciciosclase.ejercicios.Ejercicio3
import es.iesjulianmarias.ejerciciosclase.ejercicios.Ejercicio4
import es.iesjulianmarias.ejerciciosclase.ejercicios.Ejercicio5
import es.iesjulianmarias.ejerciciosclase.navigation.AppNavigation
import es.iesjulianmarias.ejerciciosclase.repaso.Ejercicio1Repaso

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppNavigation()
            //Formularios()
        }
    }
}