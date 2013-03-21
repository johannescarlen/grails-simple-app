package se.johannes.controllers

import grails.converters.JSON

class ErrorJSONController {

    def index() {
        println "In ErrorJSONController.index"
        render ([success: false]) as JSON
    }
}
