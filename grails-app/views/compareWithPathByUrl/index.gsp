<%--
  Created by IntelliJ IDEA.
  User: yohannesmanurung
  Date: 12/01/24
  Time: 18.39
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <meta name="layout" content="main" />
        <title></title>
    </head>
    <body>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.back.label"/></a></li>
            </ul>
        </div>
        <div class="container">
            <h1 class="main-title"><g:message code="default.compareWithPathByUrl.label"/></h1>

            <g:form controller="compareWithPathByUrl" action="inputFileExcel" method="POST" enctype="multipart/form-data"
                    class="js-excel-upload__form"
                    data-status-url="${createLink(controller: 'compareWithPathByUrl', action: 'importStatus')}">
                <div>
                    <div class="form-group import-progress" style="${flash.message ? '' : 'display:none'}">
                        <p><g:message code="default.status.runningJob.label" default="Running job"/></p>

                        <div class="progress">
                            <div class="progress-bar" role="progressbar" aria-valuenow="0"
                                 aria-valuemin="0" aria-valuemax="100" style="width:0%">
                                <span class="percentage"></span>
                            </div>
                        </div>
                    </div>
                </div>
                <div>
                    <div>
                        <label class="font-weight-bold" for="fileExcel"><label for="fileExcel"><g:message code="default.excelFile.label"/></label></label>
                    </div>
                    <div>
                        <input type="file" id="fileExcel" name="fileExcel">
                    </div>
                </div>
                <div class="btn-check text-center">
                    <button class="btn-generate" type="submit" disabled><g:message code="default.check.label"/></button>
                </div>
            </g:form>
            <div class="form-group">
                <code class="import-error-message"></code>
            </div>
        </div>
    </body>
</html>