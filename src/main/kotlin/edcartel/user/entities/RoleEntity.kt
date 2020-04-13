package edcartel.user.entities

import java.util.*
import javax.persistence.*


@Entity
@Table(name = "roles")
data class RoleEntity(

    @field:Id
    @field:GeneratedValue(strategy = GenerationType.AUTO)
    @field:Column(updatable = false, nullable = false)
    val id : UUID = UUID.randomUUID(),

    @field:Enumerated(EnumType.STRING)
    @field:Column(unique = true, nullable = false)
    val name : RoleEnum = RoleEnum.USER,

    @field:Column(nullable = false)
    val description : String = "",

    @field:ManyToMany(
        fetch = FetchType.LAZY,
        cascade = [CascadeType.PERSIST, CascadeType.MERGE],
        mappedBy = "roles"
    )
    val users : Collection<UserEntity> = setOf()

)