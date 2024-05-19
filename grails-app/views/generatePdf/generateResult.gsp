<%--
  Created by IntelliJ IDEA.
  User: m.ihsanyusuflubis
  Date: 18/01/24
  Time: 15.44
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
            <h1 class="main-title"><g:message code="default.pdfSuccess.label"/></h1>
        </div>
    </body>
</html>