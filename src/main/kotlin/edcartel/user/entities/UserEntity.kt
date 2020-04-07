package edcartel.user.entities

import org.hibernate.validator.constraints.Length
import java.io.Serializable
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Max
import javax.validation.constraints.Min


@Entity
@Table(name = "users")
data class UserEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    val id : UUID? = null,

    @field:Length(min = 2, max = 255)
    @Column(unique = true, nullable = false)
    val username : String = "",

    @field:Length(min = 2, max = 255)
    @Column(nullable = false)
    val password : String = "",

    @ManyToMany(
        fetch = FetchType.LAZY,
        cascade = [CascadeType.PERSIST, CascadeType.MERGE]
    )
    @JoinTable(
        name = "users_roles",
        joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")]
    )
    val roles : Collection<RoleEntity> = setOf(),

    @Column(nullable = false)
    val isEnabled : Boolean = true,

    @Column(nullable = false)
    val isAccountNonLocked : Boolean = true,

    @Column(nullable = false)
    val isAccountNonExpired : Boolean = true,

    @Column(nullable = false)
    val isCredentialsNonExpired : Boolean = true,

    @field:Length(min = 2, max = 255)
    @Column(nullable = false)
    val firstName : String = "",

    @field:Length(min = 2, max = 255)
    @Column(nullable = false)
    val familyName : String = "",

    @field:Lob
    @Column(nullable = true)
    val aboutSelf : String? = null,

    @Column(nullable = true)
    val gender : Boolean? = null,

    @Column(unique = true, nullable = true)
    val email : String? = null,

    @Column(unique = true, nullable = true)
    val phone : String? = null,

    @Column(unique = true, nullable = true)
    val skype : String? = null,

    @Column(unique = true, nullable = true)
    val git : String? = null

) : Serializable