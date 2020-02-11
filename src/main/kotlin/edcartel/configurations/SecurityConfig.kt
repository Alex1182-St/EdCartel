package edcartel.configurations

import edcartel.user.services.UserDetailsServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Configuration
class SecurityConfig(

    val userDetailsServiceImpl: UserDetailsServiceImpl

) : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {

        http.csrf().disable()

        http.headers().frameOptions().disable()

        http.authorizeRequests()
            .anyRequest().permitAll().and()
            .httpBasic()

        http.logout()
            .logoutUrl("/sign-out")
            .deleteCookies("JSESSIONID")
            .clearAuthentication(true)
            .invalidateHttpSession(true)

    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsServiceImpl)
            .passwordEncoder(passwordEncoder())
    }

    @Bean()
    fun passwordEncoder() = BCryptPasswordEncoder()

}