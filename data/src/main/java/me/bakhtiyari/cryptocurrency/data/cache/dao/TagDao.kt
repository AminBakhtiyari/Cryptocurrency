package me.bakhtiyari.cryptocurrency.data.cache.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import me.bakhtiyari.cryptocurrency.data.cache.model.TagCacheEntity

@Dao
interface TagDao {

    @Query("SELECT * FROM tags")
    fun getTags(): PagingSource<Int, TagCacheEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTags(vararg images: TagCacheEntity)

    @Query("DELETE FROM tags")
    suspend fun nukeTable()
}