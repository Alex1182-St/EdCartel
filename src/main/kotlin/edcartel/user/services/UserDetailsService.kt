package edcartel.user.services

import edcartel.user.entities.UserEntity
import edcartel.user.repositories.UserRepository
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserDetailsService(

    val userRepo: UserRepository

) : UserDetailsService {

    override fun loadUserByUsername(username: String) : UserDetails {
        val user = userRepo.findByUsername(username)
            .orElseThrow { UsernameNotFoundException("User not found with username: $username") }

        return User(
            user.username,
            user.password,
            user.isEnabled,
            user.isAccountNonExpired,
            user.isCredentialsNonExpired,
            user.isAccountNonLocked,
            getAuthorities(user)
        )
    }

    fun getAuthorities(user: UserEntity) : List<GrantedAuthority> {
        return user.roles.map { it.name as GrantedAuthority }
    }

}