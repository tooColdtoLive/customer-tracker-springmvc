<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Security Login Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css"/>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <img id="logo" src="../resources/img/crm_logo.png" alt="CRM">
                <h2>CRM - Customer Relationship Management</h2>
            </div>
        </div>

        <div id="container">
            <div id="content">
                <h3 class="tableTitle">Login Page</h3>

                <c:if test="${registrationError != null}">
                    <div class="alert alert-danger">
                        ${registrationError}
                    </div>
                </c:if>

                <form:form action="${pageContext.request.contextPath}/user/registration/register" modelAttribute="user" method="POST">
                    <div class="form-group">
                        <label for="inputUsername">Username</label>
                        <form:input id="inputUsername" path="username" type="text" class="form-control"/>
                        <form:errors path="username" cssClass="invalid"/>
                    </div>

                    <div class="form-group">
                        <label for="inputPassword">Password</label>
                        <form:input id="inputPassword" path="password" type="password" class="form-control"/>
                        <form:errors path="password" cssClass="invalid"/>
                    </div>

                    <div class="form-group">
                        <label for="inputMatchingPassword">Verify Password</label>
                            <form:input id="inputMatchingPassword" path="matchingPassword" type="password" class="form-control"/>
                            <form:errors path="matchingPassword" cssClass="invalid"/>
                    </div>

                    <div class="form-group">
                        <label for="inputFirstName">First Name</label>
                            <form:input id="inputFirstName" path="firstName" type="text" class="form-control"/>
                            <form:errors path="firstName" cssClass="invalid"/>
                    </div>

                    <div class="form-group">
                        <label for="inputLastName">Last Name</label>
                            <form:input id="inputLastName" path="lastName" type="text" class="form-control"/>
                            <form:errors path="lastName" cssClass="invalid"/>
                    </div>

                    <div class="form-group">
                        <label for="inputEmail">Email</label>
                        <form:input id="inputEmail" path="email" type="email" class="form-control"/>
                        <form:errors path="email" cssClass="invalid"/>
                    </div>

                    <input type="submit" value="Register" class="btn btn-dark"/>
                </form:form>
            </div>
        </div>
    </body>

</html>