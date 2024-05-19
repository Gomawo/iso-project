package miniproject

class UrlMappings {

    static mappings = {
       // "/$controller/$action?/$id?(.$format)?"{
       //     constraints {
       //         // apply constraints here
       //     }
       // }
        "/compareTextByText/$action?/$id?(.$format)?"(controller: "compareTextByText")
        "/compareWithPathByUrl/$action?/$id?(.$format)?"(controller: "compareWithPathByUrl")
        "/generatePdf/$action?/$id?(.$format)?"(controller: "generatePdf")

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
