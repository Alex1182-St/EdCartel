package edcartel.user.repositories

import edcartel.user.entities.UserEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : CrudRepository<UserEntity, UUID> {

    fun findByUserName(userName: String): Optional<UserEntity>

}