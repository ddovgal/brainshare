package ua.ddovgal.brainshareApi.repository.impl

import org.jooq.DSLContext
import org.jooq.Record3
import org.jooq.types.UInteger
import org.jooq.types.ULong
import org.springframework.stereotype.Repository
import ua.ddovgal.brainshareApi.jooq.tables.User.USER
import ua.ddovgal.brainshareApi.jooq.tables.UserStatus.USER_STATUS
import ua.ddovgal.brainshareApi.jooq.tables.pojos.User
import ua.ddovgal.brainshareApi.repository.UserRepository

@Repository
class UserRepositoryImpl(val jooq: DSLContext) : UserRepository {

    override fun findByLoginGetStatusIdAndPassword(login: String): Record3<String, UInteger, String>? {
        return jooq
                .select(USER.LOGIN, USER.STATUS_ID, USER.PASSWORD)
                .from(USER)
                .where(USER.LOGIN.eq(login)).fetchOne()
    }

    override fun existsByEmail(email: String): Boolean = jooq.fetchExists(USER, USER.EMAIL.eq(email))

    override fun existsByLogin(login: String): Boolean = jooq.fetchExists(USER, USER.LOGIN.eq(login))

    override fun save(user: User): User {
        // need next line because in MySQL we can't define default value for JSON column. So need to set it to "{}" manually
        user.socialLinksJson ?: user.apply { socialLinksJson = "{}" }
        val userRecord = jooq.newRecord(USER, user)
        userRecord.store()
        return userRecord.into(user)
    }

    override fun findByLoginGetId(login: String): ULong?
            = jooq.select(USER.ID).from(USER).where(USER.LOGIN.eq(login)).fetchOneInto(ULong::class.java)

    override fun updateByIdSetStatusId(userId: ULong, statusId: UInteger) {
        jooq
                .update(USER.innerJoin(USER_STATUS).on(USER.STATUS_ID.eq(USER_STATUS.ID)))
                .set(USER.STATUS_ID, statusId)
                .set(USER.RATIO, USER_STATUS.LOW_BORDER).where(USER.ID.eq(userId)).execute()
    }

    override fun findByIdGetEmail(userId: ULong): String?
            = jooq.select(USER.EMAIL).from(USER).where(USER.ID.eq(userId)).fetchOneInto(String::class.java)
}