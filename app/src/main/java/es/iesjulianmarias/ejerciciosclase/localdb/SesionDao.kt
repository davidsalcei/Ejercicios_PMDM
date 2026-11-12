package es.iesjulianmarias.ejerciciosclase.localdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SesionDao {

    // Iniciar sesion
    @Insert
    fun nuevaSesion(user: SesionData)

    // Consultar inicio de sesion
    @Query("SELECT * FROM ${Estructura.Sesion.TABLE_NAME}")
    fun getEstadoSesion(): SesionData?

    // Cerrar sesion
    @Query("DELETE FROM ${Estructura.Sesion.TABLE_NAME} WHERE ${Estructura.Sesion.IDUSUARIO} = :idUsuario")
    fun cierraSesion(idUsuario: Int)

}