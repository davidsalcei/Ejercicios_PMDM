package es.iesjulianmarias.ejerciciosclase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import es.iesjulianmarias.ejerciciosclase.ejercicios.Ejercicio1
import es.iesjulianmarias.ejerciciosclase.ejercicios.Ejercicio2
import es.iesjulianmarias.ejerciciosclase.ejercicios.Ejercicio3
import es.iesjulianmarias.ejerciciosclase.ejercicios.Ejercicio4
import es.iesjulianmarias.ejerciciosclase.ejercicios.Ejercicio5
import es.iesjulianmarias.ejerciciosclase.navigation.AppNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppNavigation()
        }
    }
}