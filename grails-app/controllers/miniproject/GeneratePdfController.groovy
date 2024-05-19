package miniproject

class GeneratePdfController {

    ConvertUrlToPdfService convertUrlToPdfService
    def index() { }

    def generate() {
        def url = params.websiteUrl
        convertUrlToPdfService.convertUrlToPdf(url)
        render(view: "generateResult")
    }
}
