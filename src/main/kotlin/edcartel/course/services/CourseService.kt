package edcartel.course.services

import edcartel.course.entities.CourseEntity
import edcartel.course.repositories.CourseRepository
import edcartel.course.DTOs.input.CourseUpdateInput
import edcartel.user.repositories.UserRepository
import org.springframework.stereotype.Service
import java.util.*


@Service
class CourseService(val courseRepo : CourseRepository, val userRepo : UserRepository) {

  fun updateCourse(courseId : UUID, courseUpdateInput : CourseUpdateInput) : CourseEntity {

      val authorIdOfUpdateCourse : UUID = courseUpdateInput.author
              ?: throw Exception ("You have not entered id of author of course, that you want to update")

      val oldCourse = courseRepo.findById(courseId)
              .orElseThrow { Exception("Course with id: $courseId, that you want to update, is not found.") }

      val authorEntitiesOfOldCourse = oldCourse?.author
              ?: throw Exception ("Course, that you want to update, does not have authors." +
                      "Checking for author rights is impossible.")

      val authorEntityOfUpdateCourse = userRepo.findById(authorIdOfUpdateCourse)
              .orElseThrow { Exception("Author not found with such id: $authorIdOfUpdateCourse") }

      val isPresent = authorEntitiesOfOldCourse.contains(authorEntityOfUpdateCourse)

      if (isPresent == true) {

      val updatedCourse = CourseEntity (
              name = courseUpdateInput.name,
              lessonsQuantity = courseUpdateInput.lessonsQuantity,
              hoursQuantity = courseUpdateInput.hoursQuantity,
              levelOfCourse = courseUpdateInput.levelOfCourse,
              shortDescription = courseUpdateInput.shortDescription,
              longDescription = courseUpdateInput.longDescription,
              cost = courseUpdateInput.cost
           )
          return updatedCourse
      }
      else
      {
          throw Exception ("You do not have rights to update course with id: $courseId. You are not author")
      }

  }

    }
