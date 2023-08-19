package me.bakhtiyari.cryptocurrency.domain.mapper

interface Mapper<E, D> {

    fun mapTo(type: E?): D
    fun mapFrom(type: D?): E
}
