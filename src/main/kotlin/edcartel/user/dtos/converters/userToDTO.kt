package edcartel.user.dtos.converters

import edcartel.user.dtos.UserDetailsDTO
import edcartel.user.dtos.UserViewDTO
import edcartel.user.entities.UserEntity

fun UserEntity.toViewDTO() : UserViewDTO = UserViewDTO(
    id = id,
    username  = username,
    roles     = roles.map { it.id },
    isEnabled = isEnabled,
    isAccountNonLocked  = isAccountNonLocked,
    isAccountNonExpired = isAccountNonExpired,
    isCredentialsNonExpired = isCredentialsNonExpired
)

fun UserEntity.toDetailsDTO() : UserDetailsDTO = UserDetailsDTO(
    id = id,
    username = username,
    password = password,
    authorities = roles.map { it.name },
    isEnabled   = isEnabled,
    isAccountNonLocked  = isAccountNonLocked,
    isAccountNonExpired = isAccountNonExpired,
    isCredentialsNonExpired = isCredentialsNonExpired
)