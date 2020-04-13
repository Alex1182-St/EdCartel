package edcartel.configurations

import edcartel.user.services.UserDetailsService
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter
import org.springframework.security.oauth2.provider.token.DefaultTokenServices
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter
import javax.servlet.Filter


@Configuration
class BeanConfig(private val userDetailsService : UserDetailsService) {

    val signingKey : String = "12345"

    @Bean
    fun passwordEncoder() : PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun tokenStore() : TokenStore = JwtTokenStore(accessTokenConverter())

    @Bean
    fun accessTokenConverter() : JwtAccessTokenConverter {
        val userTokenConverter = DefaultUserAuthenticationConverter().apply {
            setUserDetailsService(userDetailsService)
        }

        val accessTokenConverter = DefaultAccessTokenConverter().apply {
            setUserTokenConverter(userTokenConverter)
        }

        return JwtAccessTokenConverter().apply {
            setSigningKey(signingKey)
            setAccessTokenConverter(accessTokenConverter)
        }
    }

    @Bean
    fun tokenServices(tokenStore : TokenStore) : DefaultTokenServices {
        return DefaultTokenServices().apply {
            setTokenStore(tokenStore)
            setSupportRefreshToken(true)
        }
    }

    @Bean
    fun corsFilter() : FilterRegistrationBean<Filter> {
        val config = CorsConfiguration().apply {
            allowedOrigins = listOf("*")
            allowedHeaders = listOf("*")
            allowedMethods = listOf("*")
            allowCredentials = true
        }

        val source = UrlBasedCorsConfigurationSource().apply {
            registerCorsConfiguration("/**", config)
        }

        return FilterRegistrationBean<Filter>(CorsFilter(source)).apply {
            order = Ordered.HIGHEST_PRECEDENCE
        }
    }
}
