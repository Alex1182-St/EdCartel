package edcartel.user.dtos

import edcartel.user.entities.RoleEnum
import java.io.Serializable
import java.util.*

data class RoleViewDTO(

    val id : UUID,

    val name : RoleEnum,

    val description : String

) : Serializable