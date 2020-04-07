package edcartel.user.DTOs

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

data class UserDetailsTDO(

    private val id : UUID,

    private val username : String,

    private val password : String,

    private val authorities : Collection<GrantedAuthority>,

    private val isEnabled : Boolean = false,

    private val isAccountNonLocked : Boolean = false,

    private val isAccountNonExpired : Boolean = false,

    private val isCredentialsNonExpired : Boolean = false

) : UserDetails {

    /**
     * Returns id the user. Cannot return `null`.
     *
     * @return the id (never `null`)
     */
    fun getId() : UUID = this.id

    /**
     * Returns the username used to authenticate the user. Cannot return `null`.
     *
     * @return the username (never `null`)
     */
    override fun getUsername() : String = this.username

    /**
     * Returns the password used to authenticate the user.
     *
     * @return the password
     */
    override fun getPassword() : String = this.password

    /**
     * Returns the authorities granted to the user. Cannot return `null`.
     *
     * @return the authorities, sorted by natural key (never `null`)
     */
    override fun getAuthorities() : Collection<GrantedAuthority> = this.authorities

    /**
     * Indicates whether the user is enabled or disabled. A disabled user cannot be authenticated.
     *
     * @return `true` if the user is enabled, `false` otherwise
     */
    override fun isEnabled() : Boolean = this.isEnabled

    /**
     * Indicates whether the user's credentials (password) has expired. Expired credentials prevent authentication.
     *
     * @return `true` if the user's credentials are valid (ie non-expired), `false` if no longer valid (ie expired)
     */
    override fun isCredentialsNonExpired() : Boolean = this.isCredentialsNonExpired

    /**
     * Indicates whether the user's account has expired. An expired account cannot be authenticated.
     *
     * @return `true` if the user's account is valid (ie non-expired), `false` if no longer valid (ie expired)
     */
    override fun isAccountNonExpired() : Boolean = this.isAccountNonExpired

    /**
     * Indicates whether the user is locked or unlocked. A locked user cannot be authenticated.
     *
     * @return `true` if the user is not locked, `false` otherwise
     */
    override fun isAccountNonLocked() : Boolean = this.isAccountNonLocked
}