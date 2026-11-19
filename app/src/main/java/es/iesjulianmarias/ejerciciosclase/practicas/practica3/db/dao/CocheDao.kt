package es.iesjulianmarias.ejerciciosclase.practicas.practica3.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import es.iesjulianmarias.ejerciciosclase.practicas.practica3.db.Estructura
import es.iesjulianmarias.ejerciciosclase.practicas.practica3.db.entities.CochesData

@Dao
interface CocheDao {

    @Insert
    fun nuevoCoche(cochesData: CochesData)

    @Query("SELECT * FROM ${Estructura.Coche.TABLE_NAME} WHERE idUsuario = :idUsuario")
    fun getListaCoches(idUsuario: Int): List<CochesData>

    @Query("SELECT * FROM ${Estructura.Coche.TABLE_NAME} WHERE idUsuario = :idUsuario")
    fun getCochePorID(idUsuario: Int?): CochesData?

}