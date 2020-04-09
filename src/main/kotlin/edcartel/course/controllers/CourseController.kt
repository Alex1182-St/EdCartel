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
        courseRepo.findById(id)
    }

    @GetMapping("byName/{courseName}")
    fun findCourseByName(@PathVariable courseName : String) {
        courseRepo.findByCourseName(courseName)
    }

    @PutMapping("updateById/{id}")
    fun updateCourse(@PathVariable id : UUID, @RequestBody updatedCourse : CourseEntity) {
        return courseServ.updateCourse(id, updatedCourse)
    }

    @PostMapping("deleteById")
    fun deleteCourse(@RequestBody credentialsOfCourseToBeDeleted : Map<String, Any>) {
        return courseServ.deleteCourse(credentialsOfCourseToBeDeleted)
    }
}



