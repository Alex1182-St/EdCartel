package edcartel.user.controllers

import edcartel.user.DTOs.UserViewTDO
import edcartel.user.DTOs.converters.toViewTDO
import edcartel.user.entities.RoleEnum
import edcartel.user.entities.UserEntity
import edcartel.user.repositories.RoleRepository
import edcartel.user.repositories.UserRepository
import edcartel.user.requests.UserCreateInput
import edcartel.user.requests.UserUpdateInput
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.transaction.Transactional
import javax.validation.Valid

@Transactional
@RestController
@RequestMapping("user")
class UserController(val userRepo : UserRepository, val roleRepo : RoleRepository) {

    @GetMapping("all")
    @PreAuthorize("hasRole('ADMIN')")
    fun findAll() : List<UserViewTDO> =
        userRepo.findAll()
            .map { it.toViewTDO() }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    fun findById(@PathVariable id : UUID) : UserViewTDO =
        userRepo.findById(id)
            .map { it.toViewTDO() }
            .orElseThrow { UsernameNotFoundException("User not found with id: $id") }

    @PostMapping
    fun create(@Valid @RequestBody newUser : UserCreateInput) : UserViewTDO {
        val user = UserEntity(
            username = newUser.username,
            password = newUser.password,
            firstName = newUser.firstName,
            familyName = newUser.familyName,
            roles = setOf(roleRepo.findByName(RoleEnum.USER))
        )

        return userRepo.save(user).toViewTDO()
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id : UUID, @Valid @RequestBody input : UserUpdateInput) : UserViewTDO {
        val userOld = userRepo.findById(id)
            .orElseThrow { UsernameNotFoundException("User not found with id: $id") }

        val userNew = userOld.copy(
            username = input.username ?: userOld.username,
            firstName = input.firstName ?: userOld.firstName,
            familyName = input.familyName ?: userOld.familyName,
            aboutSelf = input.aboutSelf ?: userOld.aboutSelf,
            gender = input.gender ?: userOld.gender,
            email = input.email ?: userOld.email,
            phone = input.phone ?: userOld.phone
        )

        return if (userOld != userNew) {
            userRepo.save(userNew).toViewTDO()
        } else {
            userOld.toViewTDO()
        }
    }

    @DeleteMapping("/{id}")
    fun remove(@PathVariable id : UUID) = userRepo.deleteById(id)

}