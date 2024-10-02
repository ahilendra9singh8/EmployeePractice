/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Bean.EmployeeBean;
import Model.EmployeeModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author lENOVO
 */
//@WebServlet(name = "EmployeeControllerr", urlPatterns = {"/EmployeeControllerr"})
@MultipartConfig
public class EmployeeControllerr extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        ServletContext ctx = getServletContext();
        EmployeeModel employeeModel = new EmployeeModel();
        employeeModel.setDriverClass(ctx.getInitParameter("driverClass"));
        employeeModel.setConnectionString(ctx.getInitParameter("connectionString"));
        employeeModel.setDb_username(ctx.getInitParameter("db_username"));
        employeeModel.setDb_password(ctx.getInitParameter("db_password"));
        employeeModel.setConnection();

        ///////////////////save employee Data//////////////////////
        String submit = request.getParameter("submit");
        if (submit == null) {
            submit = "";
        }
        if (submit.equals("Submit")) {
            EmployeeBean employee = new EmployeeBean();
            employee.setName(request.getParameter("name"));
            employee.setEmail(request.getParameter("email"));
            employee.setPassword(request.getParameter("password"));
            employee.setGender(request.getParameter("gender"));
            employee.setDistrict_name(request.getParameter("district_name"));
            employee.setCondition(request.getParameter("condition"));

//            //File
            Part filePart = request.getPart("fileName");
            String fileName = filePart.getSubmittedFileName();
            employee.setFileName(fileName);

            // Get the file content
            InputStream fileContent = filePart.getInputStream();
            byte []data = new byte[fileContent.available()];
            fileContent.read(data);
            String path = request.getRealPath("/") + "img" + File.separator + fileName;
            employee.setFilePath(path);
            FileOutputStream fos = new FileOutputStream(path);
            fos.write(data);
            fos.close();
            
            int numberOfRows = employeeModel.saveEmployeeData(employee);
            
        }
        
        String employeeDeleteId = request.getParameter("employeeDeleteId");
        String employeeEditId = request.getParameter("employeeEditId");
        
        if (employeeDeleteId != null && employeeDeleteId != "") {
            ///////////////////Delete employee//////////////////////
            int empId = Integer.parseInt(employeeDeleteId);
            int rowsAffected = employeeModel.deleteEmployeeData(empId);
//            request.getRequestDispatcher("/employeeForm.jsp").forward(request, response);
        }
        
        if (employeeEditId != null && employeeEditId != "") {
            /////////Edit Single employee at a time///////////////////
            EmployeeBean employee = new EmployeeBean();
            int empId = Integer.parseInt(employeeEditId);
            employee = employeeModel.getsingleEmployee(empId);
            
            request.setAttribute("editmessage", "Edit Employee detail........");
            request.setAttribute("employee", employee);
            request.getRequestDispatcher("/employeeEdit.jsp").forward(request, response);
            
        } else {
            ////////////Show all employee data/////////

            List<EmployeeBean> employeeList = employeeModel.showEmployeeData();
            request.setAttribute("employeeList", employeeList);
            request.setAttribute("mymessage", "Showing EmployeeList........");
            request.getRequestDispatcher("/employeeForm.jsp").forward(request, response);
        }

        //////////Edit Employee Details////////////
        String task = request.getParameter("task");
        if (task == null) {
            task = "";
        }
        if (task.equals("Save")) {
            String employeeEditSaveId = request.getParameter("employeeEditId");
            
            if (employeeEditSaveId != null && employeeEditSaveId != "") {
                int empId = Integer.parseInt(employeeEditSaveId);
                String abcd = request.getParameter("name1");
                EmployeeBean employee = new EmployeeBean();
                employee.setName(request.getParameter("name1"));
                employee.setEmail(request.getParameter("email1"));
                employee.setPassword(request.getParameter("password1"));
                employee.setGender(request.getParameter("gender1"));
                employee.setDistrict_name(request.getParameter("district_name1"));
                employee.setCondition(request.getParameter("condition1"));
                
                int numberOfRows = employeeModel.editEmployeeDetails(employee, empId);
                request.setAttribute("mymessage", "Employee Edit Successfully........");
                if (numberOfRows > 0) {
                    request.getRequestDispatcher("/employeeForm.jsp").forward(request, response);
                }
            }
        }
        
        //Download file 
        
//        String fileNameForDownload = request.getParameter("fileNameForDownload");
//        
//          if (fileNameForDownload != null && fileNameForDownload != "") {
//              String path = request.getRealPath("/") + "img" + File.separator + fileNameForDownload;
//              
//              File dwFile = new File(path);
//              if(dwFile.exists()){
//                  response.setContentType("application/octet-stream");
//                  response.setContentLength((int) dwFile.length());
//                  //Force download
////                  String hkey = "Content-Disposition";
////                  String hValue = String.format("attachment;fileNameForDownload=\"%s\"",dwFile.getName());
////                  attachment; filename=\"" + file.getName() + "\""
//                  response.setHeader(hkey,hValue);
//                  
//                  FileInputStream in = new FileInputStream(dwFile);
//                  
////                  int i;
////                  while((i=in.read())!= -1){
////                  out.write(i);
////                  }
//                  in.close();
//                  out.close();
//              }else{
//                  out.println("File not available......");
//              }
//          }
       
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
