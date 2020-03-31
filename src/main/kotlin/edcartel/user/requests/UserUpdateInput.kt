package edcartel.user.requests

data class UserUpdateInput(

    val username : String?,

    val firstName : String?,

    val familyName : String?,

    val aboutSelf : String?,

    val gender : Boolean?,

    val email : String?,

    val phone : String?

)