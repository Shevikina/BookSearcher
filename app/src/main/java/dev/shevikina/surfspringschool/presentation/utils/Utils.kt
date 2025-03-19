package dev.shevikina.surfspringschool.presentation.utils

import dev.shevikina.surfspringschool.data.utils.OperationResult

suspend fun <T> OperationResult<T>.handle(
    onSuccess: (data: T) -> Unit,
    onError: (message: String) -> Unit = {},
    onLoading: () -> Unit = {},
) {
    when (this) {
        is OperationResult.Error -> {
            onError(message)
        }

        OperationResult.Loading -> {
            onLoading()
        }

        is OperationResult.Success -> {
            onSuccess(data)
        }
    }
}