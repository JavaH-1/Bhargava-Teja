package employeeDashboard;

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
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class EmployeeDashboardServlet
 */
@WebServlet("/EmployeeDashboardServlet")
public class EmployeeDashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 private static final String DB_URL = "jdbc:mysql://localhost:3306/Employee_DB";
	    private static final String DB_USER = "root";
	    private static final String DB_PASSWORD = "teja@929";

	    @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        HttpSession session = request.getSession(false);
	        if (session == null || session.getAttribute("username") == null) {
	            response.setContentType("application/json");
	            response.getWriter().write("{\"status\":\"error\",\"message\":\"Unauthorized access.\"}");
	            return;
	        }

	        String username = (String) session.getAttribute("username");

	        response.setContentType("application/json");
	        try (PrintWriter out = response.getWriter()) {
	            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
	                String sql = "SELECT employeeName, email, department, doj FROM EmpTable WHERE username = ?";
	                try (PreparedStatement statement = connection.prepareStatement(sql)) {
	                    statement.setString(1, username);

	                    try (ResultSet resultSet = statement.executeQuery()) {
	                        if (resultSet.next()) {
	                            String json = String.format(
	                                "{\"status\":\"success\",\"data\":{\"name\":\"%s\",\"email\":\"%s\",\"department\":\"%s\",\"doj\":\"%s\"}}",
	                                resultSet.getString("employeeName"), 
	                                resultSet.getString("email"),
	                                resultSet.getString("department"),
	                                resultSet.getString("doj")
	                            );
	                            out.print(json);
	                        } else {
	                            out.print("{\"status\":\"error\",\"message\":\"Employee not found.\"}");
	                        }
	                    }
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
	                out.print("{\"status\":\"error\",\"message\":\"An unexpected error occurred.\"}");
	            }
	        }
	    }
}
