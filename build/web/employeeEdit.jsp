<%-- 
    Document   : employeeEdit.jsp
    Created on : 27 Jun, 2023, 4:43:13 PM
    Author     : lENOVO
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1><c:out value="${editmessage}"></c:out></h1>
            <div>
                <form class="form" action="EmployeeControllerr" method="POST">
                    <input type="text" name="employeeEditId" placeholder="Enter Here" value="${employee.id}"/>
                    <p>NAME:  <input type="text" name="name1" placeholder="Enter Here" value="${employee.name}"/></p>
                <p>EMAIL:  <input type="text" name="email1" placeholder="Enter Here" value="${employee.email}"/></p>
                <p>PASSWORD:  <input type="password" name="password1" placeholder="Enter Here" value="${employee.password}"></p>
                <br>
                <p>SELECT GENDER</p>
                <c:choose>
                    <c:when test="${employee.gender eq 'male'}">
                        <input type="radio" id="gender1" name="gender1" value="male" checked="checked">
                        <label for="gender1">Male</label>
                          <input type="radio" id="gender2" name="gender1" value="female">
                          <label for="gender2">Female</label>
                    </c:when>
                    <c:when test="${employee.gender eq 'female'}">
                        <input type="radio" id="gender1" name="gender1" value="male">
                          <label for="gender1">Male</label>
                          <input type="radio" id="gender2" name="gender1" value="female" checked="checked">
                          <label for="gender2">Female</label>
                    </c:when>
                </c:choose>
                <br>
                <p>SELECT DISTRICT</p>

                <select name = "district_name1">
                    <c:choose>
                        <c:when test="${employee.district_name eq 'gwalior'}">
                            <option value="gwalior" selected="selected">gwalior</option>
                            <option value="Hyderabad">Hyderabad</option>
                            <option value="Punjab">Punjab</option>
                        </c:when>
                        <c:when test="${employee.district_name eq 'Hyderabad'}">
                            <option value="gwalior">gwalior</option>
                            <option value="Hyderabad" selected="selected">Hyderabad</option>
                            <option value="Punjab">Punjab</option>
                        </c:when>
                        <c:when test="${employee.district_name eq 'Punjab'}">
                            <option value="gwalior">gwalior</option>
                            <option value="Hyderabad">Hyderabad</option>
                            <option value="Punjab" selected="selected">Punjab</option>
                        </c:when>
                        <c:otherwise>
                            <option value="" selected="selected">Select</option>
                            <option value="gwalior">gwalior</option>
                            <option value="Hyderabad">Hyderabad</option>
                            <option value="Punjab">Punjab</option>
                        </c:otherwise>
                    </c:choose>
                </select>

                <br>

                <c:choose>
                    <c:when test="${employee.condition eq 'checked'}">
                        <p>select terms and conditions: <input type="checkbox" name="condition1" value="checked" checked="true"/></p>
                        </c:when>
                        <c:otherwise >
                        <p>select terms and conditions: <input type="checkbox" name="condition1" value="checked" checked="true"/></p>
                        </c:otherwise>
                    </c:choose>

                <br><br>
                <!--<input type="submit" name="edit" value="Edit">-->
                 <!--<a href="EmployeeControllerr?employeeEditSaveId=${employee.id}">Save</a>-->


                <input type="submit" name="task" value="Save">
            </form>
        </div>
    </body>
</html>
