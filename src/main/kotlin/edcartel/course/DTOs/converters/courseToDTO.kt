package edcartel.course.DTOs.converters

import edcartel.course.DTOs.CourseViewDTO
import edcartel.course.entities.CourseEntity
import edcartel.user.dtos.converters.toViewDTO


fun CourseEntity.toViewDTO() = CourseViewDTO(
        id = id,
        name = name,
        hoursQuantity = hoursQuantity,
        levelOfCourse = levelOfCourse,
        shortDescription = shortDescription,
        longDescription = longDescription,
        cost = cost,
        author = author.map { it.toViewDTO() }

)

