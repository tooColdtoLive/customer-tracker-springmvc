<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Security Login Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css"/>
                                               <%-- context root = WEB-application root = context path = /webapp in this case --%>
                                               <%-- dynamic context path reference, keeping links relative --%>
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

                <c:if test="${registrationSuccess != null}">
                    <div class="alert alert-success">
                        ${registrationSuccess}
                    </div>
                </c:if>

                <form:form action="${pageContext.request.contextPath}/login/authenticate" method="POST">
                    <%-- form:form auto includes CSRF token, hidden name="_csrf", 403 without token --%>
                    <%-- for manually adding CSRF token, with jsp expression, spring security will add the appropriate value --%>
                    <%-- <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> --%>

                    <%-- for login form, fields name must be username / password , for spring security filter to process --%>

                    <div class="input-group login-input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text login-input-group-text"><i class="fa fa-user"></i></span>
                        </div>
                        <input name="username" type="text" class="form-control" placeholder="username" />
                    </div>

                    <div class="input-group login-input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text login-input-group-text"><i class="fa fa-lock"></i></span>
                        </div>
                        <input name="password" type="password" class="form-control" placeholder="password" />
                    </div>

                    <input type="submit" value="Login" class="btn btn-dark"/>

                    <c:if test="${param.error != null}">    <%-- error value returned in uri --%>
                        <i class="alert alert-danger">Invalid username / password</i>
                    </c:if>

                    <c:if test="${param.logout != null}">    <%-- error value returned in uri --%>
                        <i class="alert alert-success">Logged out successfully</i>
                    </c:if>
                </form:form>

                <div>
                    <span>Not a user? <a href="${pageContext.request.contextPath}/user/registration">Register</a></span>
                </div>

            </div>
        </div>
    </body>

</html>