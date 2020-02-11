package edcartel.user.repositories

import edcartel.user.entities.RoleEntity
import edcartel.user.entities.RoleEnum
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface RoleRepository : CrudRepository<RoleEntity, UUID> {

    fun findByName(name: RoleEnum): RoleEntity

}