package edcartel.user.DTOs.converters

import edcartel.user.DTOs.RoleViewDTO
import edcartel.user.DTOs.RoleViewWithUsersDTO
import edcartel.user.entities.RoleEntity

fun RoleEntity.toViewDTO() = RoleViewDTO(
    id = id!!,
    name = name,
    description = description
)

fun RoleEntity.toViewWithUsersDTO() = RoleViewWithUsersDTO(
    id = id!!,
    name = name,
    description = description,
    users = this.users.map { it.id }
)