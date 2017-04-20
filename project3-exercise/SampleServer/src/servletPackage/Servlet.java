package servletPackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import sun.tools.jar.resources.jar;


/**
 * Servlet implementation class Servlet
 */
@WebServlet(description = "This is a sample servlet", urlPatterns = { "/Servlet" })
public class Servlet extends HttpServlet implements javax.servlet.Servlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//System.out.println("Test");
		//String searchTerm = request.getParameter("searchTerm");
		//PrintWriter writer = response.getWriter();
		//writer.println("You searched for...." + searchTerm);
		
		List<Student> list = new ArrayList<>();
		Student s1 = new Student(1, "Ardi");
		Student s2 = new Student(2, "Pratama");
		list.add(s1); list.add(s2);
		
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		
		JsonObject jsonObject = new JsonObject();
		Gson gson = new Gson();
		
		JsonArray jArray = new JsonArray();
		for (Student student : list) {
			JsonObject jObject = new JsonObject();
			jObject.addProperty("id", student.getId());
			jObject.addProperty("name", student.getName());
			jArray.add(jObject);
		}

		jsonObject.add("data", jArray);
		jsonObject.addProperty("message", "Hello from server");

		writer.println(jsonObject);
		
		
	}

}
