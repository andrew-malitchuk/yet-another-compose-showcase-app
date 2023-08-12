package dev.yacsa.domain.error

sealed interface DomainError

data class DataError(val throwable: Throwable) : DomainError
object IoError : DomainError
object NoDataError : DomainError
