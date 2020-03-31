package edcartel

import edcartel.user.entities.RoleEntity
import edcartel.user.entities.RoleEnum
import edcartel.user.entities.UserEntity
import edcartel.user.repositories.RoleRepository
import edcartel.user.repositories.UserRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.transaction.annotation.Transactional

@SpringBootApplication
class EdCartelApplication(

    private val passwordEncoder : PasswordEncoder,

    private val userRepo : UserRepository,

    private val roleRepo : RoleRepository

) : CommandLineRunner {

    @Transactional
    override fun run(vararg args : String) {
        roleRepo.saveAll(
            listOf(
                RoleEntity(name = RoleEnum.USER),
                RoleEntity(name = RoleEnum.STUDENT),
                RoleEntity(name = RoleEnum.TEACHER),
                RoleEntity(name = RoleEnum.ADMIN)
            )
        )

        userRepo.saveAll(
            listOf(
                UserEntity(
                    username = "alex", password = passwordEncoder.encode("alex"),
                    firstName = "alex", familyName = "alex",
                    roles = setOf(roleRepo.findByName(RoleEnum.ADMIN))
                ),
                UserEntity(
                    username = "daniel", password = passwordEncoder.encode("daniel"),
                    firstName = "daniel", familyName = "daniel",
                    roles = setOf(roleRepo.findByName(RoleEnum.USER))
                )
            )
        )
    }
}

fun main(args : Array<String>) {
    runApplication<EdCartelApplication>(*args)
}
