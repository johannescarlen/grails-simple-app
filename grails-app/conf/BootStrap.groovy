import se.johannes.security.Role
import se.johannes.security.User
import se.johannes.security.UserRole
import grails.converters.JSON
import se.johannes.domain.Action

class BootStrap {

    def init = { servletContext ->

        JSON.registerObjectMarshaller(Date) {
            it?.getTime()
        }

        def userRole = Role.findByAuthority('ROLE_USER') ?: new Role(authority: 'ROLE_USER').save(failOnError: true)
        def adminRole = Role.findByAuthority('ROLE_ADMIN') ?: new Role(authority: 'ROLE_ADMIN').save(failOnError: true)

        def plainUser = User.findByUsername('user') ?: new User(
                username: 'user',
                password: 'user',
                enabled: true).save(failOnError: true)

        if (!plainUser.authorities.contains(adminRole)) {
            UserRole.create plainUser, userRole
        }

        def adminUser = User.findByUsername('admin') ?: new User(
                username: 'admin',
                password: 'admin',
                enabled: true).save(failOnError: true)

        if (!adminUser.authorities.contains(adminRole)) {
            UserRole.create adminUser, adminRole
        }

        def action = new Action(activity: "Running", timestamp: new Date(), length: 4.05, time: 18.25, user:plainUser)
        action.save(failOnError: true)


    }
    def destroy = {
    }
}
