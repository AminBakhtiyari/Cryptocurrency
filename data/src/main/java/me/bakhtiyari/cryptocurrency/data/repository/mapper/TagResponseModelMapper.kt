package me.bakhtiyari.cryptocurrency.data.repository.mapper

import me.bakhtiyari.cryptocurrency.data.cache.model.TagCacheEntity
import me.bakhtiyari.cryptocurrency.data.remote.model.TagResponseModel
import me.bakhtiyari.cryptocurrency.domain.mapper.Mapper
import javax.inject.Inject

class TagResponseModelMapper  @Inject constructor() : Mapper<TagCacheEntity, TagResponseModel> {

    override fun mapTo(type: TagCacheEntity?): TagResponseModel = TagResponseModel(
        id = type?.id,
        tagType = type?.tagType,
        tagTypeName = type?.tagTypeName,
        name = type?.name,
        symbol = type?.symbol,
        rank = type?.rank,
        logoUrl = type?.logoUrl,
        isWatchlist = type?.isWatchlist
    )

    override fun mapFrom(type: TagResponseModel?): TagCacheEntity = TagCacheEntity(
        id = type?.id,
        tagType = type?.tagType,
        tagTypeName = type?.tagTypeName,
        name = type?.name,
        symbol = type?.symbol,
        rank = type?.rank,
        logoUrl = type?.logoUrl,
        isWatchlist = type?.isWatchlist
    )
}