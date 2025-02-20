package getbyID;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetById
 */
@WebServlet("/GetById")
public class GetById extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    String DB_URL = "jdbc:mysql://localhost:3306/Employee_DB";
    String DB_USER = "root";
    String DB_PASSWORD = "teja@929";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String empId = request.getParameter("employeeId");

        if (empId == null || empId.trim().isEmpty()) {
            out.println("<div class='alert alert-danger'>Employee ID is required.</div>");
            return;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            String sql = "SELECT * FROM EmpTable WHERE employeeId = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, empId);


            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                out.println("<table class='table table-bordered'>");
                out.println("<thead class='table-light'>");
                out.println("<tr><th>Field</th><th>Details</th></tr>");
                out.println("</thead>");
                out.println("<tbody>");
                out.println("<tr><td><b>Employee ID</b></td><td>" + rs.getString("employeeId") + "</td></tr>");
                out.println("<tr><td><b>Name</b></td><td>" + rs.getString("employeeName") + "</td></tr>");
                out.println("<tr><td><b>Department</b></td><td>" + rs.getString("department") + "</td></tr>");
                out.println("<tr><td><b>Email</b></td><td>" + rs.getString("email") + "</td></tr>");
                out.println("</tbody>");
                out.println("</table>");
            } else {
                out.println("<div class='alert alert-warning'>No employee found with ID: " + empId + "</div>");
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            out.println("<div class='alert alert-danger'>Error: " + e.getMessage() + "</div>");
            e.printStackTrace(out);
        }
    }
}
