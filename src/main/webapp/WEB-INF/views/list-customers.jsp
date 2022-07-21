<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
    <html>
        <head>
            <title>List Customers</title>
            <link rel="icon" href="resources/img/crm_logo.png" type = "image/x-icon">
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
                <div>
                    <input id="addCustomerBtn" class="btn btn-dark btn-lg"
                    type="button" value="Add Customer" onclick="window.location.href='showAddForm'; return false;" />
                </div>
                <div id="content">
                    <h3 class="tableTitle">Customer List</h3>
                    <div class="row">
                        <div class="col">
                            <p class="colTitle">First Name</p>
                        </div>
                        <div class="col">
                            <p class="colTitle">Last Name</p>
                        </div>
                        <div class="col">
                            <p class="colTitle">Email</p>
                        </div>
                        <div class="col">
                            <p class="colTitle">Action</p>
                        </div>
                    </div>

                    <c:forEach var="customer" items="${customers}">
                        <c:url var="updateLink" value="/customer/showUpdateForm">
                            <c:param name="customerId" value="${customer.id}"/>
                        </c:url>
                        <c:url var="deleteLink" value="/customer/deleteCustomer">
                            <c:param name="customerId" value="${customer.id}"/>
                        </c:url>

                        <div class="row customer-row">
                            <div class="col">
                                <p>${customer.firstName}</p>
                            </div>
                            <div class="col">
                                <p>${customer.lastName}</p>
                            </div>
                            <div class="col">
                                <p>${customer.email}</p>
                            </div>
                            <div class="col">
                                <p>
                                    <a href="${updateLink}" class="link-primary">Update</a>
                                    <span> | </span>
                                    <a href="${deleteLink}" class="link-primary"
                                        onclick="if(!(confirm('Confirm to delete customer ${customer.firstName} ${customer.lastName}'))) return false;">Delete</a>
                                </p>
                            </div>
                        </div>
                    </c:forEach>

                </div>
            </div>
        </body>

    </html>
