<%--
  Created by IntelliJ IDEA.
  User: yohannesmanurung
  Date: 15/01/24
  Time: 10.45
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
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
            <h1 class="main-title"><g:message code="default.Failed.label"/></h1>
        </div>
    </body>
</html>
