package edcartel.user.repositories

import edcartel.user.entities.СourseEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*


@Repository
interface CourseRepository : JpaRepository<СourseEntity, UUID> {

    fun findByCourseName(courseName : String) : СourseEntity


}