package edcartel.user.DTOs.converters

import edcartel.user.DTOs.UserDetailsDTO
import edcartel.user.DTOs.UserViewDTO
import edcartel.user.entities.UserEntity

fun UserEntity.toViewDTO() = UserViewDTO(
    id = id!!,
    username = username,
    password = password,
    roles = roles.map { it.toViewDTO() },
    isEnabled = isEnabled,
    isAccountNonLocked = isAccountNonLocked,
    isAccountNonExpired = isAccountNonExpired,
    isCredentialsNonExpired = isCredentialsNonExpired
)

fun UserEntity.toDetailsDTO() = UserDetailsDTO(
    id = id!!,
    username = username,
    password = password,
    authorities = roles.map { it.name },
    isEnabled = isEnabled,
    isAccountNonLocked = isAccountNonLocked,
    isAccountNonExpired = isAccountNonExpired,
    isCredentialsNonExpired = isCredentialsNonExpired
)