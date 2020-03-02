package edcartel.user.entities

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "roles")
data class RoleEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID? = null,

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    val name: RoleEnum? = null,

    val description: String? = null

)