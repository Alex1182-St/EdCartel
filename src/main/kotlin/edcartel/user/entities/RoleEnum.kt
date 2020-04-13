package edcartel.user.entities

import org.springframework.security.core.GrantedAuthority
import org.springframework.stereotype.Service

@Service
enum class RoleEnum : GrantedAuthority {

    USER, STUDENT, TEACHER, ADMIN;

    override fun getAuthority() : String = "ROLE_${name}"

}
