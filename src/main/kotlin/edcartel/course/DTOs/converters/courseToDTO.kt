package edcartel.course.DTOs.converters

import edcartel.course.DTOs.CourseViewDTO
import edcartel.course.entities.CourseEntity



fun CourseEntity.toViewDTO() = CourseViewDTO(
        id = id,
        name = name,
        lessonsQuantity = lessonsQuantity,
        hoursQuantity = hoursQuantity,
        levelOfCourse = levelOfCourse,
        shortDescription = shortDescription,
        longDescription = longDescription,
        cost = cost,
        author = author.map { it.id }

)

