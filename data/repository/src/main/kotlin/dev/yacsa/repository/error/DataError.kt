package dev.yacsa.repository.error

// TODO: idk, maybe, recode?
sealed interface DataError

sealed class CryptoDatastoreError() : DataError {
    class CryptoDatastoreMapperError() : CryptoDatastoreError()
    class CryptoDatastoreIoError(throwable: Throwable) : CryptoDatastoreError()
}

sealed class DatabaseError() : DataError {
    class DatabaseMapperError() : DatabaseError()
    class DatabaseIoError(throwable: Throwable) : DatabaseError()
}

sealed class DatastoreError() : DataError {
    class DatastoreMapperError() : DatastoreError()
    class DatastoreIoError(throwable: Throwable) : DatastoreError()
}

sealed class NetworkError() : DataError {
    class NetworkMapperError() : NetworkError()
    class NetworkIoError(throwable: Throwable) : NetworkError()
}

sealed class RemoteConfigError() : DataError {
    class RemoteConfigMapperError() : RemoteConfigError()
    class RemoteConfigIoError(throwable: Throwable) : RemoteConfigError()
}

