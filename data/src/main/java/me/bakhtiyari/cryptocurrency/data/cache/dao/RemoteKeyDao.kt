package me.bakhtiyari.cryptocurrency.data.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import me.bakhtiyari.cryptocurrency.data.cache.model.RemoteKeyEntity

@Dao
interface RemoteKeyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(remoteKey: RemoteKeyEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplaceAll(vararg remoteKeys: RemoteKeyEntity)

    @Query("SELECT * FROM remote_keys WHERE tag_id = :tagId")
    suspend fun remoteKeyByTagId(tagId: Long): RemoteKeyEntity

    @Query("DELETE FROM remote_keys")
    suspend fun clearRemoteKeys()
}