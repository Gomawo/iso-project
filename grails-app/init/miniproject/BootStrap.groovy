package miniproject

import grails.core.GrailsApplication


class BootStrap {

    GrailsApplication grailsApplication

    def init = { servletContext ->

        Boolean initEnabled = Boolean.valueOf(grailsApplication.config.getProperty("application.dataInit.enabled"))

        if (!initEnabled) return

        Role adminRole = new Role(authority: "ROLE_ADMIN").save(flush: true)
        User admin = new User(username: "admin", password: "Pass1234").save(flush: true)
        new UserRole(user: admin, role: adminRole).save(flush: true)
    }
    def destroy = {
    }
}
