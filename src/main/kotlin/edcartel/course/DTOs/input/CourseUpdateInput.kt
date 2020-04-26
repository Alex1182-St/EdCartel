package edcartel.course.DTOs.input

import java.util.*

data class CourseUpdateInput(

        val name : String?,

        val lessonsQuantity : Int?,

        val hoursQuantity : Long?,

        val levelOfCourse : String?,

        val shortDescription: String?,

        val longDescription: String?,

        val cost: Long?,

        val author: UUID?


)