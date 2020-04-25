package edcartel.user.dtos.converters

import edcartel.user.dtos.UserDetailsDTO
import edcartel.user.dtos.output.UserOutput
import edcartel.user.entities.UserEntity

fun UserEntity.toDetailsDTO() = UserDetailsDTO(
    id                      = id,
    username                = username,
    password                = password,
    authorities             = roles.map { it.name },
    isEnabled               = isEnabled,
    isAccountNonLocked      = isAccountNonLocked,
    isAccountNonExpired     = isAccountNonExpired,
    isCredentialsNonExpired = isCredentialsNonExpired
)

fun UserEntity.toOutput() = UserOutput(
    id                      = id,
    username                = username,
    roles                   = roles.map { it.id },
    isEnabled               = isEnabled,
    isAccountNonLocked      = isAccountNonLocked,
    isAccountNonExpired     = isAccountNonExpired,
    isCredentialsNonExpired = isCredentialsNonExpired,

    name                    = name,
    familyName              = familyName,
    aboutSelf               = aboutSelf,
    gender                  = gender,
    email                   = email,
    phone                   = phone,
    skype                   = skype,
    git                     = git
)

