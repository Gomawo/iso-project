package miniproject

import com.lowagie.text.pdf.PdfReader
import grails.gorm.transactions.Transactional
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.ss.usermodel.WorkbookFactory
import org.springframework.beans.factory.annotation.Autowired

@Transactional
class CompareTextByTextService {

    String importStatus = ""
    Boolean isProcessing = false
    Integer totalRows
    Integer currentRow = 0
    String errorMessage = ""

    @Autowired
    private Md5ChecksumService md5ChecksumService
    @Autowired
    private ConvertUrlToPdfService convertUrlToPdfService

    boolean compareAndUpdateExcelFileTBT(String filePath) {
        try {
            File file = new File(filePath)
            Workbook wb = WorkbookFactory.create(new FileInputStream(file))
            Sheet sheet = wb.getSheetAt(0)

            isProcessing = true
            totalRows = sheet.getLastRowNum()

            for (int i = 1; i <= sheet.size(); i++) {
                currentRow = i
                Row row = sheet.getRow(i)

                if (row != null) {
                    Cell cell = row.getCell(3)

                    if (cell != null) {
                        String pathDocument = row.getCell(1).getStringCellValue()
                        String url = row.getCell(2).getStringCellValue()

                        String urlDocument = convertUrlToPdfService.convertUrlToPdf(url)

                        def reader1 = new PdfReader(pathDocument)
                        def reader2 = new PdfReader(urlDocument)

                        def pages1 = reader1.getNumberOfPages()
                        def pages2 = reader2.getNumberOfPages()

                        /*if (pages1 != pages2) {
                            cell.setCellValue('Different')
                            wb.write(new FileOutputStream(file))
                        }

                        for (int j = 1; j <= pages1; j++) {
                            def text1 = reader1.getPageContent(j)
                            def text2 = reader2.getPageContent(j)

                            if (text1 != text2 ) {
                                cell.setCellValue('Different')
                                wb.write(new FileOutputStream(file))
                                continue
                            }
                        }
                        cell.setCellValue('Match')
                        wb.write(new FileOutputStream(file))*/

                        if (pages1 != pages2 || (1..pages1).any { reader1.getPageContent(it) != reader2.getPageContent(it) }) {
                            cell.setCellValue('Different')
                            wb.write(new FileOutputStream(file))
                        } else {
                            cell.setCellValue('Match')
                            wb.write(new FileOutputStream(file))
                        }
                    }
                }
            }
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
