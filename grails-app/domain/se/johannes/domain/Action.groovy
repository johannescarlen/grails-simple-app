package se.johannes.domain

import se.johannes.security.User

class Action {

    Date timestamp
    String activity
    float length
    float time
    User user

    static constraints = {
    }
}
