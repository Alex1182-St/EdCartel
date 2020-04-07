package edcartel.user.DTOs

import edcartel.user.entities.RoleEnum
import java.io.Serializable
import java.util.*

data class RoleViewWithUsersTDO(

    val id : UUID,

    val name : RoleEnum,

    val description : String = "",

    val users : Collection<UUID?> = setOf()

) : Serializable