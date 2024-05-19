package miniproject

import grails.converters.JSON
import org.springframework.beans.factory.annotation.Value

class CompareTextByTextController {
    @Value('${environments.development.application.uploadPath}')
    private String uploadPath

    CompareTextByTextService compareTextByTextService

    def index(){
        render(view: 'index')
    }

    def inputFileExcel() {
        def file = request.getFile('fileExcel')
        String filePath = (uploadPath?.endsWith("/") ? uploadPath : uploadPath + "/") + file.originalFilename
        boolean compareSuccess = compareTextByTextService.compareAndUpdateExcelFileTBT(filePath)
        render(view: compareSuccess? "report" : "error")
    }

    def importStatus() {
        def importStatus = compareTextByTextService.getImportStatus()
        render importStatus as JSON
    }
}
