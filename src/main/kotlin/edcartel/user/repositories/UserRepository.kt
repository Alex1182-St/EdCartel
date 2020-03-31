package edcartel.user.repositories

import edcartel.user.entities.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<UserEntity, UUID> {

    fun getById(id : UUID) : UserEntity?

    fun getByUsername(username : String)  : UserEntity?

    fun removeById(id : UUID) : Boolean

}