package edcartel.user.entities

import org.springframework.security.core.GrantedAuthority

enum class RoleEnum : GrantedAuthority {

    USER, STUDENT, TEACHER, ADMIN;

    override fun getAuthority() : String = "ROLE_${name}"

}
