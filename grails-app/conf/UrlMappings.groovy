class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}
        "/actions" {
            controller = "ActionAPI"
            action = [GET: "index", POST: "indexPost"]
        }
		"/"(view:"/index")
		"500"(view:'/error')
        "401"(controller:"ErrorJSON", action:"index")
        "403"(controller:"ErrorJSON", action:"index")

    }
}
