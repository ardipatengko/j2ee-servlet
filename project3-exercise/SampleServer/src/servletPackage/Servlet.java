package servletPackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
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
@MultipartConfig
public class Servlet extends HttpServlet implements javax.servlet.Servlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	public List<Student> list = new ArrayList<>();
	Student s1 = new Student(1, "Ardi");
	Student s2 = new Student(2, "Pratama");
	Student s3 = new Student(3, "Patengko");
	Student s4 = new Student(4, "George");
	Student s5 = new Student(5, "Gates");
	
	protected void AddData(){
		list.clear();
		list.add(s1); 
		list.add(s2);
		list.add(s3);
		list.add(s4);
		list.add(s5);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//System.out.println("Test");
		//String searchTerm = request.getParameter("searchTerm");
		//PrintWriter writer = response.getWriter();
		//writer.println("You searched for...." + searchTerm);
		AddData();
		
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		
		JsonObject jsonObject = new JsonObject();
		//Gson gson = new Gson();
		
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
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		AddData();
		resp.setContentType("application/json;charset=UTF-8");
		PrintWriter writer = resp.getWriter();
		String name = req.getParameter("name");
		
		JsonObject jsonObject = new JsonObject();
		
		JsonArray jArray = new JsonArray();
		for (Student student : list.stream().filter(e -> e.name.toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList())) {
			JsonObject jObject = new JsonObject();
			jObject.addProperty("id", student.getId());
			jObject.addProperty("name", student.getName());
			jArray.add(jObject);
		}

		jsonObject.add("data", jArray);
		jsonObject.addProperty("message", "Hello from server");

		//writer.println(new Gson().toJson(list.stream().filter(e -> e.name.toLowerCase().equals(name.toLowerCase())).findFirst().get()));
		writer.println(jsonObject);
	}

}
