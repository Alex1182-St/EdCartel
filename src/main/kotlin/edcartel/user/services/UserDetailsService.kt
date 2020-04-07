package edcartel.user.services

import edcartel.user.DTOs.UserDetailsTDO
import edcartel.user.DTOs.converters.toDetailsTDO
import edcartel.user.repositories.UserRepository
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class UserDetailsService(val userRepo : UserRepository) : UserDetailsService {

    /**
     * Find the user based on the username. In the current implementation,
     * the search can be case-sensitive or case-insensitive, depending on how the implementation instance is configured.
     * The returning `UserDetails' object may have a user name that is different from the one actually requested.
     *
     * @param username the username identifying the user whose data is required.
     *
     * @return a fully populated user record (never `null`)
     *
     * @throws UsernameNotFoundException if the user could not be found, or the user has no GrantedAuthority
     */
    override fun loadUserByUsername(username : String) : UserDetailsTDO {
        val user = userRepo.getByUsername(username)
            .orElseThrow { UsernameNotFoundException("User not found with username: $username") }

        return user.toDetailsTDO()
    }
}