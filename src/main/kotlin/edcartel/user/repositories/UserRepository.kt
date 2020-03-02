package edcartel.user.repositories

import edcartel.user.entities.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<UserEntity, UUID> {

    fun findByUsername(username: String): Optional<UserEntity>

    fun removeById(id: UUID): Boolean

}