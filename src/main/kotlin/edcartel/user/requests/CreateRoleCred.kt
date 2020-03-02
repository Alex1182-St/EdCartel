package edcartel.user.requests

import edcartel.user.entities.RoleEnum

data class CreateRoleCred(

    val name: RoleEnum,

    val descriprion: String?

)