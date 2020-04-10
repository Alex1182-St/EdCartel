package edcartel.course.entities

import edcartel.user.entities.UserEntity
import org.hibernate.validator.constraints.Length
import java.util.*
import javax.persistence.*


@Entity
@Table(name = "courses")
data class CourseEntity(

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(updatable = false, nullable = false)
        val id : UUID? = null,

        @field:Length(min = 2, max = 255)
        @Column(nullable = false)
        val name : String? = null,

        @Column(nullable = false)
        val lessonsQuantity : Int? = null,

        @Column(nullable = false)
        val hoursQuantity : Long? = null,

        @Column(nullable = true)
        val levelOfCourse : String? = null,

        @field:Length(min = 2, max = 1024)
        @Column(nullable = false)
        val shortDescription : String? = null,

        @field:Length(min = 2, max = 4096)
        @Column(nullable = false)
        val longDescription : String? = null,

        @Column(nullable = true)
        val cost : Long? = null,

        @Column(nullable = true)
        val discount : Int? = null,

        @ManyToMany(
                mappedBy = "courses",
                fetch = FetchType.LAZY,
                cascade = [CascadeType.PERSIST, CascadeType.MERGE]
        )
        val author : Collection<UserEntity> = setOf()

        )