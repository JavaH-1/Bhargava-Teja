package jsdataservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DatabaseConnection;


@WebServlet("/JsDataServletJQuery")
public class JsDataServletJQuery extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String employeeName = request.getParameter("employeeName");
	    String employeeId = request.getParameter("employeeId");
	    String email = request.getParameter("email");
	    String phone = request.getParameter("phone");
	    String department = request.getParameter("department");
	    String dob = request.getParameter("dob");
	    String emergencyContact = request.getParameter("emg-contact");
	    String doj = request.getParameter("doj");
	    String bank = request.getParameter("bank");

	    response.setContentType("application/json");
	    PrintWriter out = response.getWriter();
	    try {
	        Connection con = DatabaseConnection.initializeDatabase();

	        PreparedStatement st = con.prepareStatement(
	            "INSERT INTO EmpTable(employeeName, employeeId, email, phone, department, dob, emergencyContact, doj, bank) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"
	        );

	        st.setString(1, employeeName);
	        st.setString(2, employeeId);
	        st.setString(3, email);
	        st.setString(4, phone);
	        st.setString(5, department);
	        st.setString(6, dob);
	        st.setString(7, emergencyContact);
	        st.setString(8, doj);
	        st.setString(9, bank);

	        st.executeUpdate();
	        st.close();
	        con.close();

	        out.write("{ \"status\": \"success\", \"message\": \"Successfully Inserted\" }");
	    } catch (Exception e) {
	        out.write("{ \"status\": \"error\", \"message\": \"" + e.getMessage() + "\" }");
	    }
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String empId = request.getParameter("searchEmployeeId");

	    response.setContentType("application/json");
	    PrintWriter out = response.getWriter();

	    try {
	        Connection con = DatabaseConnection.initializeDatabase();
	        PreparedStatement ps = con.prepareStatement("SELECT * FROM EmpTable WHERE employeeId=?");
	        ps.setString(1, empId);

	        ResultSet rs = ps.executeQuery();

	        if (!rs.isBeforeFirst()) {
	            out.write("{ \"status\": \"not_found\", \"message\": \"No employee found\" }");
	        } else {
	            StringBuilder json = new StringBuilder();
	            json.append("{ \"status\": \"success\", \"employees\": [");

	            while (rs.next()) {
	                json.append("{");
	                json.append("\"employeeName\": \"" + rs.getString("employeeName") + "\",");
	                json.append("\"employeeId\": \"" + rs.getString("employeeId") + "\",");
	                json.append("\"email\": \"" + rs.getString("email") + "\",");
	                json.append("\"phone\": \"" + rs.getString("phone") + "\",");
	                json.append("\"department\": \"" + rs.getString("department") + "\",");
	                json.append("\"dob\": \"" + rs.getString("dob") + "\",");
	                json.append("\"emergencyContact\": \"" + rs.getString("emergencyContact") + "\",");
	                json.append("\"doj\": \"" + rs.getString("doj") + "\",");
	                json.append("\"bank\": \"" + rs.getString("bank") + "\"");
	                json.append("},");
	            }

	            json.setLength(json.length() - 1);
	            json.append("]}");

	            out.write(json.toString());
	        }
	        ps.close();
	        con.close();
	    } catch (Exception e) {
	        out.write("{ \"status\": \"error\", \"message\": \"" + e.getMessage() + "\" }");
	    }
	}


}
