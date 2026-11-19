package es.iesjulianmarias.ejerciciosclase.examen.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import es.iesjulianmarias.ejerciciosclase.examen.db.EstructuraExamen
import es.iesjulianmarias.ejerciciosclase.examen.db.entities.AmistadesDataExamen

@Dao
interface AmistadDaoExamen {

    @Insert
    fun nuevaAmistad(amistadesDataExamen: AmistadesDataExamen)

    @Query("SELECT * FROM ${EstructuraExamen.Amistad.TABLE_NAME} WHERE idUsuario1 = :idUsuario1")
    fun getAmistadDeUnUsuario(idUsuario1: Int): List<AmistadesDataExamen>

    @Query("DELETE FROM ${EstructuraExamen.Amistad.TABLE_NAME} WHERE idUsuario1 = :idUsuario1 AND idUsuario2 = :idUsuario2")
    fun eliminarAmistad(idUsuario1: Int, idUsuario2: Int)

}