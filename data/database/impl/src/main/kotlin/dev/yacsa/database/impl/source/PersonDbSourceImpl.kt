package dev.yacsa.database.impl.source

import dev.yacsa.database.impl.dao.PersonDbDao
import dev.yacsa.database.model.PersonDbModel
import dev.yacsa.database.source.PersonDbSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PersonDbSourceImpl @Inject constructor(
    private val personDao: PersonDbDao,
) : PersonDbSource {

    override suspend fun get(id: Int): PersonDbModel? {
        return personDao.get(id)
    }

    override suspend fun get(): List<PersonDbModel>? {
        return personDao.get()
    }

    override suspend fun getFlow(): Flow<List<PersonDbModel>?> {
        return personDao.getFlow()
    }

    override suspend fun insert(value: PersonDbModel): Int {
        return personDao.insert(value).toInt()
    }

    override suspend fun insert(values: List<PersonDbModel>) {
        values.forEach {
            personDao.insert(it)
        }
    }

    override suspend fun update(value: PersonDbModel) {
        personDao.update(value)
    }

    override suspend fun update(values: List<PersonDbModel>) {
        values.forEach {
            personDao.update(it)
        }
    }

    override suspend fun delete(id: Int) {
        return personDao.delete(id)
    }

    override suspend fun delete(ids: List<Int>) {
        ids.forEach {
            personDao.delete(it)
        }
    }

    override suspend fun deleteAll() {
        return personDao.deleteAll()
    }
}
