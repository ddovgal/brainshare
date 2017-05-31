package ua.ddovgal.brainshareApi.service.impl

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ua.ddovgal.brainshareApi.jooq.tables.pojos.User
import ua.ddovgal.brainshareApi.repository.UserRepository
import ua.ddovgal.brainshareApi.service.DataValidationService
import javax.mail.internet.AddressException
import javax.mail.internet.InternetAddress

@Service
@Transactional(readOnly = true)
class BasicDataValidationServiceImpl(val userRepository: UserRepository) : DataValidationService {

    override fun validateUserData(user: User): Map<String, String> {
        val errors = mutableMapOf<String, String>()

        if (user.email == null) errors.put("email", "Email is not provided")
        else if (userRepository.existsByEmail(user.email)) errors.put("email", "Such email is already exists")
        else if (!isValidEmailAddress(user.email)) errors.put("email", "Such email is not valid")

        if (user.login == null) errors.put("login", "Login is not provided")
        else if (userRepository.existsByLogin(user.login)) errors.put("login", "Such login is already exists")
        else if (!isAcceptableLogin(user.login)) errors.put("login", "Such email is not valid. " +
                "It can't contain spaces or any of these character: \"'@#$%^&.+=")

        if (user.password == null) errors.put("password", "Password is not provided")
        else if (!isAcceptablePasswordComplexity(user.password)) errors.put("password", "The password is not complex enough. " +
                "Demands to password: a digit must occur at least once; a lower case letter must occur at least once; " +
                "an upper case letter must occur at least once; no whitespace allowed in the entire string " +
                "length must be at last 8 characters.")

        return errors
    }

    private fun isAcceptablePasswordComplexity(password: String): Boolean
            = password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$".toRegex())

    //must not contain dot in any way, because it affects some logic of another method
    private fun isAcceptableLogin(login: String): Boolean
            = login.matches("^((?![\"'@#$%^&.+=\\s]).)*$".toRegex())

    private fun isValidEmailAddress(email: String): Boolean {
        var result = true
        try {
            InternetAddress(email).validate()
        } catch (ex: AddressException) {
            result = false
        }

        return result
    }
}