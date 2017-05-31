package ua.ddovgal.brainshareApi.repository

import org.jooq.Record3
import org.jooq.types.UInteger
import org.jooq.types.ULong
import ua.ddovgal.brainshareApi.jooq.tables.pojos.User

interface UserRepository {
    /**
     * Searches for user by it's login and returns login, status name and user's password(encrypted)
     * @param login login to search by
     * @return null if there is no user with such login
     * @return record of login, status name and user's password(encrypted)
     */
    fun findByLoginGetStatusIdAndPassword(login: String): Record3<String, UInteger, String>?

    /**
     * Checks, if user with such email exists
     * @param email email to search by
     * @return true, if user exists
     */
    fun existsByEmail(email: String): Boolean

    /**
     * Checks, if user with such login exists
     * @param login login to search by
     * @return true, if user exists
     */
    fun existsByLogin(login: String): Boolean

    /**
     * Just saves user and return it after inserting into table
     * @param user user to save
     * @return same user, but after insertion. So, with possible autogenerated set fields
     */
    fun save(user: User): User

    /**
     * Searches for user by it's login and returns only it's id
     * @param login login to search by
     * @return null if there is no user with such login
     * @return id for user with such login
     */
    fun findByLoginGetId(login: String): ULong?

    /**
     * Updates user's status. (Uses when confirms registration)
     * @param userId id of user, for which must perform update
     * @param statusId id of new status
     */
    fun updateByIdSetStatusId(userId: ULong, statusId: UInteger)

    /**
     * Searches for user by it's id and returns only it's email
     * @param userId id to search by
     * @return null if there is no user with such login
     * @return email for user with such id
     */
    fun findByIdGetEmail(userId: ULong): String?
}