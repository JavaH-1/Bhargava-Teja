

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EmployeeDetailsServlet
 */
@WebServlet("/EmployeeDetailsServlet")
public class EmployeeDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 // Database credentials (update as needed)
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Employee_DB";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "teja@929";

    // JDBC method to get the database connection
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    // Handling GET request to display employee data by ID
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set the response content type to HTML
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Retrieve the employee ID from the request
        String employeeId = request.getParameter("employeeId");

        // Start the HTML structure with Bootstrap
        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>Employee Details</title>");
        out.println("<link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css'>");
        out.println("</head>");
        out.println("<body class='bg-light'>");
        out.println("<div class='container mt-5'>");
        out.println("<h3 class='text-center'>Employee Details</h3>");

        // Validate the input
        if (employeeId == null || employeeId.isEmpty()) {
            out.println("<div class='alert alert-danger text-center'>Employee ID is required!</div>");
        } else {
            // Display employee details for the specific ID
            try (Connection conn = getConnection()) {
                String query = "SELECT employeeId, employeeName, email, phone, department, dob, emergencyContact, doj, bank, country, state FROM EmpTable WHERE employeeId = ?";
                
                try (PreparedStatement ps = conn.prepareStatement(query)) {
                    ps.setInt(1, Integer.parseInt(employeeId));
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            // Display the employee details
                            out.println("<table class='table table-bordered mt-4'>");
                            out.println("<tr><th>ID</th><td>" + rs.getInt("employeeId") + "</td></tr>");
                            out.println("<tr><th>Name</th><td>" + rs.getString("employeeName") + "</td></tr>");
                            out.println("<tr><th>Email</th><td>" + rs.getString("email") + "</td></tr>");
                            out.println("<tr><th>Phone</th><td>" + rs.getString("phone") + "</td></tr>");
                            out.println("<tr><th>Department</th><td>" + rs.getString("department") + "</td></tr>");
                            out.println("<tr><th>Date of Birth</th><td>" + rs.getDate("dob") + "</td></tr>");
                            out.println("<tr><th>Emergency Contact</th><td>" + rs.getString("emergencyContact") + "</td></tr>");
                            out.println("<tr><th>Date of Joining</th><td>" + rs.getDate("doj") + "</td></tr>");
                            out.println("<tr><th>Bank</th><td>" + rs.getString("bank") + "</td></tr>");
                            out.println("<tr><th>Country</th><td>" + rs.getString("country") + "</td></tr>");
                            out.println("<tr><th>State</th><td>" + rs.getString("state") + "</td></tr>");
                            out.println("</table>");
                        } else {
                            out.println("<div class='alert alert-warning text-center'>No employee found with ID: " + employeeId + "</div>");
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                out.println("<div class='alert alert-danger text-center'>Error fetching employee details: " + e.getMessage() + "</div>");
            } catch (NumberFormatException e) {
                out.println("<div class='alert alert-danger text-center'>Invalid Employee ID format!</div>");
            }
        }

        // End the HTML
        out.println("</div>"); // Close container
        out.println("<script src='https://code.jquery.com/jquery-3.5.1.slim.min.js'></script>");
        out.println("<script src='https://cdn.jsdelivr.net/npm/@popperjs/core@2.4.4/dist/umd/popper.min.js'></script>");
        out.println("<script src='https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js'></script>");
        out.println("</body>");
        out.println("</html>");
    }}
