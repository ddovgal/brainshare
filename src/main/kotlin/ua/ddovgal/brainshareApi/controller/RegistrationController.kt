package ua.ddovgal.brainshareApi.controller

import org.springframework.web.bind.annotation.*
import ua.ddovgal.brainshareApi.jooq.tables.pojos.User
import ua.ddovgal.brainshareApi.service.DataValidationService
import ua.ddovgal.brainshareApi.service.UserRegistrationService
import java.time.Instant

@RestController
@RequestMapping("/register")
class RegistrationController(val validationService: DataValidationService, val registrationService: UserRegistrationService) {

    data class ValidationResponse(
            val timestamp: Long = Instant.now().toEpochMilli(),
            val result: String,
            val errors: Map<String, String>
    )

    @PostMapping("/validate")
    fun validate(@RequestBody user: User): ValidationResponse {
        val errors = validationService.validateUserData(user)
        return if (errors.isEmpty()) ValidationResponse(result = "OK", errors = errors)
        else ValidationResponse(result = "Contains errors", errors = errors)
    }

    @PostMapping
    fun register(@RequestBody user: User) = registrationService.registerUser(user)

    @GetMapping
    fun confirmRegistration(@RequestParam("confirm") confirmationHash: String)
            = registrationService.confirmRegistration(confirmationHash)

    // TODO: make confirmation process like this
    /*@GetMapping
    fun confirmRegistration(@RequestParam("confirm") confirmationHash: String) {
        // proxy stage of confirmation
        // could check at this stage if hash is correct\valid\active
        // if all ok -> just response to user some form that can perform POST(linkWithHash)
    }

    @PostMapping
    fun confirmRegistration(@RequestParam("confirm") confirmationHash: String)
            = registrationService.confirmRegistration(confirmationHash)*/

}