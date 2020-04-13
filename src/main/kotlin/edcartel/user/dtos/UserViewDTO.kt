package edcartel.user.dtos

import java.io.Serializable
import java.util.*

data class UserViewDTO(

    val id : UUID,

    val username : String,

    val roles : Collection<UUID>,

    val isEnabled : Boolean,

    val isAccountNonLocked : Boolean,

    val isAccountNonExpired : Boolean,

    val isCredentialsNonExpired : Boolean

) : Serializable