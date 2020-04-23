package edcartel.course.DTOs

import edcartel.user.dtos.UserViewDTO
import java.util.*


data class CourseViewDTO(

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