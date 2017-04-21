package servletPackage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Servlet2
 */
@WebServlet("/Servlet2")
public class Servlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    List<String> nameList = new ArrayList<>();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getSession().setAttribute("list", nameList);
		System.out.println("get");
		if(!(null == request.getParameter("deleteid"))){
			//System.out.println("==================" + request.getParameter("deleteid").toString());
			nameList.remove(Integer.valueOf(request.getParameter("deleteid")) - 1);
		}
		request.getRequestDispatcher("/hello.jsp").forward(request, response);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nama = "";
		nama = request.getParameter("name").toString();
		nameList.add(nama);
		request.getSession().setAttribute("list", nameList);
		request.getRequestDispatcher("/hello.jsp").forward(request, response);
		System.out.println("post");
	}

}
