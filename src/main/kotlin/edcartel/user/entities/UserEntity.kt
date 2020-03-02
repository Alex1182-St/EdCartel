package edcartel.user.entities

import edcartel.user.repositories.RoleRepository
import org.springframework.security.core.GrantedAuthority
import java.util.*
import javax.persistence.*


@Entity
@Table(name = "users")
data class UserEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    val id: UUID? = null,


    @Column(unique = true, nullable = false)
    val username: String = "",

    @Column(nullable = false)
    val password: String = "",

    @ManyToMany(
        fetch = FetchType.LAZY,
        cascade = arrayOf(
            CascadeType.PERSIST, CascadeType.MERGE
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
    val roles: MutableSet<RoleEntity> = mutableSetOf(),

    @Column(nullable = false)
    val isEnabled: Boolean = true,

    @Column(nullable = false)
    val isAccountNonLocked: Boolean = true,

    @Column(nullable = false)
    val isAccountNonExpired: Boolean = true,

    @Column(nullable = false)
    val isCredentialsNonExpired: Boolean = true,


    @Column(nullable = false)
    val firstName: String = "",

    @Column(nullable = false)
    val familyName: String = "",

    @Lob
    val aboutSelf: String? = null,

    val gender: Boolean? = null,


    @Column(unique = true)
    val email: String? = null,

    @Column(unique = true)
    val phone: String? = null,

    @Column(unique = true)
    val skype: String? = null,

    @Column(unique = true)
    val git: String? = null

)
