package edcartel.user.services

import edcartel.user.entities.CourseEntity
import edcartel.user.repositories.CourseRepository
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
        val ourArrayFromCourse = arrayOf(courseRepo.findById(courseId))
        for (element in ourArrayFromCourse) {
            if (element.equals(authorId)){
                courseRepo.deleteById(courseId)
            }
            else {
                throw Exception ("You do not have rights to delete course")
            }
        }
    }

}