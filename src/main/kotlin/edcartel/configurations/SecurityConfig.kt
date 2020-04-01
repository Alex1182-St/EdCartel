package edcartel.configurations

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig(

    private val passwordEncoder : PasswordEncoder,

    private val userDetailsService : UserDetailsService

) : WebSecurityConfigurerAdapter() {

    @Bean
    override fun authenticationManagerBean() : AuthenticationManager =
        super.authenticationManagerBean()

    override fun configure(http : HttpSecurity) {
        http.csrf().disable()

        http.sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        http.authorizeRequests()
            .anyRequest().permitAll()

        http.formLogin().and().logout().disable()
    }

    override fun configure(auth : AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder)
    }
}