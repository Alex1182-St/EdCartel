package edcartel.user.entities

import org.hibernate.validator.constraints.Length
import java.io.Serializable
import edcartel.course.entities.CourseEntity
import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "users")
data class UserEntity(

    @field:Id
    @field:GeneratedValue(strategy = GenerationType.AUTO)
    @field:Column(updatable = false, nullable = false, unique = true)
    val id : UUID = UUID.randomUUID(),

    @field:Length(min = 2, max = 255)
    @field:Column(nullable = false, unique = true)
    val username : String,

    @field:Length(min = 2, max = 255)
    @field:Column(nullable = false)
    val password : String,

    @field:ManyToMany(
        fetch = FetchType.LAZY,
        cascade = [CascadeType.PERSIST, CascadeType.MERGE]
    )
    @field:JoinTable(
        name = "users_roles",
        joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")]
    )
    val roles : Collection<RoleEntity> = setOf(),

    @field:Column(nullable = false)
    val isEnabled : Boolean = true,

    @field:Column(nullable = false)
    val isAccountNonLocked : Boolean = true,

    @field:Column(nullable = false)
    val isAccountNonExpired : Boolean = true,

    @field:Column(nullable = false)
    val isCredentialsNonExpired : Boolean = true,


    @field:Length(min = 2, max = 255)
    @field:Column(nullable = false)
    val name : String,

    @field:Length(min = 2, max = 255)
    @field:Column(nullable = false)
    val familyName : String,

    @field:Lob
    @field:Column(nullable = true)
    val aboutSelf : String? = null,

    @field:Column(nullable = true)
    val gender : Boolean? = null,

    @field:Column(nullable = true, unique = true)
    val email : String? = null,

    @field:Column(nullable = true, unique = true)
    val phone : String? = null,

    @field:Column(nullable = true, unique = true)
    val skype : String? = null,

    @field:Column(nullable = true, unique = true)
    val git : String? = null,

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = [CascadeType.PERSIST, CascadeType.MERGE]
    )
    @JoinTable(
            name = "users_courses",
            joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
            inverseJoinColumns = [JoinColumn(name = "course_id", referencedColumnName = "id")]
    )
    val courses : Collection<CourseEntity> = setOf()

) : Serializable