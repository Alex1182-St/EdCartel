package edcartel.user.resolvers

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import edcartel.user.repositories.RoleRepository
import edcartel.user.repositories.UserRepository
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class UserQueryResolver(

    val userRepo: UserRepository,

    val roleReo: RoleRepository

) : GraphQLQueryResolver {

    fun users() =
        userRepo.findAll()

    @PreAuthorize("hasAuthority('ADMIN')")
    fun userById(id: UUID) =
        userRepo.findById(id)
            .orElseThrow { UsernameNotFoundException("User not found with id: $id") }

}