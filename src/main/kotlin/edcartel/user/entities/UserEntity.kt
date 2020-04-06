package edcartel.user.entities

import edcartel.course.entities.CourseEntity
import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode
import java.util.*
import javax.persistence.*


@Entity
@Table(name = "users")
data class UserEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    val id : UUID? = null,

    @Column(unique = true, nullable = false)
    val username : String? = null,

    @Column(nullable = false)
    val password : String? = null,

    @ManyToMany(
        fetch = FetchType.EAGER,
        cascade = [CascadeType.PERSIST, CascadeType.MERGE]
    )
    @JoinTable(
        name = "users_roles",
        joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")]
    )
    @Fetch(FetchMode.SUBSELECT)
    val roles : Set<RoleEntity> = setOf(),

    @Column(nullable = false)
    val isEnabled : Boolean = true,

    @Column(nullable = false)
    val isAccountNonLocked : Boolean = true,

    @Column(nullable = false)
    val isAccountNonExpired : Boolean = true,

    @Column(nullable = false)
    val isCredentialsNonExpired : Boolean = true,

    @Column(nullable = false)
    val firstName : String? = null,

    @Column(nullable = false)
    val familyName : String? = null,

    @Lob
    val aboutSelf : String? = null,

    val gender : Boolean? = null,

    @Column(unique = true)
    val email : String? = null,

    @Column(unique = true)
    val phone : String? = null,

    @Column(unique = true)
    val skype : String? = null,

    @Column(unique = true)
    val git : String? = null,

    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = [CascadeType.PERSIST, CascadeType.MERGE]
    )
    val courses : Set<CourseEntity> = setOf()


)