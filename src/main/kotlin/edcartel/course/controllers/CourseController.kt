package edcartel.course.controllers

import edcartel.course.DTOs.output.CourseOutput
import edcartel.course.DTOs.converters.toCourseOutput
import edcartel.course.entities.CourseEntity
import edcartel.course.repositories.CourseRepository
import edcartel.course.DTOs.input.CourseUpdateInput
import edcartel.course.services.CourseService
import org.springframework.web.bind.annotation.*
import java.util.*
import kotlin.Exception


@RestController
@RequestMapping("course")
class CourseController(val courseRepo : CourseRepository, val courseServ : CourseService) {

    @PostMapping
    fun createCourse(@RequestBody newCourse : CourseEntity) : CourseOutput {
        return  courseRepo.save(newCourse).toCourseOutput()
    }

    @GetMapping("byId/{id}")
    fun findCourseById(@PathVariable id : UUID) : CourseOutput {
            return courseRepo.findById(id)
                    .map { it.toCourseOutput() }
                    .orElseThrow {
                Exception("Course not found with such id: $id") }
    }

    @GetMapping("byName/{courseName}")
    fun findCourseByName(@PathVariable courseName : String) : CourseOutput {
           return courseRepo.findByName(courseName)
                   .map {it.toCourseOutput() }
                   .orElseThrow {
               Exception("Course not found with such name: $courseName") }
    }

    @PutMapping("updateById/{id}")
    fun updateCourse(@PathVariable id : UUID, @RequestBody updateInput : CourseUpdateInput) : CourseOutput {

        val oldCourseToUpdate = courseRepo.findById(id)
                .orElseThrow { Exception("Course not found with such id: $id") }

        val newCourse = oldCourseToUpdate.copy(
                name = updateInput.name ?: oldCourseToUpdate.name,
                lessonsQuantity = updateInput.lessonsQuantity ?: oldCourseToUpdate.lessonsQuantity,
                hoursQuantity = updateInput.hoursQuantity ?: oldCourseToUpdate.hoursQuantity,
                levelOfCourse = updateInput.levelOfCourse ?: oldCourseToUpdate.levelOfCourse,
                shortDescription = updateInput.shortDescription ?: oldCourseToUpdate.shortDescription,
                longDescription = updateInput.longDescription ?: oldCourseToUpdate.longDescription,
                cost = updateInput.cost ?: oldCourseToUpdate.cost
        )

        return if (newCourse != oldCourseToUpdate) {
             courseServ.updateCourse(id, updateInput).toCourseOutput()
        } else {
            oldCourseToUpdate.toCourseOutput()
        }
    }

    // в чем разница между return if и return в if ?????

    @DeleteMapping("deleteById{id}")
    fun deleteCourse(@PathVariable id : UUID ) {
        return courseRepo.deleteById(id)
    }
}



