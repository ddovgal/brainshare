package ua.ddovgal.brainshareApi.integration

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import junitparams.JUnitParamsRunner
import junitparams.Parameters
import org.jooq.DSLContext
import org.junit.*
import org.junit.Assert.*
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.mail.javamail.JavaMailSenderImpl
import org.springframework.test.context.junit4.rules.SpringClassRule
import org.springframework.test.context.junit4.rules.SpringMethodRule
import ua.ddovgal.brainshareApi.jooq.tables.User
import ua.ddovgal.brainshareApi.model.UserStatus
import java.util.*
import javax.mail.internet.MimeMessage

typealias PUser = ua.ddovgal.brainshareApi.jooq.tables.pojos.User

@RunWith(JUnitParamsRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserRegistrationTest {

    @Autowired lateinit var restTemplate: TestRestTemplate
    @MockBean lateinit var mailSender: JavaMailSenderImpl

    @Autowired lateinit var jooq: DSLContext
    private val U = User.USER
    private val TEST_LOGIN = "test"

    // Need this two rules just for Parametrized methods
    // Java's public static final field
    companion object {
        @JvmField
        @ClassRule
        val SPRING_CLASS_RULE = SpringClassRule()
    }

    // Java's public final field
    @Rule @JvmField val springMethodRule: SpringMethodRule = SpringMethodRule()

    // TODO: more different variants, different error types
    fun parametersForTest_bad_validation() = listOf(
            mapOf("email" to "just@test.com", "login" to "bd", "password" to "as w0rd"),
            mapOf("email" to "just@test.com", "login" to "b@d", "password" to "Asw0rd")
    )

    @Before
    fun setup() {
        whenever(mailSender.send(any<MimeMessage>())).then { }
        whenever(mailSender.createMimeMessage()).thenCallRealMethod()
    }

    @After
    fun cleanup() {
        jooq.deleteFrom(U).where(U.LOGIN.eq(TEST_LOGIN)).execute()
    }

    @Test fun test_normal_validation() {
        val requestMap = mapOf("email" to "test@test.com", "login" to TEST_LOGIN, "password" to "Passw0rd")
        val responseMap = restTemplate.postForObject("/register/validate", requestMap, Map::class.java)

        assertEquals("It seems that there is some other fields", 2, responseMap.size)
        assertEquals("It seems that result is not OK", "OK", responseMap["result"])
    }

    @Test fun test_normal_registration() {
        val requestMap = mapOf("email" to "errorer.404@gmail.com", "login" to TEST_LOGIN, "password" to "Passw0rd")
        assertFalse("Tested user's data is already exists in DB(such login)", jooq.fetchExists(U, U.LOGIN.eq(TEST_LOGIN)))
        val responseEntity = restTemplate.postForEntity("/register", requestMap, Map::class.java)
        val createdUser = jooq.selectFrom(U).where(U.LOGIN.eq(TEST_LOGIN)).fetchAnyInto(PUser::class.java)

        assertNotNull("After registration user wasn't created in DB", createdUser)
        assertEquals(UserStatus.UNCHECKED.id, createdUser.statusId.toInt())
        assertEquals("It seems, that response isn't 200(OK)", 200, responseEntity.statusCodeValue)

        val captor = argumentCaptor<MimeMessage>()
        verify(mailSender).send(captor.capture())
        val expectedHash = Base64.getEncoder().encodeToString("${createdUser.id}.$TEST_LOGIN".toByteArray())
        assertTrue("Confirmation link has an invalid hash",
                (captor.firstValue.dataHandler.content as? String)?.contains(expectedHash) ?: false)
    }

    @Parameters
    @Test fun test_bad_validation(requestMap: Map<String, String>) {
        val responseMap = restTemplate.postForObject("/register/validate", requestMap, Map::class.java)
        // TODO: do more serious checking
        assertNotEquals("It seems that result is OK. But must not", "OK", responseMap["result"])
    }

    // TODO: complete tests
    /*@Test fun test_normal_confirmation() {
        // no need of hash checking(it done in test_normal_registration)
        // user exists, its status is UNCHECKED
        // retrieve user's login from hash -> aster request its's status should become NORMAL
    }

    @Test fun test_bad_confirmation() {
        // check, that representation of InvalidDataProvidedException was received
    }*/

}