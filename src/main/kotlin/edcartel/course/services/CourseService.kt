package edcartel.course.services

import edcartel.course.entities.CourseEntity
import edcartel.course.repositories.CourseRepository
import org.springframework.stereotype.Service
import java.util.*


@Service
class CourseService (val courseRepo : CourseRepository)
{
    fun updateCourse(courseId : UUID,  course : CourseEntity) : CourseEntity {

        val oldCourse = courseRepo.findById(courseId)
                .orElseThrow{ Exception("Course not found with such id: $courseId") }
        val authorsFromOldCourse = oldCourse.author
        val authorsFromNewCourse = course.author
/*
        for (n in authorsFromNewCourse)
        {
            for (o in authorsFromOldCourse) {
                authorsFromNewCourse.
            }
        }
*/
        return if (oldCourse != course && authorsFromOldCourse == authorsFromNewCourse) {
                  courseRepo.save(course)
        }
        else {
            throw Exception("There are nothing to update with course - courseId: $courseId")}
    }}

