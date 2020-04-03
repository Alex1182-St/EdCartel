package edcartel.user.controllers

import edcartel.user.entities.CourseEntity
import edcartel.user.repositories.CourseRepository
import edcartel.user.services.CourseService
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("/course")
class CourseController(val courseRepo : CourseRepository, val courseServ : CourseService) {

    @PostMapping
    fun createCourse(@RequestBody newCourse : CourseEntity) : CourseEntity {
        return  courseRepo.save(newCourse)
    }

    @GetMapping("/byId/{id}")
    fun findCourseById(@PathVariable id : UUID) {
        if (courseRepo.findById(id) != null) {
            courseRepo.findById(id)
        }
        else
            throw Exception("Course with $id is not present")
    }

    @GetMapping("/byName/{courseName}")
    fun findCourseByName(@PathVariable courseName : String) {
        if (courseRepo.findByCourseName(courseName) != null) {
            courseRepo.findByCourseName(courseName)
        } else
            throw Exception("Course with name $courseName is not present")
    }

    @PutMapping("/byId/{id}")
    fun updateCourse(@PathVariable id : UUID, @RequestBody updatedCourse : CourseEntity, authorId : UUID) {
        val oldCourse : Optional<CourseEntity> = courseRepo.findById(id)
        if (oldCourse!=updatedCourse) {
            courseServ.updateCourse(id, authorId, updatedCourse)
        }
        else
            throw Exception("Such course with $id is absent or has nothing to update")
    }

    @DeleteMapping("/byId/{id}")
    fun deleteCourse(@PathVariable id : UUID, authorId : UUID) {
        courseServ.deleteCourse(id, authorId)
    }
}



