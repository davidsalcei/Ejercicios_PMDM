package es.iesjulianmarias.ejerciciosclase.practicas.practica3.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CocheDao {
    // Querys

    // Insertar coche
    @Insert
    fun nuevoCoche(cocheData: CocheData)

    // Leer toda la lista de coches
    @Query("SELECT * FROM ${EstructuraCoche.Coche.TABLE_NAME}")
    fun getListaCoches(): List<CocheData>

    // Leer un coche en concreto a través de su titulo
    @Query("SELECT * FROM ${EstructuraCoche.Coche.TABLE_NAME} WHERE titulo = :titulo")
    fun getUnCoche(titulo: String): CocheData?

}