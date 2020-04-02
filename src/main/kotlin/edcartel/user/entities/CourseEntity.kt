package edcartel.user.entities

import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode
import java.util.*
import javax.persistence.*


@Entity
@Table(name = "courses")
data class CourseEntity(

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id : UUID? = null,

        val courseName : String? = null,

        val lessonsQuantity : Int? = null,

        val hoursQuantity : Long? = null,

        val levelOfCourse : String? = null,

        val shortDescription : String? = null,

        val longDescription : String? = null,

        val courseCost : Long? = null,

        val courseDiscount : Int? = null,

        @ManyToMany(
                fetch = FetchType.LAZY,
                cascade = [CascadeType.PERSIST, CascadeType.MERGE]
        )
        @JoinTable(
                name = "courses_users",
                joinColumns = [JoinColumn(name = "courses_id", referencedColumnName = "id")],
                inverseJoinColumns = [JoinColumn(name = "users_id", referencedColumnName = "id")]
        )
        @Fetch(FetchMode.SUBSELECT)
        val courseAuthor : Set<UserEntity> = setOf()

)