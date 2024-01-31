package gaur.himanshu.august.movies.utils

sealed class NsState{
    object Loading:NsState()
    class  Success<T>(var data:T):NsState()
    class  Failure(var throwable: Throwable):NsState()
}
