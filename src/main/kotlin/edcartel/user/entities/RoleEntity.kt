package edcartel.user.entities

import java.util.*
import javax.persistence.*

@Table(name = "roles")
@Entity
data class RoleEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID? = null,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val name: RoleEnum,

    val description: String? = null

)