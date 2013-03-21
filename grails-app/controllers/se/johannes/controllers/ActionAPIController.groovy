package se.johannes.controllers

import se.johannes.security.User
import grails.converters.JSON
import grails.plugins.springsecurity.Secured
import org.apache.commons.lang.StringUtils
import se.johannes.domain.Action

class ActionAPIController {

    @Secured(['ROLE_USER'])
    def index() {

        println "index"
        def paramid = params.actionid

        render Action.findById(paramid) as JSON
    }

    /*
    Call http service with this json:

    {
    "user" : "user",
        "action" : {
            "activity" : "Walking",
            "length" : "8,09",
            "time" : "45,04"
        }
    }

    Basic auth: user/user

     */
    @Secured(['ROLE_USER'])
    def indexPost() {
        println "indexPOST"

        def result = true
        def requestJson
        def username
        def user
        def actionJson
        def action

        requestJson = request.JSON
        username = requestJson.user

        if(StringUtils.isNotBlank(username)) {
            user = User.findByUsername(username)

            if (user != null) {
                actionJson = requestJson.action
                //println
                println actionJson

                action = new Action(actionJson)
                action.timestamp = new Date()

                action.user = user
                action.save(failOnError: true)

                render action as JSON

            } else
            {
                println "No user found with username: " + username
                result = false
            }

        } else {
            println "Missing param 'user'";
            result = false
        }

        render ([success: result]) as JSON

    }

}
