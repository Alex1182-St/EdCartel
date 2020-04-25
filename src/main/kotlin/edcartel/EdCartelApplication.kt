package edcartel

import edcartel.user.entities.RoleEntity
import edcartel.user.entities.RoleEnum
import edcartel.user.entities.UserEntity
import edcartel.user.repositories.RoleRepository
import edcartel.user.repositories.UserRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.actuate.endpoint.annotation.Endpoint
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.servlet.config.annotation.EnableWebMvc

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
                    name = "alex", familyName = "alex",
                    roles = setOf(roleRepo.findByName(RoleEnum.ADMIN), roleRepo.findByName(RoleEnum.ADMIN))
                ),
                UserEntity(
                    username = "daniel", password = passwordEncoder.encode("daniel"),
                    name = "daniel", familyName = "daniel",
                    roles = setOf(roleRepo.findByName(RoleEnum.TEACHER))
                ),
                UserEntity(
                    username = "max", password = passwordEncoder.encode("max"),
                    name = "max", familyName = "max",
                    roles = setOf(roleRepo.findByName(RoleEnum.USER))
                )
            )
        )
    }
}

fun main(args : Array<String>) {
    runApplication<EdCartelApplication>(*args)
}
