package edcartel.user.dtos.output

import edcartel.user.entities.RoleEnum
import java.io.Serializable
import java.util.*

data class RoleOutput(

    val id : UUID,

    val name : RoleEnum,

    val description : String

) : Serializable