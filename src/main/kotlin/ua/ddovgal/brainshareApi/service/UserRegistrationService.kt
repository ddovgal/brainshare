package ua.ddovgal.brainshareApi.service

import ua.ddovgal.brainshareApi.exception.InvalidDataProvidedException
import ua.ddovgal.brainshareApi.jooq.tables.pojos.User

interface UserRegistrationService {
    /**
     * Register user. Validation included. Only valid user will be registered.
     * Also sends confirmation mail to user to confirm his account.
     * @param user user that tries to register
     * @throws InvalidDataProvidedException if user provided data is not valid
     */
    fun registerUser(user: User)

    /**
     * Finalize registration procedure. Checks for hash correctness.
     * If so, updates user's state to NORMAL and ratio from -2147483647 to 0.
     * Also notifies user about registration complete
     */
    fun confirmRegistration(hash: String)
}