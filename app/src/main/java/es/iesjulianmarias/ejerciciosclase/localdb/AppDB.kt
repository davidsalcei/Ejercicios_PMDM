package es.iesjulianmarias.ejerciciosclase.localdb

import androidx.room.Database
import androidx.room.RoomDatabase

@Database( // Indica que esta clase define una base de datos Room.
    entities = [UsuarioData::class, SesionData::class], version = 1, exportSchema = true

    // Especifica que la base de datos contiene una tabla representada por la entidad UsuarioData. Si tenemos más tablas en la base de datos, se ponen a continuación separadas por comas. Ej: [UsuarioData::class, SesionData::class]
    // Establece la versión actual de la base de datos, necesaria para el manejo de migraciones y cambios realizados. Ir incrementando a medida que se va modificando para notificar y adaptar los cambios.
    // Indica que la estructura de la base de datos se exportará a un archivo para mantener un historial de esquemas.
)

// AppDB hereda de RoomDatabase, que es la clase base para bases de datos Room.
abstract class AppDB : RoomDatabase() {
    // Método abstracto usuarioDao() para proporcionar instancias de acceso a las operaciones definidas en la
    // interfaz UsuarioDao. Room genera la implementación automáticamente. Implementar este método para
    // proporcionar la instancia funcional de acceso a los datos. Es decir, para poder acceder a la BBDD desde nuestro código.
    abstract fun usuarioDao(): UsuarioDao
    abstract fun sesionDao(): SesionDao
}
