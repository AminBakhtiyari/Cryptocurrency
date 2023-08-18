package me.bakhtiyari.cryptocurrency.data.cache.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import me.bakhtiyari.cryptocurrency.data.cache.dao.RemoteKeyDao
import me.bakhtiyari.cryptocurrency.data.cache.dao.TagDao
import me.bakhtiyari.cryptocurrency.data.cache.model.RemoteKeyEntity
import me.bakhtiyari.cryptocurrency.data.cache.model.TagCacheEntity
import me.bakhtiyari.cryptocurrency.data.cache.utils.CacheConstants
import me.bakhtiyari.cryptocurrency.data.cache.utils.Migrations
import javax.inject.Inject

@Database(
    entities = [TagCacheEntity::class, RemoteKeyEntity::class],
    version = Migrations.DB_VERSION,
    exportSchema = false
)
abstract class TagsDatabase @Inject constructor() : RoomDatabase() {

    abstract fun cachedTagDao(): TagDao
    abstract fun cachedRemoteKeyDao(): RemoteKeyDao

    companion object {
        @Volatile
        private var INSTANCE: TagsDatabase? = null

        fun getInstance(context: Context): TagsDatabase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            TagsDatabase::class.java,
            CacheConstants.DB_NAME
        ).build()
    }
}