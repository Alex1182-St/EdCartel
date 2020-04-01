package edcartel.user.repositories

import edcartel.user.entities.CourseEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*


@Repository
interface CourseRepository : JpaRepository<CourseEntity, UUID> {

    fun findByCourseName(courseName : String) : CourseEntity


}