package edcartel.configurations

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.provider.token.DefaultTokenServices
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore

@Configuration
class BeanConfig {

//    @Bean
//    fun openFilter() =
//        OpenEntityManagerInViewFilter() as Filter

    @Bean
    fun passwordEncoder() : PasswordEncoder =
        BCryptPasswordEncoder()

    @Bean
    fun tokenStore() : TokenStore =
        JwtTokenStore(accessTokenConverter())

    @Bean
    fun accessTokenConverter(): JwtAccessTokenConverter {
        val converter = JwtAccessTokenConverter()
        converter.setSigningKey("12345")
        return converter
    }

    @Bean
    fun tokenServices(tokenStore: TokenStore): DefaultTokenServices {
        val defaultTokenServices = DefaultTokenServices()
        defaultTokenServices.setTokenStore(tokenStore)
        defaultTokenServices.setSupportRefreshToken(true)
        return defaultTokenServices
    }
}
