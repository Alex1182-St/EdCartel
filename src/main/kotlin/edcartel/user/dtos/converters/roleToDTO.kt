package edcartel.user.dtos.converters

import edcartel.user.dtos.RoleViewDTO
import edcartel.user.dtos.RoleViewWithUsersDTO
import edcartel.user.entities.RoleEntity

fun RoleEntity.toViewDTO() : RoleViewDTO = RoleViewDTO(
    id = id,
    name = name,
    description = description
)

fun RoleEntity.toViewWithUsersDTO() : RoleViewWithUsersDTO = RoleViewWithUsersDTO(
    id = id,
    name = name,
    description = description,
    users = users.map { it.id }
)