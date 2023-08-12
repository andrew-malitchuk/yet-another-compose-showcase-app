package dev.yacsa.domain.impl.usecase.security

import arrow.core.Either
import dev.yacsa.domain.error.DataError
import dev.yacsa.domain.error.DomainError
import dev.yacsa.domain.impl.mapper.security.SecurityStatusDomainRepoMapper
import dev.yacsa.domain.model.security.SecurityStatusDomainModel
import dev.yacsa.domain.usecase.secutiry.CheckSecurityStatusUseCase
import dev.yacsa.repository.repository.SecurityRepository
import javax.inject.Inject

class CheckSecurityStatusUseCaseImpl @Inject constructor(
    private val securityRepository: SecurityRepository,
    private val securityStatusDomainRepoMapper: SecurityStatusDomainRepoMapper,
) : CheckSecurityStatusUseCase {

    override suspend fun invoke(): Either<DomainError, SecurityStatusDomainModel> {
        return try {
            val result = securityRepository.getSecurityStatus()
            Either.Right(securityStatusDomainRepoMapper.toDomain(result))
        } catch (ex: Exception) {
            Either.Left(DataError(ex))
        }
    }
}
