package ua.ddovgal.brainshareApi.steps

import org.jooq.DSLContext
import org.jooq.types.UInteger
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringRunner
import ua.ddovgal.brainshareApi.BrainshareApiApplication
import ua.ddovgal.brainshareApi.jooq.tables.TUserStatus
import ua.ddovgal.brainshareApi.jooq.tables.pojos.UserStatus

@RunWith(SpringRunner::class)
@TestPropertySource("/application.properties")
@ContextConfiguration(classes = arrayOf(BrainshareApiApplication::class))
class JooqBasic {

    @Autowired lateinit var jooq: DSLContext

    @Test
    fun test_that_injection_successful() {
        assertNotNull("Spring cant inject DSLContext", jooq)
    }

    @Test
    fun test_inserting() {
        val inUserStatusRows = jooq.fetchCount(TUserStatus.USER_STATUS)
        val newRecord = jooq.newRecord(TUserStatus.USER_STATUS, UserStatus(UInteger.valueOf(127), "test", "test", 127))
        newRecord.store()
        assertEquals("Jooq didn't insert into USER_STATUS table", inUserStatusRows + 1, jooq.fetchCount(TUserStatus.USER_STATUS))
        newRecord.delete()
        assertEquals("Jooq didn't delete from USER_STATUS table", inUserStatusRows, jooq.fetchCount(TUserStatus.USER_STATUS))
    }
}