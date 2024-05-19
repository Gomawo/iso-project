package miniproject

import grails.converters.JSON
import org.springframework.beans.factory.annotation.Value

class CompareWithPathByUrlController {
    @Value('${environments.development.application.uploadPath}')
    private String uploadPath

    CompareWithPathByUrlService compareWithPathByUrlService

    def index() {
        render(view: 'index')
    }

    def inputFileExcel() {
        def file = request.getFile('fileExcel')
        String filePath = (uploadPath?.endsWith("/") ? uploadPath : uploadPath + "/") + file.originalFilename
        boolean compareSuccess = compareWithPathByUrlService.compareAndUpdateExcelFilePTU(filePath)
        render(view: compareSuccess? "report" : "error")
    }

    def importStatus() {
        def importStatus = compareWithPathByUrlService.getImportStatus()
        render importStatus as JSON
    }
}