package edcartel.user.entities

import javax.persistence.Id
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType

@Entity
data class User(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,


    var username: String? = null,

    var password: String? = null,


    var firstName: String? = null,

    var familyName: String? = null,


    var email: String? = null,

    var phone: String? = null

)