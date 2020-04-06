package edcartel.course.services

import edcartel.course.entities.CourseEntity
import edcartel.course.repositories.CourseRepository
import org.springframework.stereotype.Service
import java.util.*


@Service
class CourseService (val courseRepo : CourseRepository)
{
    fun updateCourse(courseId : UUID, authorId : UUID, course : CourseEntity) {
        val ourArrayFromCourse = arrayOf(course)
        for (element in ourArrayFromCourse) {
            if (element.equals(authorId)){
                courseRepo.save(course)
            }
            else {
                throw Exception ("You do not have rights to update course")
            }
        }
}

    fun deleteCourse(courseId : UUID, authorId : UUID) {
        val ourListFromCourse = listOf<Any>(courseRepo.findById(courseId))
            if (ourListFromCourse.contains(authorId)) {
                        courseRepo.deleteById(courseId)
        }
            else {
                throw Exception ("You do not have rights to delete course")
            }
    }

}