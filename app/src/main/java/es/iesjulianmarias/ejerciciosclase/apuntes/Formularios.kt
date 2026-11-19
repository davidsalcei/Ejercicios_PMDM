package es.iesjulianmarias.ejerciciosclase.apuntes

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedSecureTextField
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.SecureTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TooltipAnchorPosition
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Formularios() {

    /*
    Propiedades de los formularios:
     - TextField (RECOMENDADA): estilo de contenedor relleno.
        - value = "": valor inicial
        - onValueChange = {}: cuando se cambia el value, hace x cosas
        - label = {}: texto de referencia de dentro del contenedor
        - keyboardOptions = KeyboardActions(
            - KeyboardType = KeyboardType.
                - Text: teclado normal
                - Number: teclado solo numeros
                - Phone: teclado solo numeros de telefono
                - Email: teclado para correos
                - Password: teclado oculta texto para contraseñas, va revelando
                - VisiblePassword: teclado muestra contraseña sin ocultar
                - Decimal: teclado para numeros con decimales
                - Uri: teclado para URLs

            - capitalization = KeyboardCapitalization.
                - None: no cambia las letras
                - Characters: todas las letras en mayusculas
                - Words: primera letra en mayuscula tras escribir un espacio (por palabra)
                - Sentences: primera letra en mayuscula tras escribir un punto (por oracion)
                - Unspecified: no se especifican reglas

            - autoCorrectEnabled: habilita o no la autocorrecion mientras se escribe
            - imeAction: accion que el usuario ve y puede activar en el teclado de la pantalla
                - Default: accion por defecto
                - Done: boton con tick (done)
                - Go: boton con flecha (ir)
                - Next: boton con flecha para siguiente campo
                - Previous: boton con flecha para campo anterior
                - Search: boton con icono de busqueda (lupa)
                - Send: boton para enviar informacion

            - showKeyboardOnFocus: controlar si el teclado se muestra cuando el campo recibe el foco

     - OutlinedTextField: estilo de contenedor con contorno.
     - BasicTextField: permite editar el texto con el teclado pero sin decoraciones.
     - SecureTextField: permite ocultar entrada de caracteres (contraseña), no tiene copiar y cortar
        - textObfuscationMode = TextObfuscationMode.
            - Hidden: oculta la entrada entera
            - Visible: muentra toda la entrada
            - RevealLastTyped: oculta todo menos el ultimo caracter
        SI NO SE USA Y SE USA EL keyboardOptions con KeyboardType.Password, por defecto usa RevealLastTyped

     - OutlinedSecureTextField:
     */

    // Ejemplos de TextField
    Column(modifier = Modifier.padding(top = 80.dp, start = 20.dp)) {
        var textoIngresado by rememberSaveable { mutableStateOf("") }

        Text(text = "Ejemplo: TextField")
        TextField(
            value = textoIngresado,
            onValueChange = { textoIngresado = it },
            label = { Text(text = "Campo de texto") }
        )

        // Se necesita state en los TextField para usarlo adecuadamente
        val nombre = rememberTextFieldState("Hola mundo")
        Text(text = "Ejemplo: TextField con state", modifier = Modifier.padding(top = 20.dp))
        TextField(
            state = nombre,
            label = { Text(text = "Campo de texto") }
        )

        Text(text = "Ejemplo: OutlinedTextField", modifier = Modifier.padding(top = 20.dp))
        OutlinedTextField(
            value = textoIngresado,
            onValueChange = { textoIngresado = it },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.White,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                unfocusedLabelColor = Color.White,
                focusedBorderColor = Color.White,
                focusedLabelColor = Color.White
            ),
            label = { Text(text = "Campo de texto") }
        )

        Text(text = "Ejemplo: BasicTextField", modifier = Modifier.padding(top = 20.dp))
        BasicTextField(
            value = textoIngresado,
            onValueChange = { textoIngresado = it },
            modifier = Modifier.padding(top = 10.dp).border(1.dp, color = Color.Black)
        )

        val estadoContrasenia = rememberTextFieldState()
        Text(text = "Ejemplo: SecureTextField", modifier = Modifier.padding(top = 20.dp))
        SecureTextField(
            state = estadoContrasenia,
            label = { Text(text = "Contraseña") },
            placeholder = { Text(text = "Introduce la contraseña: ") },
            textObfuscationMode = TextObfuscationMode.Hidden,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        val password = rememberTextFieldState()
        var passwordOculta by rememberSaveable { mutableStateOf(true) }
        Text(text = "Ejemplo: SecureTextField con iconos", modifier = Modifier.padding(top = 20.dp))
        SecureTextField(
            state = password,
            placeholder = { Text(text = "Introduce la contraseña: ") },
            textObfuscationMode = if (passwordOculta) TextObfuscationMode.RevealLastTyped else TextObfuscationMode.Visible,
            trailingIcon = {
                // Mensaje que saldrá en el boton cuando le das
                val description = if (passwordOculta) "Mostrar contraseña" else "Ocultar contraseña"

                // Icono que sale dependiendo de si está oculto o no
                val visibilityIcon = if (passwordOculta) Icons.Filled.Visibility else Icons.Filled.VisibilityOff

                // Para mostrar un texto descriptivo
                TooltipBox(
                    positionProvider = TooltipDefaults.rememberTooltipPositionProvider(TooltipAnchorPosition.Above),
                    tooltip = { PlainTooltip { Text(description) }},
                    state = rememberTooltipState()
                ) {
                    IconButton(onClick = { passwordOculta = !passwordOculta}) {
                        Icon(imageVector = visibilityIcon, contentDescription = description)
                    }
                }
            }
        )

        val contraseniaOutlined = rememberTextFieldState()
        var passVisible by rememberSaveable { mutableStateOf(false) }
        Text(text = "Ejemplo: OutlinedSecureTextField", modifier = Modifier.padding(top = 20.dp))
        OutlinedSecureTextField(
            state = contraseniaOutlined,
            label = { Text(text = "Contraseña") },
            modifier = Modifier.width(300.dp),
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

    }
}