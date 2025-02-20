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

@WebServlet("/DropdownDataServlet")
public class DropdownDataServlet extends HttpServlet {
 
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        String country = request.getParameter("country");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        try {
        	String dbDriver = "com.mysql.cj.jdbc.Driver";
            String dbURL = "jdbc:mysql://localhost:3306/countries";
            String dbUsername = "root"; 
            String dbPassword = "teja@929"; 

            Class.forName(dbDriver); 
            Connection con = DriverManager.getConnection(dbURL, dbUsername, dbPassword);

            String query = "";
            if ("countries".equals(type)) {
                query = "SELECT country_name FROM countries";
            } else if ("states".equals(type) && country != null) {
                query = "SELECT state_name FROM states WHERE country_name = ?";
            }

            PreparedStatement stmt = con.prepareStatement(query);

            if ("states".equals(type)) {
                stmt.setString(1, country);
            }

            ResultSet rs = stmt.executeQuery();
            StringBuilder json = new StringBuilder("[");
            while (rs.next()) {
                json.append("\"").append(rs.getString(1)).append("\",");
            }
            if (json.length() > 1) {
                json.deleteCharAt(json.length() - 1); 
            }
            json.append("]");

            out.print(json.toString());
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            out.print("[]");
        }
    }
}
