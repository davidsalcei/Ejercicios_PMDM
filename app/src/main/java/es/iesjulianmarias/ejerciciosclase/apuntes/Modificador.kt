package es.iesjulianmarias.ejerciciosclase.apuntes

import androidx.compose.runtime.Composable

@Composable
fun Modificador() {

    /*
    Modificadores de Modifier:
     - modifier: poner modificadores (padding, background..)
     EL ORDEN DE LA FUNCIONES ES IMPORTANTE, LOS PRIMEROS QUE SE AGREGUEN SE APLICAN PRIMERO
        - .padding: coloca espacio de relleno alrededor de un elemento
        - .size: establece un tamaño. Se puede indicar ancho (width) y alto (height)
        - .fillMaxWidth: el elemento ocupa el ancho maximo que otorga el elemento superior
        - .fillMaxHeight: el elemento ocupa el alto maximo que otorga el elemento superior
        - .fillMaxSize: el elemento ocupa todo el tamaño disponible
        - .background: proporciona un color detrás del contenido
        - .weight: tamaño de un elemento componible para ser flexible (Solo Column y Row)
            - El primero ocupa el doble que el segundo, sobre las 3 partes a repartir entre los 2
            - Al estar repartido entre 3 partes cada parte seria 33,33%
     */

}