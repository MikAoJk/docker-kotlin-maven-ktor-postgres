package services

import db.DatabaseInterface
import db.validData
import kotlinx.coroutines.GlobalScope


class ValidateService() {
    fun executeValidateData(validationData: ValidationData, database: DatabaseInterface): ValidationResult =
        with(GlobalScope) {
            if (database.validData(validationData.data)) {
                return ValidationResult("OK")
            }
            return ValidationResult("INVALID")
        }
}

data class ValidationData(
    val data: String
)

data class ValidationResult(
    val result: String
)

