<%--
  Created by IntelliJ IDEA.
  User: m.ihsanyusuflubis
  Date: 18/01/24
  Time: 15.43
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
            <h1 class="main-title"><g:message code="default.generatePdf.label"/></h1>
            <g:form controller="generatePdf" action="generate" method="POST" enctype="multipart/form-data">
                <div>
                    <div>
                        <label class="font-weight-bold" for="websiteUrl" ><g:message code="default.websiteUrl.label"/></label>
                    </div>
                    <div>
                        <input type="url" id="websiteUrl" name="websiteUrl">
                    </div>
                    <div class="btn-check text-center">
                        <button class="btn-generate" type="submit" disabled><g:message code="default.generate.label"/></button>
                    </div >
                </div>
            </g:form>
        </div>
    </body>
</html>