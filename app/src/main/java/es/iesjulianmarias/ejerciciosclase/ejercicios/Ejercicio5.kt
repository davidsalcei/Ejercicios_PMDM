package es.iesjulianmarias.ejerciciosclase.ejercicios

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Ejercicio5(){

    Column(modifier = Modifier.padding(all = 70.dp)) {

        val variableEstado = remember { mutableStateOf(0) }
        variableEstado.value = 1
        Text( text = "Valor de la variable estado: ${variableEstado.value}")

        Text(
            text = "Hola!",
            modifier = Modifier.padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = "FDGHH",
            onValueChange = {},
            label = { Text(text = "Nombre") }
        )
    }

}