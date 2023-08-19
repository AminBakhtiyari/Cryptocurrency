package me.bakhtiyari.cryptocurrency.presentation.mapper

import me.bakhtiyari.cryptocurrency.domain.mapper.Mapper
import me.bakhtiyari.cryptocurrency.domain.model.TagModel
import me.bakhtiyari.cryptocurrency.presentation.model.TagUIModel
import me.bakhtiyari.cryptocurrency.presentation.utils.addMediaBaseUrl
import javax.inject.Inject

class TagUIModelMapper @Inject constructor() : Mapper<TagModel, TagUIModel> {

    override fun mapTo(type: TagModel?): TagUIModel = TagUIModel(
        id = type?.id,
        tagType = type?.tagType,
        tagTypeName = type?.tagTypeName,
        name = type?.name,
        symbol = type?.symbol,
        rank = type?.rank,
        logoUrl = type?.logoUrl?.addMediaBaseUrl(),
        isWatchlist = type?.isWatchlist
    )

    override fun mapFrom(type: TagUIModel?): TagModel = TagModel(
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