package edcartel.user.controllers

import edcartel.user.DTOs.RoleViewWithUsersDTO
import edcartel.user.DTOs.converters.toViewWithUsersDTO
import edcartel.user.repositories.RoleRepository
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.transaction.Transactional

@Transactional
@RestController
@RequestMapping("role")
class RoleController(val roleRepo : RoleRepository) {

    @GetMapping("all")
    @PreAuthorize("hasRole('ADMIN')")
    fun findAll() : List<RoleViewWithUsersDTO> {
        return roleRepo.findAll()
            .map { it.toViewWithUsersDTO() }
    }
}