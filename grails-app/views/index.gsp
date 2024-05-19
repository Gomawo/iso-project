<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Welcome to Grails</title>
</head>
<body>

<div id="content" style="padding-bottom: 30px;" >
    <section class="row" style="display: table">
            <h1>Document Checker</h1>
            <p>
                Below are the features that you can currently use in this application, select and click on the feature you need.
            </p>
        <div id="controllers" role="navigation">
            <div><h2 style="text-align: center; padding-bottom: 20px;">Available Features:</h2></div>
            <div>
                <ul style="display: flex; list-style: none; justify-content: space-between">
                    %{--<g:each var="c" in="${grailsApplication.controllerClasses.findAll { it.fullName.startsWith('miniproject') }.sort { it.fullName } }">
                        <li class="controller">
                            <button type="button" onclick="location.href='${createLink(controller: c.logicalPropertyName)}'">${c.fullName}</button>
                        </li>
                    </g:each>--}%
                    <li class="controller">
                        <button type="button" onclick="location.href='/compareTextByText/index'"><g:message code="default.textToTextController.label"/></button>
                    </li>
                    <li class="controller">
                        <button type="button" onclick="location.href='/compareWithPathByUrl/index'"><g:message code="default.pathByUrlController.label"/></button>
                    </li>
                    <li class="controller">
                        <button type="button" onclick="location.href='/generatePdf/index'"><g:message code="default.generatePdfController.label"/></button>
                    </li>
                </ul>
            </div>
        </div>
    </section>
</div>
</body>
</html>
