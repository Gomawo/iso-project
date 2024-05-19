package miniproject

import grails.gorm.transactions.Transactional
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.openqa.selenium.*
import org.openqa.selenium.chrome.*
import org.springframework.beans.factory.annotation.Value
import org.xhtmlrenderer.layout.SharedContext
import org.xhtmlrenderer.pdf.ITextRenderer

@Transactional
class ConvertUrlToPdfService {
    @Value('${environments.development.application.pdfDownloadPath}')
    private String pdfDownloadPath
    @Value('${environments.development.application.chromeDriver}')
    private String chromeDriver

    String convertUrlToPdf(String url) {
        System.setProperty("webdriver.chrome.driver", chromeDriver)
        ChromeOptions chromeOptions = new ChromeOptions()
        chromeOptions.addArguments("--headless")
        WebDriver driver = new ChromeDriver(chromeOptions)
        String filePath = ""
        try {
            driver.get(url)
            Thread.sleep(3000)
            List<WebElement> uniqueElements
            def selectors = [
                    By.id("legisContent"),
                    By.className("col-sm-9"),
                    By.id("content"),
                    By.className("bJMeLZ"),
                    By.id("aws-page-content-main"),
                    By.className("lb-col"),
                    By.className("corner-all"),
                    By.className("article"),
                    By.className("row"),
                    By.className("devsite-article"),
                    By.id("wiki-body"),
                    By.id("main-content"),
                    By.className("css-1bingsm"),
                    By.className("_noListItem_1s3in_137"),
                    By.className("topic__inner-container")
            ]

            /*def selectors = [
                    By.className("bJMeLZ"),
                    By.tagName("main"),
                    By.className("lb-col"),
                    By.id("legisContent"),
                    By.id("content"),
                    By.id("main"),
                    By.className("legal-template"),
                    By.className("col-sm-9"),
                    By.className("main-col")
            ]*/

            for (selector in selectors) {
                uniqueElements = driver.findElements(selector)
                if (!uniqueElements.isEmpty()) {
                    break
                }
            }

            def htmlContent = "<html><body>"
            uniqueElements.each { element ->
                htmlContent += element.getAttribute("outerHTML")
            }
            htmlContent += "</body></html>"
            Document doc = Jsoup.parse(htmlContent, "UTF-8")
            doc.select("style").remove()
            doc.select(".devsite-content-data").remove()
            doc.select("sectlevel1").remove()
            doc.select("feedback").remove()
            doc.select("additional-help").remove()
            doc.select("sidebar").remove()
            doc.select("vigor-title ").remove()
            doc.select("br").append("\\n")
            doc.outputSettings().syntax(Document.OutputSettings.Syntax.xml)

            String timestamp = new Date().format("yyyyMMdd_HHmmssSSS")
            String fileName = "PDF" + timestamp + ".pdf"

            filePath = pdfDownloadPath + File.separator + fileName
            convertHtmlToPdf(filePath, doc)
        } catch (Exception e) {
            log.error(e)
            driver.quit()
        }
        return filePath
    }

    void convertHtmlToPdf(String outputPdf, Document doc) {
        try {
            OutputStream outputStream = new FileOutputStream(outputPdf)
            ITextRenderer renderer = new ITextRenderer();
            SharedContext sharedContext = renderer.getSharedContext();
            sharedContext.setPrint(true);
            sharedContext.setInteractive(false);
            renderer.setDocumentFromString(doc.html());
            renderer.layout();
            renderer.createPDF(outputStream);
        } catch (Exception e) {
            log.error(e)
        }
    }
}
