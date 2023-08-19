package me.bakhtiyari.cryptocurrency.data.repository.mapper

import me.bakhtiyari.cryptocurrency.data.cache.model.TagCacheEntity
import me.bakhtiyari.cryptocurrency.domain.mapper.Mapper
import me.bakhtiyari.cryptocurrency.domain.model.TagModel
import javax.inject.Inject

class TagModelMapper  @Inject constructor() : Mapper<TagCacheEntity, TagModel> {

    override fun mapTo(type: TagCacheEntity?): TagModel = TagModel(
        id = type?.id,
        tagType = type?.tagType,
        tagTypeName = type?.tagTypeName,
        name = type?.name,
        symbol = type?.symbol,
        rank = type?.rank,
        logoUrl = type?.logoUrl,
        isWatchlist = type?.isWatchlist
    )

    override fun mapFrom(type: TagModel?): TagCacheEntity = TagCacheEntity(
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