package edcartel.user.services

import edcartel.user.entities.UserEntity
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails


class UserDetailsImpl(private val user: UserEntity) : UserDetails {

    override fun isEnabled(): Boolean = user.enabled

    override fun getUsername(): String = user.userName

    override fun getPassword(): String = user.passWord

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> =
        user.roles.map { it.name as GrantedAuthority }
            .toMutableSet()

    override fun isAccountNonLocked(): Boolean = user.accountNonLocked

    override fun isAccountNonExpired(): Boolean = user.accountNonExpired

    override fun isCredentialsNonExpired(): Boolean = user.credentialsNonExpired

}
