package edcartel.user.controllers

import edcartel.user.dtos.converters.toDetailsDTO
import edcartel.user.dtos.converters.toOutput
import edcartel.user.dtos.input.UserCreateInput
import edcartel.user.dtos.input.UserUpdateInput
import edcartel.user.dtos.output.UserOutput
import edcartel.user.entities.RoleEnum
import edcartel.user.entities.UserEntity
import edcartel.user.repositories.RoleRepository
import edcartel.user.repositories.UserRepository
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.oauth2.common.OAuth2AccessToken
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.security.oauth2.provider.token.DefaultTokenServices
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.transaction.Transactional
import javax.validation.Valid


@Transactional
@RestController
@RequestMapping("oauth")
class OAuthController(

    @Qualifier("accessTokenConverter")
    private val accessTokenConverter : JwtAccessTokenConverter,

    private val tokenServices : DefaultTokenServices,

    private val userRepo : UserRepository,

    private val roleRepo : RoleRepository

) {

    @PostMapping("sign_up")
    fun signUp(auth : OAuth2Authentication, @Valid @RequestBody newUser : UserCreateInput) : OAuth2AccessToken {
        val role = roleRepo.findByName(RoleEnum.USER)

        val user = UserEntity(
            username   = newUser.username,
            password   = newUser.password,
            name       = newUser.name,
            familyName = newUser.familyName,
            roles      = setOf(role)
        )

        val userDetails = userRepo.save(user).toDetailsDTO()

        val authentication = OAuth2Authentication(
            auth.oAuth2Request,
            UsernamePasswordAuthenticationToken(
                userDetails,
                userDetails.password,
                userDetails.authorities
            )
        )

        val accessToken = accessTokenConverter.enhance(
            tokenServices.createAccessToken(authentication),
            authentication
        )

        return accessToken
    }
}