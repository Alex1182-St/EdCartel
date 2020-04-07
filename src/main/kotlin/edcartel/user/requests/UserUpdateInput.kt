package edcartel.user.requests

import org.hibernate.validator.constraints.Length

data class UserUpdateInput(

    @field:Length(min = 2, max = 255)
    val username : String?,

    @field:Length(min = 2, max = 255)
    val firstName : String?,

    @field:Length(min = 2, max = 255)
    val familyName : String?,

    val aboutSelf : String?,

    val gender : Boolean?,

    val email : String?,

    val phone : String?

)