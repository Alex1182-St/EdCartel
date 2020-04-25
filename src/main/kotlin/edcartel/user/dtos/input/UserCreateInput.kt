package edcartel.user.dtos.input

import org.hibernate.validator.constraints.Length

data class UserCreateInput(

    @field:Length(min = 2, max = 255)
    val username : String,

    @field:Length(min = 2, max = 255)
    val password : String,

    @field:Length(min = 2, max = 255)
    val name : String,

    @field:Length(min = 2, max = 255)
    val familyName : String,

    val aboutSelf : String?,

    val gender : Boolean?,

    val email : String?,

    val phone : String?

)