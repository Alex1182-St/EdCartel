package edcartel.user.repositories

import edcartel.user.entities.RoleEntity
import edcartel.user.entities.RoleEnum
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface RoleRepository : JpaRepository<RoleEntity, UUID> {

    fun findByName(name: RoleEnum): RoleEntity

}