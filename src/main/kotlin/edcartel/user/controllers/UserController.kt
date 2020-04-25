package edcartel.user.controllers

import edcartel.user.dtos.converters.toOutput
import edcartel.user.dtos.input.UserUpdateInput
import edcartel.user.dtos.output.UserOutput
import edcartel.user.repositories.UserRepository
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.transaction.Transactional
import javax.validation.Valid

@Transactional
@RestController
@RequestMapping("user")
class UserController(private val userRepo : UserRepository) {

    @GetMapping("all/{page}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    fun all() : Collection<UserOutput> =
        userRepo.findAll()
            .map { it.toOutput() }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('ADMIN') or authentication.principal.id == #id")
    fun findById(@PathVariable id : UUID) : UserOutput =
        userRepo.findById(id)
            .map { it.toOutput() }
            .orElseThrow { UsernameNotFoundException("User not found with id: $id") }

    @PutMapping("{id}")
    @PreAuthorize("hasAnyRole('ADMIN') or authentication.principal.id == #id")
    fun update(@PathVariable id : UUID, @Valid @RequestBody input : UserUpdateInput) : UserOutput {
        val user = userRepo.findById(id)
            .orElseThrow { UsernameNotFoundException("User not found with id: $id") }

        val updatedUser = user.copy(
            username    = input.username   ?: user.username,
            name        = input.name       ?: user.name,
            familyName  = input.familyName ?: user.familyName,
            aboutSelf   = input.aboutSelf  ?: user.aboutSelf,
            gender      = input.gender     ?: user.gender,
            email       = input.email      ?: user.email,
            phone       = input.phone      ?: user.phone
        )

        return if (user != updatedUser) {
            userRepo.save(updatedUser).toOutput()
        } else {
            user.toOutput()
        }
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyRole('ADMIN') or authentication.principal.id == #id")
    fun delete(@PathVariable id : UUID) : Unit = userRepo.deleteById(id)

}