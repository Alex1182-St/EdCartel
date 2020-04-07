package edcartel.user.DTOs.converters

import edcartel.user.DTOs.RoleViewTDO
import edcartel.user.DTOs.RoleViewWithUsersTDO
import edcartel.user.entities.RoleEntity


fun RoleEntity.toViewTDO() = RoleViewTDO(

    id = id!!,

    name = name,

    description = description

)

fun RoleEntity.toViewWithUsersTDO() = RoleViewWithUsersTDO(

    id = id!!,

    name = name,

    description = description,

    users = this.users.map { it.id }

)