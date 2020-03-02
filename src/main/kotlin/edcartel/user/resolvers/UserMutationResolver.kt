package edcartel.user.resolvers

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import edcartel.user.entities.UserEntity
import edcartel.user.repositories.RoleRepository
import edcartel.user.repositories.UserRepository
import edcartel.user.requests.CreateUserCred
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class UserMutationResolver(

    val userRepo: UserRepository,

    val roleReo: RoleRepository

) : GraphQLMutationResolver {

    fun userCreate(newUser: CreateUserCred) : UserEntity {
        return userRepo.save(
            UserEntity(
                username = newUser.userName,
                password = newUser.passWord,
                firstName = newUser.firstName,
                familyName = newUser.familyName,
                roles = newUser.roles.map { roleReo.findByName(it) }.toMutableSet()
            )
        )
    }

    fun userRemove(id: UUID) : Boolean {
        return userRepo.removeById(id)
    }
}