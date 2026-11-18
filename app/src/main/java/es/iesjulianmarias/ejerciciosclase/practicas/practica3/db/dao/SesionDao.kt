package es.iesjulianmarias.ejerciciosclase.practicas.practica3.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import es.iesjulianmarias.ejerciciosclase.practicas.practica3.db.Estructura
import es.iesjulianmarias.ejerciciosclase.practicas.practica3.db.entities.SesionesData

@Dao
interface SesionDao {

    @Insert
    fun nuevaSesion(sesionesData: SesionesData)

    @Query("SELECT * FROM ${Estructura.Sesion.TABLE_NAME}")
    fun getEstadoSesion(): SesionesData?

    @Query("DELETE FROM ${Estructura.Sesion.TABLE_NAME} WHERE ${Estructura.Sesion.IDUSUARIO} = :idUsuario")
    fun cerrarSesion(idUsuario: Int?)

}