/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weServices.controller;

import Bean.EmployeeBean;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.xml.ws.WebServiceContext;
import org.codehaus.jettison.json.JSONObject;
import weServices.Model.EmployeeWebservicesModel;

/**
 *
 * @author lENOVO
 */
@Path("/")
public class EmployeeWebServicesController {

    @Resource
    WebServiceContext wsContext;

    @GET
    @Path("/getEmployees")
    @Produces(MediaType.APPLICATION_JSON)  //http://192.168.1.15:8084/EmployeePractice/employees/getEmployees
    @Consumes(MediaType.APPLICATION_JSON)
    public List<EmployeeBean> getEmployees(String dataString) {
        EmployeeWebservicesModel employeeWebservicesModel = new EmployeeWebservicesModel();
        List<EmployeeBean> employeeList = null;
        try {
            System.out.println("Employee web services details fetiching.............");
            employeeWebservicesModel.setConnection();

            employeeList = employeeWebservicesModel.getAllemployeesDetails();
        } catch (Exception e) {
            System.out.println("Error in BLEWebServices 'requestData' url calling getWardData()..." + e);
        }
        return employeeList;
    }

    @POST
    @Path("/saveEmployeeDeails")
    @Produces(MediaType.APPLICATION_JSON)//http://192.168.1.15:8084/EmployeePractice/employees/saveEmployeeDeails
    @Consumes(MediaType.APPLICATION_JSON)
    public String saveEmployeeDetails(JSONObject dataString) {

//        String dataString1[] = dataString.split(",");
        String status = "";
        EmployeeWebservicesModel employeeWebservicesModel = new EmployeeWebservicesModel();
        try {
            employeeWebservicesModel.setConnection();

            EmployeeBean employee = new EmployeeBean();
            employee.setName(dataString.getString("name"));
            employee.setEmail(dataString.getString("email"));
            employee.setPassword(dataString.getString("password"));
            employee.setGender(dataString.getString("gender"));
            employee.setDistrict_name(dataString.getString("district_name"));
            employee.setCondition(dataString.getString("condition"));
            employee.setFileName(dataString.getString("fileName"));
            employee.setFilePath(dataString.getString("filePath"));

            int numberOfRows = employeeWebservicesModel.saveAllEmployeesDetails(employee);
            if (numberOfRows > 0) {
                status = "successfully add employee data.......";
            } else {
                status = "employee data not saved ......";
            }

        } catch (Exception e) {
            System.out.println(" " + e);
        }
        return status;
    }

    @DELETE
    @Path("/deleteEmployee")
    @Produces(MediaType.APPLICATION_JSON)//http://192.168.1.15:8084/EmployeePractice/employees/deleteEmployee
    @Consumes(MediaType.APPLICATION_JSON)
    public String deleteEmployee(@Context HttpServletRequest request, String dataString) {

        String status = "";
        EmployeeWebservicesModel employeeWebservicesModel = new EmployeeWebservicesModel();
        try {
            employeeWebservicesModel.setConnection();

            int empId = Integer.parseInt(request.getParameter("employeeDeleteId"));
            int rowsAffected = employeeWebservicesModel.deleteSingleEmployeeDetails(empId);

            if (rowsAffected > 0) {
                status = "Employee Delete Successfuly.......";
            } else {
                status = "employee not deleted ......";
            }

        } catch (Exception e) {
            System.out.println("weServices.controller.EmployeeWebServicesController()- " + e);
        }
        return status;
    }

    @PUT
    @Path("/editEmployee")
    @Produces(MediaType.APPLICATION_JSON)//http://192.168.1.15:8084/EmployeePractice/employees/deleteEmployee
    @Consumes(MediaType.APPLICATION_JSON)
    public String editEmployee(@Context HttpServletRequest request, JSONObject dataString) {

        String status = "";
        EmployeeWebservicesModel employeeWebservicesModel = new EmployeeWebservicesModel();
        int empId = Integer.parseInt(request.getParameter("employeeEditId"));
        try {
            employeeWebservicesModel.setConnection();

            EmployeeBean employee = new EmployeeBean();
            employee.setName(dataString.getString("name"));
            employee.setEmail(dataString.getString("email"));
            employee.setPassword(dataString.getString("password"));
            employee.setGender(dataString.getString("gender"));
            employee.setDistrict_name(dataString.getString("district_name"));
            employee.setCondition(dataString.getString("condition"));
            employee.setFileName(dataString.getString("fileName"));
            employee.setFilePath(dataString.getString("filePath"));

            int numberOfRows = employeeWebservicesModel.editAllEmployeesDetails(empId, employee);
            if (numberOfRows > 0) {
                status = "successfully Edit employee data.......";
            } else {
                status = "employee data not Edit ......";
            }

        } catch (Exception e) {
            System.out.println("(\"weServices.controller.EmployeeWebServicesController()-  " + e);
        }
        return status;
    }

}
