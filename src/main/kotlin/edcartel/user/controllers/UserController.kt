package edcartel.user.controllers

import edcartel.user.entities.UserEntity
import edcartel.user.repositories.UserRepository
import edcartel.user.requests.UserCreateInput
import edcartel.user.requests.UserUpdateInput
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.ConstraintViolation
import javax.validation.Valid
import javax.validation.Validator

@RestController
@RequestMapping("user")
class UserController(val userRepo : UserRepository) {

    @GetMapping
    fun findAll() : List<UserEntity> = userRepo.findAll()

    @GetMapping("{id}")
    fun findById(@PathVariable id : UUID) : UserEntity =
        userRepo.getById(id)
            ?: throw UsernameNotFoundException("User not found with id: $id")

    @PostMapping
    fun create(@RequestBody newUser : UserEntity) : Any? {
        val user = UserEntity(
            username = newUser.username,
            password = newUser.password,
            firstName = newUser.firstName,
            familyName = newUser.familyName,
            aboutSelf = newUser.aboutSelf
        )
        return userRepo.save(user)
    }

    @PutMapping("{id}")
    fun update(@PathVariable id : UUID, @RequestBody input : UserUpdateInput) : UserEntity {
        val userOld =  userRepo.getById(id)
            ?: throw UsernameNotFoundException("User not found with id: $id")

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
            userRepo.save(userNew)
        } else {
            userOld
        }
    }

    @DeleteMapping("{id}")
    fun remove(@PathVariable id : UUID) = userRepo.removeById(id)
}