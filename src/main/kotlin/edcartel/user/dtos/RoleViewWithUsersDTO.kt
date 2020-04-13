package edcartel.user.dtos

import edcartel.user.entities.RoleEnum
import java.io.Serializable
import java.util.*

data class RoleViewWithUsersDTO(

    val id : UUID,

    val name : RoleEnum,

    val description : String,

    val users : Collection<UUID> = setOf()

) : Serializable