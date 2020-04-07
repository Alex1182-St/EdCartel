package edcartel.user.entities

import java.util.*
import javax.persistence.*


@Entity
@Table(name = "roles")
data class RoleEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    val id : UUID? = null,

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    val name : RoleEnum = RoleEnum.USER,

    @Column(nullable = false)
    val description : String = "",

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
    val users : Collection<UserEntity> = setOf()

)