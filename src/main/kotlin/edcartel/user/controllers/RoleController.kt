package edcartel.user.controllers

import edcartel.user.dtos.converters.toOutput
import edcartel.user.dtos.output.RoleOutput
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
    fun findAll() : Collection<RoleOutput> =
        roleRepo.findAll()
            .map { it.toOutput() }
}