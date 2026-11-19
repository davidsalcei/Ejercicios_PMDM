package es.iesjulianmarias.ejerciciosclase.examen.db

import androidx.room.Database
import androidx.room.RoomDatabase
import es.iesjulianmarias.ejerciciosclase.examen.db.dao.AmistadDaoExamen
import es.iesjulianmarias.ejerciciosclase.examen.db.dao.SesionDaoExamen
import es.iesjulianmarias.ejerciciosclase.examen.db.dao.UsuarioDaoExamen
import es.iesjulianmarias.ejerciciosclase.examen.db.entities.AmistadesDataExamen
import es.iesjulianmarias.ejerciciosclase.examen.db.entities.SesionesDataExamen
import es.iesjulianmarias.ejerciciosclase.examen.db.entities.UsuariosDataExamen


@Database(
    entities = [UsuariosDataExamen::class, SesionesDataExamen::class, AmistadesDataExamen::class], version = 1, exportSchema = true
)

abstract class AppDBExamen: RoomDatabase() {
    abstract fun UsuarioDaoExamen(): UsuarioDaoExamen
    abstract fun SesionDaoExamen(): SesionDaoExamen
    abstract fun AmistadDaoExamen(): AmistadDaoExamen
}