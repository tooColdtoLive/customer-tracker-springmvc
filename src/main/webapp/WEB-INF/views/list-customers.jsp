<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
    <html>
        <head>
            <title>List Customers</title>
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css"/>
        </head>

        <body>
            <div id="wrapper">
                <div id="header">
                    <h2>CRM - Customer Relationship Management</h2>
                </div>
            </div>

            <div id="container">
                <div id="content">

                    <div class="row">
                        <div class="col">
                            <p>First Name</p>
                        </div>
                        <div class="col">
                            <p>Last Name</p>
                        </div>
                        <div class="col">
                            <p>Email</p>
                        </div>
                    </div>

                    <c:forEach var="customer" items="${customers}">
                        <div class="row">
                            <div class="col">
                                <p>${customer.firstName}</p>
                            </div>
                            <div class="col">
                                <p>${customer.lastName}</p>
                            </div>
                            <div class="col">
                                <p>${customer.email}</p>
                            </div>
                        </div>
                    </c:forEach>

                </div>
            </div>
        </body>

    </html>
