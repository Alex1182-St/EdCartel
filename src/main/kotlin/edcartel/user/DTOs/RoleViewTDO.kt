package edcartel.user.DTOs

import edcartel.user.entities.RoleEnum
import java.io.Serializable
import java.util.*

data class RoleViewTDO(

    val id : UUID,

    val name : RoleEnum,

    val description : String = ""

) : Serializable