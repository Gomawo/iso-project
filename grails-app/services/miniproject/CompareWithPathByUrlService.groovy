package miniproject

import grails.gorm.transactions.Transactional
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.ss.usermodel.WorkbookFactory
import org.springframework.beans.factory.annotation.Autowired

@Transactional
class CompareWithPathByUrlService {

    String importStatus = ""
    Boolean isProcessing = false
    Integer totalRows
    Integer currentRow = 0
    String errorMessage = ""

    @Autowired
    private Md5ChecksumService md5ChecksumService

    boolean compareAndUpdateExcelFilePTU(String filePath) {
        try {
            File file = new File(filePath)
            Workbook wb = WorkbookFactory.create(new FileInputStream(file))
            Sheet sheet = wb.getSheetAt(0)

            isProcessing = true
            totalRows = sheet.getLastRowNum()

            for (int i = 1; i <= totalRows; i++) {
                currentRow = i
                Row row = sheet.getRow(i)

                if (row != null) {
                    Cell cell = row.getCell(3)

                    if (cell != null) {
                        String pathDocument = row.getCell(1).getStringCellValue()
                        String urlDocument = row.getCell(2).getStringCellValue()

                        def input1 = md5ChecksumService.calculateChecksum(pathDocument)
                        def input2 = md5ChecksumService.calculateChecksum(urlDocument)

                        def resultCompare = md5ChecksumService.compareChecksums(input1, input2)
                        def value = resultCompare ? 'Match' : 'Different'
                        cell.setCellValue(value)
                    }
                }
            }
            wb.write(new FileOutputStream(file))
            return true
        } catch (IOException e) {
            log.error("Error processing Excel file", e)
            return false
        }
        importStatus = "Success"
    }

    def getImportStatus() {
        def lastStatus = [isProcessing: isProcessing, currentRow: currentRow, totalRows: totalRows, message: importStatus, errorMessage: errorMessage]
        if (!isProcessing) {
            errorMessage = ""
            importStatus = ""
        }
        return lastStatus
    }
}
