package edcartel.user.entities

import java.util.*
import javax.persistence.*

@Table(name = "users")
@Entity
data class UserEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    val id: UUID? = null,


    @Column(unique = true, nullable = false)
    val userName: String,

    @Column(nullable = false)
    val passWord: String,

    @ManyToMany(
        fetch = FetchType.LAZY,
        cascade = arrayOf(
            CascadeType.ALL, CascadeType.PERSIST
        )
    )
    @JoinTable(
        name = "users_roles",
        joinColumns = arrayOf(
            JoinColumn(name = "user_id", referencedColumnName = "id")
        ),
        inverseJoinColumns = arrayOf(
            JoinColumn(name = "role_id", referencedColumnName = "id")
        )
    )
    val roles: MutableSet<RoleEntity>,

    @Column(nullable = false)
    val enabled: Boolean = true,

    @Column(nullable = false)
    val accountNonLocked: Boolean = true,

    @Column(nullable = false)
    val accountNonExpired: Boolean = true,

    @Column(nullable = false)
    val credentialsNonExpired: Boolean = true,


    @Column(nullable = false)
    val firstName: String,

    @Column(nullable = false)
    val familyName: String,

    @Lob
    val aboutSelf: String? = null,

    val gender: Boolean? = null,


    @Column(unique = true)
    val email: String? = null,

    @Column(unique = true)
    val phone: String? = null,


    val skype: String? = null,

    val github: String? = null

)
