package Controller;

import Bean.EmployeeBean;
import Model.EmployeeModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;

public class EmployeeController extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        ServletContext ctx = getServletContext();
        EmployeeModel employeeModel = new EmployeeModel();
        employeeModel.setDriverClass(ctx.getInitParameter("driverClass"));
        employeeModel.setConnectionString(ctx.getInitParameter("connectionString"));
        employeeModel.setDb_username(ctx.getInitParameter("db_username"));
        employeeModel.setDb_password(ctx.getInitParameter("db_password"));
        employeeModel.setConnection();

        ///////////////////save employee Data//////////////////////
        String submit = req.getParameter("submit");
        if (submit == null) {
            submit = "";
        }
        if (submit.equals("Submit")) {
            EmployeeBean employee = new EmployeeBean();
            employee.setName(req.getParameter("name"));
            employee.setEmail(req.getParameter("email"));
            employee.setPassword(req.getParameter("password"));
            employee.setGender(req.getParameter("gender"));
            employee.setDistrict_name(req.getParameter("district_name"));
            employee.setCondition(req.getParameter("condition"));

            out.println(req.getParameter("name"));
            out.println(req.getParameter("email"));
            out.println(req.getParameter("password"));
            out.println(req.getParameter("gender"));
            out.println(req.getParameter("district_name"));
            out.println(req.getParameter("condition"));
            System.out.println("Insertig values by model....");
            int numberOfRows = employeeModel.saveEmployeeData(employee);

        }

        ////////////Show all employee data/////////
        List<EmployeeBean> employeeList = employeeModel.showEmployeeData();

        req.setAttribute("employeeList", employeeList);
        req.setAttribute("mymessage", "Hi i am use JSP");
        req.getRequestDispatcher("/employeeForm.jsp").forward(req, resp);

        ///////////////////Delete employee//////////////////////
      
        String employeeId = req.getParameter("employeeId");
        
        if (employeeId != null && employeeId != "") {
            int empId = Integer.parseInt(employeeId);
            int rowsAffected = employeeModel.deleteEmployeeData(empId);
        }
    }

}
