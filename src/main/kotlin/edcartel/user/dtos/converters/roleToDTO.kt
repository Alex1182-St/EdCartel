package edcartel.user.dtos.converters

import edcartel.user.dtos.output.RoleOutput
import edcartel.user.entities.RoleEntity

fun RoleEntity.toOutput() : RoleOutput = RoleOutput(
    id          = id,
    name        = name,
    description = description
)
