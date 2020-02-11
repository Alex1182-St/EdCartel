package edcartel.user.resolvers

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import edcartel.user.repositories.RoleRepository
import edcartel.user.repositories.UserRepository
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

@Transactional
@Service
class UserQueryResolver(

    val userRepo: UserRepository,

    val roleReo: RoleRepository

) : GraphQLQueryResolver {

    fun findAllUser() =
        userRepo.findAll()

    fun findUserByID(id: UUID) =
        userRepo.findById(id)
            .orElseThrow()

}