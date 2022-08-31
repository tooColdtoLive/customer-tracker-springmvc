<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
    <html>
        <head>
            <title>Access Denied Page</title>
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
            <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css"/>
        </head>

        <body>
            <div id="wrapper">
                <div id="header">
                    <h2>Spring Security Demo</h2>
                </div>
            </div>

            <div id="container">
                <div id="content">
                    <h3 class="tableTitle">Access Denied</h3>
                    <h5>You are not authorized to access this page</h5>

                    <a href="${pageContext.request.contextPath}/home">Return to Home Page</a>
                </div>
            </div>
        </body>

    </html>
