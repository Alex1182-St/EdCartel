package edcartel.user.controllers

import edcartel.user.entities.CourseEntity
import edcartel.user.repositories.CourseRepository
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("/course")
class CourseController(val courseRepo : CourseRepository) {

    @PostMapping
    fun createCourse(@RequestBody newCourse : CourseEntity) : CourseEntity {
        return  courseRepo.save(newCourse)
    }

    @GetMapping("/byId/{id}")
    fun findCourseById(@PathVariable id : UUID) : Optional<CourseEntity> {
        if (courseRepo.findById(id) != null) {
            return courseRepo.findById(id)
        }
        else
            throw Exception("Course by Id is not found")
    }

    @GetMapping("/byName/{courseName}")
    fun findCourseByName(@PathVariable courseName : String) : CourseEntity {
        if (courseRepo.findByCourseName(courseName) != null) {
            return courseRepo.findByCourseName(courseName)
        } else
            throw Exception("Course by Name is not found")
    }

    @PutMapping("/byId/{id}")
    fun updateCourse(@PathVariable id : UUID, @RequestBody updatedCourse : CourseEntity) : CourseEntity {
        val oldCourse : CourseEntity
        if (courseRepo.findById(id) != null) {
            return courseRepo.save(updatedCourse)
        }
        else
            throw Exception("Such course is absent")
    }

    @DeleteMapping("/byId/{id}")
    fun deleteCourse(@PathVariable id : UUID) {
        courseRepo.deleteById(id)
    }
}



