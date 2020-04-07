package edcartel.user.DTOs

import java.io.Serializable
import java.util.*

data class UserViewDTO(

    val id : UUID,

    val username : String,

    val password : String,

    val roles : Collection<RoleViewDTO> = setOf(),

    val isEnabled : Boolean = false,

    val isAccountNonLocked : Boolean = false,

    val isAccountNonExpired : Boolean = false,

    val isCredentialsNonExpired : Boolean = false

) : Serializable