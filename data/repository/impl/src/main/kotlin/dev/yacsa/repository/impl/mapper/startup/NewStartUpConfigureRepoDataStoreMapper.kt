package dev.yacsa.repository.impl.mapper.startup

import dev.yacsa.datastore.model.StartUpConfigureDataStoreModel
import dev.yacsa.repository.model.StartUpConfigureRepoModel
import org.mapstruct.Mapper
import org.mapstruct.NullValueCheckStrategy
import org.mapstruct.NullValuePropertyMappingStrategy


@Mapper(
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT,
)
interface NewStartUpConfigureRepoDataStoreMapper {

    fun toRepo(
        startUpConfigureDataStoreModel: StartUpConfigureDataStoreModel
    ): StartUpConfigureRepoModel

    fun toDataStore(
        startUpConfigureRepoModel: StartUpConfigureRepoModel
    ): StartUpConfigureDataStoreModel

}