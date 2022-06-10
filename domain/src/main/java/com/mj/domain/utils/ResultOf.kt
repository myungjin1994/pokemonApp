package com.mj.domain.utils

sealed class ResultOf<out R> {

    data class Success<out T>(val data: T) : ResultOf<T>()
    data class Error(val exception: Exception) : ResultOf<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}

fun <T> ResultOf<T>.getValue(): T {

    return if(this is ResultOf.Success) {
        this.data
    } else {
        throw (this as ResultOf.Error).exception
    }
}

val ResultOf<*>.succeeded
    get() = this is ResultOf.Success && data != null
