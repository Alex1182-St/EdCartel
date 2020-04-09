package edcartel.course.controllers

import edcartel.course.entities.CourseEntity
import edcartel.course.repositories.CourseRepository
import edcartel.course.services.CourseService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.bind.annotation.*
import java.util.*
import kotlin.Exception


@RestController
@RequestMapping("course")
class CourseController(val courseRepo : CourseRepository, val courseServ : CourseService) {

    @PostMapping
    fun createCourse(@RequestBody newCourse : CourseEntity) : CourseEntity {
        return  courseRepo.save(newCourse)
    }

    @GetMapping("byId/{id}")
    fun findCourseById(@PathVariable id : UUID) : CourseEntity {
            return courseRepo.findById(id).orElseThrow {
                Exception("Course not found with id: $id") }
    }


    @GetMapping("byName/{courseName}")
    fun findCourseByName(@PathVariable courseName : String) : CourseEntity {
           return courseRepo.findByCourseName(courseName).orElseThrow {
               Exception("User not found with name: $courseName") }

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



