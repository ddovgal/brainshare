package ua.ddovgal.brainshareApi.service.impl

import org.jooq.types.UInteger
import org.jooq.types.ULong
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ua.ddovgal.brainshareApi.configuration.WebProperties
import ua.ddovgal.brainshareApi.exception.InvalidDataProvidedException
import ua.ddovgal.brainshareApi.jooq.tables.pojos.User
import ua.ddovgal.brainshareApi.model.UserStatus
import ua.ddovgal.brainshareApi.repository.UserRepository
import ua.ddovgal.brainshareApi.service.DataValidationService
import ua.ddovgal.brainshareApi.service.MailSenderService
import ua.ddovgal.brainshareApi.service.UserRegistrationService
import java.sql.Date
import java.time.LocalDate
import java.util.*

@Service
@Transactional
class UserRegistrationServiceImpl(
        private val validationService: DataValidationService,
        private val userRepository: UserRepository,
        private val mailSenderService: MailSenderService,
        private val webProperties: WebProperties
) : UserRegistrationService {

    private val bCryptEncoder = BCryptPasswordEncoder()
    private val confirmationGetRequestURL = "/register"

    override fun registerUser(user: User) {
        val errors = validationService.validateUserData(user)
        if (errors.isNotEmpty()) throw InvalidDataProvidedException("User have invalid data", errors)

        user.password = bCryptEncoder.encode(user.password)
        user.registrationDate = Date.valueOf(LocalDate.now())

        val generatedId = userRepository.save(user).id
        val confirmationHash = Base64.getEncoder().encodeToString("$generatedId.${user.login}".toByteArray())
        val userConfirmationLink = "${webProperties.protocol}://${webProperties.rootUrl}$confirmationGetRequestURL" +
                "?confirm=$confirmationHash"

        val receiverName = if (user.firstName != null) user.firstName + user.lastName.orEmpty() else user.login
        val messageText = "Dear, $receiverName, we a happy to see you on Brainshare ! " +
                "To complete registration, please visit $userConfirmationLink"
        mailSenderService.sendSimpleMessage(user.email, receiverName, "Confirm registration", messageText)
    }

    override fun confirmRegistration(hash: String) {
        val decryptedParts = String(Base64.getDecoder().decode(hash)).split(".")

        val idFromHash: ULong? by lazy {
            var result: ULong? = null
            try {
                result = ULong.valueOf(decryptedParts[0])
            } catch (e: NumberFormatException) {
            }
            return@lazy result
        }
        if (decryptedParts.size != 2 || idFromHash == null)
            throw InvalidDataProvidedException("Invalid value of confirmation hash parameter", mapOf("hash" to hash))

        val loginFromHash = decryptedParts[1]
        val idForLogin = userRepository.findByLoginGetId(loginFromHash)

        if (idForLogin == null || idForLogin != idFromHash)
            throw InvalidDataProvidedException("Value of confirmation hash parameter has incorrect data", mapOf("hash" to hash))

        val statusId = UInteger.valueOf(UserStatus.NORMAL.id)
        userRepository.updateByIdSetStatusId(idFromHash, statusId)

        val userEmail = userRepository.findByIdGetEmail(idFromHash)!!
        val messageText = "Registration complete\nCongratulations, you are with brainshare now !"
        mailSenderService.sendSimpleMessage(userEmail, loginFromHash, "Registration complete", messageText)
    }

}