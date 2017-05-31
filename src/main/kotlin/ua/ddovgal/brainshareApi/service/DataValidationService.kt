package ua.ddovgal.brainshareApi.service

import ua.ddovgal.brainshareApi.jooq.tables.pojos.User

interface DataValidationService {
    /**
     * Validates user, that want to be created
     * @param user user, that will be checked
     * @return map or errors. If all OK, then map will be empty
     */
    fun validateUserData(user: User): Map<String, String>
}