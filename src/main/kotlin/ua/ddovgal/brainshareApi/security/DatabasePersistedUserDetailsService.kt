package ua.ddovgal.brainshareApi.security

import org.jooq.types.UInteger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import ua.ddovgal.brainshareApi.jooq.tables.User.USER
import ua.ddovgal.brainshareApi.model.UserRole
import ua.ddovgal.brainshareApi.model.UserStatus
import ua.ddovgal.brainshareApi.repository.UserRepository

@Service
class DatabasePersistedUserDetailsService : UserDetailsService {

    @Autowired private lateinit var repository: UserRepository

    override fun loadUserByUsername(login: String): UserDetails {
        repository.findByLoginGetStatusIdAndPassword(login)?.let {
            val statusId = it[USER.STATUS_ID]
            val password = it[USER.PASSWORD]
            if (statusId != UInteger.valueOf(UserStatus.UNCHECKED.id))
            // TODO: make more logical\correct authority extradition, depending on the status
                return User(login, password, setOf(SimpleGrantedAuthority(UserRole.ROLE_SIMPLE_USER.name)))
        }
        throw UsernameNotFoundException("User with name [$login] not found")
    }
}