package es.iesjulianmarias.ejerciciosclase.examen.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import es.iesjulianmarias.ejerciciosclase.examen.db.EstructuraExamen
import es.iesjulianmarias.ejerciciosclase.examen.db.entities.UsuariosDataExamen

@Dao
interface UsuarioDaoExamen {

     @Insert
     fun nuevoUsuario(usuariosDataExamen: UsuariosDataExamen)

     @Query("SELECT * FROM ${EstructuraExamen.Usuario.TABLE_NAME}  WHERE idUsuario = :idUsuario")
     fun getListaUsuarios(idUsuario: Int): List<UsuariosDataExamen>

     @Query("SELECT * FROM ${EstructuraExamen.Usuario.TABLE_NAME} WHERE email = :email")
     fun getUsuario(email: String): UsuariosDataExamen?

     @Query("SELECT * FROM ${EstructuraExamen.Usuario.TABLE_NAME} WHERE idUsuario = :idUsuario")
     fun getUsuarioPorID(idUsuario: Int?): UsuariosDataExamen?

}