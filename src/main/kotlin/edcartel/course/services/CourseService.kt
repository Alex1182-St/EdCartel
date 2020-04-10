package edcartel.course.services

import edcartel.course.entities.CourseEntity
import edcartel.course.repositories.CourseRepository
import edcartel.user.entities.UserEntity
import org.springframework.stereotype.Service
import java.util.*


@Service
class CourseService (val courseRepo : CourseRepository)
{
    fun updateCourse(courseId : UUID,  course : CourseEntity) {

        val oldCourse : Optional<CourseEntity> = courseRepo.findById(courseId)
        val authorId : Collection<UserEntity> = course.author
        val ourArrayFromCourse = arrayOf(oldCourse)

        for (element in ourArrayFromCourse) {
            if (element.equals(authorId)){
                if (oldCourse!=course) {
                    courseRepo.save(course)
                }
                else {
                    throw Exception("Such course with $courseId is absent or has nothing to update")}
            }
            else {
                throw Exception ("You do not have rights to update course")
            }
        }
}

    fun deleteCourse(body : Map<String, Any>) {
        val oldCourse : CourseEntity = courseRepo.findById(body.get("id") as UUID).get()
        val authorId : Set<UserEntity> = body.get("courseAuthor") as Set<UserEntity>
        val ourListFromCourse = listOf<Any>(oldCourse)

            if (ourListFromCourse.contains(authorId)) {
                        courseRepo.deleteById(body.get("id") as UUID)
        }
            else {
                throw Exception ("You do not have rights to delete course")
            }
    }

}