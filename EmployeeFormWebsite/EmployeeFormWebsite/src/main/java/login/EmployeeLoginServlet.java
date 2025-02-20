package login;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;

/**
 * Servlet implementation class LoginDataServlet
 */
@WebServlet("/EmployeeLoginServlet")
public class EmployeeLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Employee_DB";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "teja@929";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        session.setAttribute("username", username);

        try {
            if (validateUser(username, password)) {
                out.write("{\"status\":\"success\",\"message\":\"Login successful!\"}");
            } else {
                out.write("{\"status\":\"error\",\"message\":\"Invalid username or password.\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.write("{\"status\":\"error\",\"message\":\"An unexpected error occurred.\"}");
        } finally {
            out.flush();
        }
    }

    private boolean validateUser(String username, String password) {
        boolean isValid = false;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                String sql = "SELECT * FROM EmpTable WHERE username = ? AND password = ?";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, username);
                    statement.setString(2, password);

                    try (ResultSet resultSet = statement.executeQuery()) {
                        isValid = resultSet.next();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isValid;
    }
}
