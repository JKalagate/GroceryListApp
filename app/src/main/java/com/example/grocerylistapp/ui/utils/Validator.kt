package com.example.grocerylistapp.ui.utils

object Validator {

    fun validateEmptyNameValue(name: String, productValue: String): ValidationResult {
        return ValidationResult(
            (name.isNotEmpty() && productValue.isNotEmpty())
        )
    }

    fun validateProductValue(productValue: String): ValidationResult {
        val regex = "^[0-9.]+\$".toRegex()
        return ValidationResult(
            (regex.matches(productValue))
        )
    }

}

data class ValidationResult(
    val status: Boolean = false
)

data class ProductValueUIState(
    val nameProductValueNotEmpty: Boolean = false,
    val productValueError: Boolean = false
)