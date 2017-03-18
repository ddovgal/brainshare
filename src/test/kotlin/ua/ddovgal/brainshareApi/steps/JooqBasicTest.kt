package ua.ddovgal.brainshareApi.steps

import org.jooq.DSLContext
import org.jooq.types.UInteger
import org.junit.After
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.dao.DataAccessException
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import ua.ddovgal.brainshareApi.jooq.tables.TUserStatus.USER_STATUS
import ua.ddovgal.brainshareApi.jooq.tables.pojos.UserStatus

@Suppress("SpringKotlinAutowiredMembers")
@RunWith(SpringRunner::class)
@SpringBootTest
@ActiveProfiles("default")
class JooqBasicTest {

    @Autowired lateinit var jooq: DSLContext
    private val TESTED_USER_STATUS_ID = 127

    @After fun cleanup() {
        jooq.deleteFrom(USER_STATUS).where(USER_STATUS.ID.eq(UInteger.valueOf(TESTED_USER_STATUS_ID))).execute()
    }

    @Test fun test_that_injection_successful() {
        assertNotNull("Spring cant inject DSLContext", jooq)
    }

    @Test fun test_inserting_and_deleting() {
        val inUserStatusRows = jooq.fetchCount(USER_STATUS)
        val newRecord = jooq.newRecord(USER_STATUS, UserStatus(UInteger.valueOf(TESTED_USER_STATUS_ID), "test", "test", 1212))
        newRecord.store()
        assertEquals("Jooq didn't insert into USER_STATUS table", inUserStatusRows + 1, jooq.fetchCount(USER_STATUS))
        newRecord.delete()
        assertEquals("Jooq didn't delete from USER_STATUS table", inUserStatusRows, jooq.fetchCount(USER_STATUS))
    }

    @Test(expected = DataAccessException::class) fun test_DataAccessException_throwing() {
        var newRecord = jooq.newRecord(USER_STATUS, UserStatus(UInteger.valueOf(TESTED_USER_STATUS_ID), "test", "test", 1212))
        newRecord.store()
        newRecord = jooq.newRecord(USER_STATUS, UserStatus(UInteger.valueOf(TESTED_USER_STATUS_ID), "test1", "test1", 1212 + 1))
        newRecord.store()
    }

    @Test fun test_rollback() {
        val inUserStatusRows = jooq.fetchCount(USER_STATUS)
        var rollback = false
        try {
            jooq.transaction { _ ->
                jooq.insertInto(USER_STATUS)
                        .set(USER_STATUS.ID, UInteger.valueOf(TESTED_USER_STATUS_ID))
                        .set(USER_STATUS.STATUS_NAME, "test")
                        .set(USER_STATUS.DESCRIPTION, "test")
                        .set(USER_STATUS.LOW_BORDER, 1212)
                        .execute()
                jooq.insertInto(USER_STATUS)
                        .set(USER_STATUS.ID, UInteger.valueOf(TESTED_USER_STATUS_ID))
                        .set(USER_STATUS.STATUS_NAME, "test1")
                        .set(USER_STATUS.DESCRIPTION, "test1")
                        .set(USER_STATUS.LOW_BORDER, 1212 + 1)
                        .execute()
                Assert.fail("Jooq saved 2 records with same ID. But must not. There must be a DataAccessException")
            }
        } catch(e: DataAccessException) {
            rollback = true
        }
        assertTrue("There wasn't DataAccessException, so rollback didn't done", rollback)
        assertEquals("Rollback after bad query in transaction don't working", inUserStatusRows, jooq.fetchCount(USER_STATUS))
    }
}