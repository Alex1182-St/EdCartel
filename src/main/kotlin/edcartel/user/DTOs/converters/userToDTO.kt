package edcartel.user.DTOs.converters

import edcartel.user.DTOs.UserDetailsTDO
import edcartel.user.DTOs.UserViewTDO
import edcartel.user.entities.UserEntity

fun UserEntity.toViewTDO() = UserViewTDO(

    id = id!!,

    username = username,

    password = password,

    roles = roles.map { it.toViewTDO() },

    isEnabled = isEnabled,

    isAccountNonLocked = isAccountNonLocked,

    isAccountNonExpired = isAccountNonExpired,

    isCredentialsNonExpired = isCredentialsNonExpired

)

fun UserEntity.toDetailsTDO() = UserDetailsTDO(

    id = id!!,

    username = username,

    password = password,

    authorities = roles.map { it.name },

    isEnabled = isEnabled,

    isAccountNonLocked = isAccountNonLocked,

    isAccountNonExpired = isAccountNonExpired,

    isCredentialsNonExpired = isCredentialsNonExpired

)