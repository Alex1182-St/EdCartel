package edcartel.user.requests

import edcartel.user.entities.RoleEnum

data class CreateUserCred(
    val userName: String,
    val passWord: String,
    val firstName: String,
    val familyName: String,
    val roles: MutableSet<RoleEnum>
)