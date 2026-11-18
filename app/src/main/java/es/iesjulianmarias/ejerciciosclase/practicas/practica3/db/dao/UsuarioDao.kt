package es.iesjulianmarias.ejerciciosclase.practicas.practica3.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import es.iesjulianmarias.ejerciciosclase.practicas.practica3.db.Estructura
import es.iesjulianmarias.ejerciciosclase.practicas.practica3.db.entities.UsuariosData

@Dao
interface UsuarioDao {

     @Insert
     fun nuevoUsuario(usuarioData: UsuariosData)

     @Query("SELECT * FROM ${Estructura.Usuario.TABLE_NAME}")
     fun getListaUsuarios(): List<UsuariosData>

     @Query("SELECT * FROM ${Estructura.Usuario.TABLE_NAME} WHERE email = :email")
     fun getUsuario(email: String): UsuariosData?

}