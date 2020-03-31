package edcartel.user.requests

data class UserCreateInput(

    val username : String,

    val password : String,

    val firstName : String,

    val familyName : String,

    val aboutSelf : String?,

    val gender : Boolean?,

    val email : String?,

    val phone : String?

)