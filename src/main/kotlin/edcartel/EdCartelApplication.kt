package edcartel

import edcartel.user.entities.User
import edcartel.user.repositories.UserRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EdCartelApplication(

    val userRepo: UserRepository

) : CommandLineRunner {

    override fun run(vararg args: String?) {
        val userList = listOf(
            User(
                username = "alex", password = "alex"
            ),
            User(
                username = "daniel", password = "daniel"
            )
        )

        userRepo.saveAll(userList)
    }

}

fun main(args: Array<String>) {
    runApplication<EdCartelApplication>(*args)
}
