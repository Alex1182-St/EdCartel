package edcartel.course.controllers

import edcartel.course.DTOs.CourseViewDTO
import edcartel.course.DTOs.converters.toViewDTO
import edcartel.course.entities.CourseEntity
import edcartel.course.repositories.CourseRepository
import edcartel.course.requests.CourseUpdateInput
import edcartel.course.services.CourseService
import org.springframework.web.bind.annotation.*
import java.util.*
import kotlin.Exception


@RestController
@RequestMapping("course")
class CourseController(val courseRepo : CourseRepository, val courseServ : CourseService) {

    @PostMapping
    fun createCourse(@RequestBody newCourse : CourseEntity) : CourseViewDTO {
        return  courseRepo.save(newCourse).toViewDTO()
    }

    @GetMapping("byId/{id}")
    fun findCourseById(@PathVariable id : UUID) : CourseViewDTO {
            return courseRepo.findById(id)
                    .map { it.toViewDTO() }
                    .orElseThrow {
                Exception("Course not found with such id: $id") }
    }

    @GetMapping("byName/{courseName}")
    fun findCourseByName(@PathVariable courseName : String) : CourseViewDTO {
           return courseRepo.findByName(courseName)
                   .map {it.toViewDTO() }
                   .orElseThrow {
               Exception("Course not found with such name: $courseName") }
    }

    @PutMapping("updateById/{id}")
    fun updateCourse(@PathVariable id : UUID, @RequestBody updateInput : CourseUpdateInput) : CourseViewDTO {

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
             courseServ.updateCourse(id, updateInput).toViewDTO()
        } else {
            oldCourseToUpdate.toViewDTO()
        }
    }

    // в чем разница между return if и return в if ?????

    @DeleteMapping("deleteById{id}")
    fun deleteCourse(@PathVariable id : UUID ) {
        return courseRepo.deleteById(id)
    }
}



