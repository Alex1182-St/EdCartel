package edcartel.user.entities

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

        val cost : Long? = null,

        val discount : Int? = null,

        val courseAuthor : String? = null

        )
