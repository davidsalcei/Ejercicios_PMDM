package es.iesjulianmarias.ejerciciosclase.examen.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import es.iesjulianmarias.ejerciciosclase.examen.db.EstructuraExamen
import es.iesjulianmarias.ejerciciosclase.examen.db.entities.SesionesDataExamen

@Dao
interface SesionDaoExamen {

    @Insert
    fun nuevaSesion(sesionesDataExamen: SesionesDataExamen)

    @Query("SELECT * FROM ${EstructuraExamen.Sesion.TABLE_NAME}")
    fun getEstadoSesion(): SesionesDataExamen?

    @Query("DELETE FROM ${EstructuraExamen.Sesion.TABLE_NAME} WHERE ${EstructuraExamen.Sesion.IDUSUARIO} = :idUsuario")
    fun cerrarSesion(idUsuario: Int?)

}