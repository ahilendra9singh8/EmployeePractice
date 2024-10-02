<%-- 
    Document   : employeeForm.jsp
    Created on : 26 Jun, 2023, 1:10:35 PM
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
        <h1>Hello World!</h1>
        <div>
            <form class="form" action="EmployeeControllerr" method="POST" enctype="multipart/form-data">
                <p>NAME:  <input type="text" name="name" placeholder="Enter Here"/></p>
                <p>EMAIL:  <input type="text" name="email" placeholder="Enter Here"/></p>
                <p>PASSWORD:  <input type="password" name="password" placeholder="Enter Here"></p>
                <br>
                <p>SELECT GENDER</p>
                  <input type="radio" name="gender" value="male">
                  <label for="gender1">Male</label><br>
                  <input type="radio" name="gender" value="female">
                  <label for="gender2">Female</label>
                <br>
                <p>SELECT DISTRICT</p>
                <select name = "district_name">
                    <option value="" selected="selected">Select</option>
                    <option value="gwalior">gwalior</option>
                    <option value="Hyderabad">Hyderabad</option>
                    <option value="Punjab">Punjab</option>
                </select>
                <br><br>
                <p>select File</p>
                <input type="file" name="fileName" />
                <br><br>
                <p>select terms and conditions: <input type="checkbox" name="condition" value="checked"/></p>

                <br><br>
                <input type="submit" name="submit" value="Submit">
            </form>
        </div>

        <br> <br> <br> <br> <br>

        <div >
            <h1><c:out value="${mymessage}"></c:out></h1>

                <form name="form" method="POST" action="EmployeeControllerr">
                    <table>
                        <thead>
                            <tr>
                                <th> ID </th>
                                <th> Name </th>
                                <th> Email</th>
                                <th> Password</th>
                                <th> Gender</th>
                                <th> DistrictName</th>
                                <th> Image </th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="employee" items="${employeeList}"  varStatus="loopCounter">
                            <tr name="employeeId" value="${employee.id}">
                                <td>${employee.id}</td>
                                <td>${employee.name}</td>
                                <td>${employee.email}</td>
                                <td>${employee.password}</td>
                                <td>${employee.gender}</td>
                                <td>${employee.district_name}</td>
                                <td><img src="img/${employee.fileName}" alt="File Display" width="50" height="50"/></td>
                                <td><a href="EmployeeControllerr?fileNameForDownload=${employee.fileName}">Download file</a></td>
                                 <td>
                                    <a href="EmployeeControllerr?employeeEditId=${employee.id}">Edit</a>
                                </td>
                                <td>
                                    <a href="EmployeeControllerr?employeeDeleteId=${employee.id}">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </form>

        </div> 
    </body>
</html>
