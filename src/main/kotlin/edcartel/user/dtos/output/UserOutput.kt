package edcartel.user.dtos.output

import java.io.Serializable
import java.util.*
import javax.persistence.Column
import javax.persistence.Lob

data class UserOutput(

    val id : UUID,

    val username : String,

    val roles : Collection<UUID>,

    val isEnabled : Boolean,

    val isAccountNonLocked : Boolean,

    val isAccountNonExpired : Boolean,

    val isCredentialsNonExpired : Boolean,


    val name : String,

    val familyName : String,

    val aboutSelf : String?,

    val gender : Boolean?,

    val email : String?,

    val phone : String?,

    val skype : String?,

    val git : String?

) : Serializable