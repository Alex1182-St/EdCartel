package edcartel.user.services

import edcartel.user.repositories.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service()
class UserDetailsServiceImpl(

    val userRepo: UserRepository

) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails =
        UserDetailsImpl(
            userRepo.findByUserName(username)
                .orElseThrow()
        )

}