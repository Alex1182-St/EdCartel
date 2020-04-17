package edcartel.course.services

import edcartel.course.entities.CourseEntity
import edcartel.course.repositories.CourseRepository
import edcartel.course.requests.CourseUpdateInput
import edcartel.user.repositories.UserRepository
import org.springframework.stereotype.Service
import java.util.*


@Service
class CourseService(val courseRepo : CourseRepository, val userRepo : UserRepository) {

  fun updateCourse(courseId : UUID, courseUpdateInput : CourseUpdateInput) : CourseEntity {

      val oldCourse = courseRepo.findById(courseId)
              .orElseThrow { Exception("Course not found with such id: $courseId") }

      val authorEntitiesOfOldCourse = oldCourse.author
              // ЕСЛИ АВТОРА НЕТ, NULL ?????

      val authorIdOfUpdateCourse : UUID = courseUpdateInput.author


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
