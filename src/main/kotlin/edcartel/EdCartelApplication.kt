package edcartel

import edcartel.user.entities.RoleEntity
import edcartel.user.entities.RoleEnum
import edcartel.user.entities.UserEntity
import edcartel.user.repositories.RoleRepository
import edcartel.user.repositories.UserRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import javax.transaction.Transactional

@SpringBootApplication
class EdCartelApplication(

    private val userRepo: UserRepository,

    private val roleRepo: RoleRepository

) : CommandLineRunner {

    @Transactional
    override fun run(vararg args: String?) {

        roleRepo.saveAll(
            listOf(
                RoleEntity(name = RoleEnum.USER),
                RoleEntity(name = RoleEnum.ADMIN)
            )
        )

        userRepo.saveAll(
            listOf(
                UserEntity(
                    userName = "alex", passWord = BCryptPasswordEncoder().encode("alex"),
                    firstName = "alex", familyName = "alex",
                    roles = mutableSetOf(roleRepo.findByName(RoleEnum.ADMIN))
                ),
                UserEntity(
                    userName = "daniel", passWord = BCryptPasswordEncoder().encode("daniel"),
                    firstName = "daniel", familyName = "daniel",
                    roles = mutableSetOf(roleRepo.findByName(RoleEnum.USER))
                )
            )
        )
    }
}

fun main(args: Array<String>) {
    runApplication<EdCartelApplication>(*args)
}
