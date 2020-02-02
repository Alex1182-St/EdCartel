package edcartel.user

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import edcartel.user.entities.User
import edcartel.user.repositories.UserRepository
import org.springframework.stereotype.Service

@Service
class Query(

    val userRepo: UserRepository

) : GraphQLQueryResolver {

    fun getUserById(id: Long): User {
        return userRepo.findById(id)
            .orElseThrow()
    }

}