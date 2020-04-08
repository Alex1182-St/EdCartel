package edcartel.course.controllers

import edcartel.course.entities.CourseEntity
import edcartel.course.repositories.CourseRepository
import edcartel.course.services.CourseService
import edcartel.user.entities.UserEntity
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("course")
class CourseController(val courseRepo : CourseRepository, val courseServ : CourseService) {

    @PostMapping
    fun createCourse(@RequestBody newCourse : CourseEntity) : CourseEntity {
        return  courseRepo.save(newCourse)
    }

    @GetMapping("byId/{id}")
    fun findCourseById(@PathVariable id : UUID) {
        if (courseRepo.findById(id) != null) {
            courseRepo.findById(id)
        }
        else
            throw Exception("Course with $id is not present")
    }

    @GetMapping("byName/{courseName}")
    fun findCourseByName(@PathVariable courseName : String) {
        if (courseRepo.findByCourseName(courseName) != null) {
            courseRepo.findByCourseName(courseName)
        } else
            throw Exception("Course with name $courseName is not present")
    }

    @PutMapping("byId/{id}")
    fun updateCourse(@PathVariable id : UUID, @RequestBody updatedCourse : CourseEntity) {
        val oldCourse : Optional<CourseEntity> = courseRepo.findById(id)
        val authorId : Set<UserEntity> = updatedCourse.courseAuthor
        if (oldCourse!=updatedCourse) {
            courseServ.updateCourse(id, authorId, updatedCourse)
        }
        else
            throw Exception("Such course with $id is absent or has nothing to update")
    }

    @DeleteMapping("byId/{id}")
    fun deleteCourse(@PathVariable id : UUID, authorId : UUID) {
        courseServ.deleteCourse(id, authorId)
    }
}



