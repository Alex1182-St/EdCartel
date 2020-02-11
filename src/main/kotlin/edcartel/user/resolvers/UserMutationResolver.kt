package edcartel.user.resolvers

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import edcartel.user.entities.RoleEnum
import edcartel.user.entities.UserEntity
import edcartel.user.repositories.RoleRepository
import edcartel.user.repositories.UserRepository
import edcartel.user.requests.CreateUserCred
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Service
import java.security.Principal
import javax.transaction.Transactional

@Transactional
@Service
class UserMutationResolver(

    val userRepo: UserRepository,

    val roleReo: RoleRepository

) : GraphQLMutationResolver {

    @PreAuthorize("hasAuthority('ADMIN')")
    fun saveUser(newUser: CreateUserCred) =
        userRepo.save(
            UserEntity(
                userName = newUser.userName,
                passWord = newUser.passWord,
                firstName = newUser.firstName,
                familyName = newUser.familyName,
                roles = newUser.roles.map { roleReo.findByName(it) }
                    .toMutableSet()
            )
        )
}