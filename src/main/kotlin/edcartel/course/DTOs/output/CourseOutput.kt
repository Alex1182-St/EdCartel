package edcartel.course.DTOs.output

import java.util.*


data class CourseOutput(

        val id: UUID?,

        val name: String?,

        val lessonsQuantity : Int?,

        val hoursQuantity: Long?,

        val levelOfCourse: String?,

        val shortDescription: String?,

        val longDescription: String?,

        val cost: Long?,

        val author: Collection<UUID>
)