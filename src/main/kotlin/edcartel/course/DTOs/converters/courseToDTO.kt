package edcartel.course.DTOs.converters

import edcartel.course.DTOs.output.CourseOutput
import edcartel.course.entities.CourseEntity



fun CourseEntity.toCourseOutput() = CourseOutput(
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

