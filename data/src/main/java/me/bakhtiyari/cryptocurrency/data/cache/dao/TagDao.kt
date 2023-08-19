package me.bakhtiyari.cryptocurrency.data.cache.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import me.bakhtiyari.cryptocurrency.data.cache.model.TagCacheEntity

@Dao
interface TagDao {

    @Query("SELECT * FROM tags WHERE (:query = '' OR symbol LIKE '%' || :query || '%' OR name LIKE '%' || :query || '%')")
    fun getTags(query: String): PagingSource<Int, TagCacheEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTags(vararg images: TagCacheEntity)

    @Query("DELETE FROM tags")
    suspend fun nukeTable()
}