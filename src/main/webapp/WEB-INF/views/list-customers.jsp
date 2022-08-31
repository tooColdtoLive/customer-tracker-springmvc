<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page import ="com.luv2code.customerTracker.util.*" %>

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

                    <form:form action="searchCustomer" method="GET">
                        <label for="searchCriteria">Search Customer</label>
                        <div id="searchCriteria" >
                            <div class="inlineBlock">
                                <select id="searchType" name="searchType">
                                  <option value="firstName">First Name</option>
                                  <option value="lastName">Last Name</option>
                                  <option value="email">Email</option>
                                </select>
                            </div>
                            <div class="inlineBlock">
                                <input id="searchString" name="searchString" type="text" class="form-control" placeholder="case insensitive"/>
                                <%-- name for @RequestParam value --%>
                            </div>
                        </div>

                        <input type="submit" value="Search" class="btn btn-dark"/>   <%-- form:input tag requires path, use input tag when suitable --%>

                        <form:form action="list" method="GET">
                            <input id="showAllBtn" type="submit" value="Show All" class="btn btn-dark"/>
                        </form:form>
                    </form:form>

                    <div class="logoutForm">
                        <form:form action="${pageContext.request.contextPath}/logout" method="POST">
                            <input type="submit" value="Logout" class="btn btn-dark"/>
                        </form:form>
                    </div>

                    <c:url var="sortLinkFirstName" value="/customer/list">
                        <c:param name="sort" value="<%= Integer.toString(CustomerSortUtil.FIRST_NAME) %>" />
                    </c:url>
                    <c:url var="sortLinkLastName" value="/customer/list">
                        <c:param name="sort" value="<%= Integer.toString(CustomerSortUtil.LAST_NAME) %>" />
                    </c:url>
                    <c:url var="sortLinkEmail" value="/customer/list">
                        <c:param name="sort" value="<%= Integer.toString(CustomerSortUtil.EMAIL) %>" />
                    </c:url>
                    <div class="row colTitleRow">
                        <div class="col">
                            <a href="${sortLinkFirstName}" class="colTitle link-primary">First Name</a>
                        </div>
                        <div class="col">
                            <a href="${sortLinkLastName}" class="colTitle link-primary">Last Name</a>
                        </div>
                        <div class="col">
                            <a href="${sortLinkEmail}" class="colTitle link-primary">Email</a>
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
