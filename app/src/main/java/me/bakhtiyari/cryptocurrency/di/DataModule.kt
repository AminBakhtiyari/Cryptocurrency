package me.bakhtiyari.cryptocurrency.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import me.bakhtiyari.cryptocurrency.BuildConfig
import me.bakhtiyari.cryptocurrency.data.cache.dao.RemoteKeyDao
import me.bakhtiyari.cryptocurrency.data.cache.dao.TagDao
import me.bakhtiyari.cryptocurrency.data.cache.database.TagsDatabase
import me.bakhtiyari.cryptocurrency.data.cache.datasource.GetTagsCacheDataSource
import me.bakhtiyari.cryptocurrency.data.cache.datasource.GetTagsCacheDataSourceImpl
import me.bakhtiyari.cryptocurrency.data.remote.api.ApiService
import me.bakhtiyari.cryptocurrency.data.remote.datasource.GetTagsRemoteDataSource
import me.bakhtiyari.cryptocurrency.data.remote.datasource.GetTagsRemoteDataSourceImpl
import me.bakhtiyari.cryptocurrency.data.remote.di.createService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return createService(BuildConfig.DEBUG, BuildConfig.BASE_URL, BuildConfig.API_KEY)
    }

    @Provides
    @Singleton
    fun provideGetTagsRemoteDataSourceImpl(getTagsRemoteDataSourceImpl: GetTagsRemoteDataSourceImpl): GetTagsRemoteDataSource {
        return getTagsRemoteDataSourceImpl
    }

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context): TagsDatabase {
        return TagsDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideTagDao(tagsDatabase: TagsDatabase): TagDao {
        return tagsDatabase.cachedTagDao()
    }

    @Provides
    @Singleton
    fun provideRemoteKeyDao(tagsDatabase: TagsDatabase): RemoteKeyDao {
        return tagsDatabase.cachedRemoteKeyDao()
    }

    @Provides
    @Singleton
    fun provideGetTagsCacheDataSource(getTagsCacheDataSourceImpl: GetTagsCacheDataSourceImpl): GetTagsCacheDataSource {
        return getTagsCacheDataSourceImpl
    }
}