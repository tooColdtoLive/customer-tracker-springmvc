<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Customer Form</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css"/>

    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <img id="logo" src="../resources/img/crm_logo.png" alt="CRM">
                <h2>CRM - Customer Relationship Management</h2>
            </div>
        </div>

        <div id="container">
            <h3 class="tableTitle">Add Customer</h3>

            <form:form action="saveCustomer" modelAttribute="customer" method="POST">
                <form:hidden path="id" />   <%-- without the hidden field, id will be lost when form submitted, as setter is run --%>

                <div class="form-group">
                    <label for="inputFirstName">First Name</label>
                    <form:input id="inputFirstName" path="firstName" type="text" class="form-control" placeholder="Enter first name" />
                    <form:errors path="firstName" cssClass="invalid-feedback"/>
                </div>
                <div class="form-group">
                    <label for="inputLastName">Last Name</label>
                    <form:input id="inputLastName" path="lastName" type="text" class="form-control" placeholder="Enter last name"/>
                    <form:errors path="lastName" cssClass="invalid-feedback"/>
                </div>
                <div class="form-group">
                    <label for="inputEmail">Email</label>
                    <form:input id="inputEmail" path="email" type="email" class="form-control" placeholder="Enter email"/>
                    <form:errors path="email" cssClass="invalid-feedback"/>
                </div>
                <input type="submit" value="Save" class="btn btn-dark"/>
            </form:form>

            <div class="goBackHref">
                <a href="${pageContext.request.contextPath}/customer/list" class="link-primary">Back to Customer List</a>
            </div>
        </div>
    </body>
</html>